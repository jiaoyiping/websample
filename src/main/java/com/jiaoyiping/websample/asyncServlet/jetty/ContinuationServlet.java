package com.jiaoyiping.websample.asyncServlet.jetty;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/23
  * Time: 23:52
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationListener;
import org.eclipse.jetty.continuation.ContinuationSupport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

@WebServlet(urlPatterns = "/pull", asyncSupported = true)
public class ContinuationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        Map<String, PushAgent> pushAgentMap = (Map<String, PushAgent>) req.getServletContext().getAttribute("agentmap");
        if (pushAgentMap.containsKey(user)) {
            PushAgent pushAgent = pushAgentMap.get(user);
            Continuation continuation = ContinuationSupport.getContinuation(req);
            continuation.setTimeout(90000000);
            //第一次请求进来
            if (continuation.isInitial()) {
                resp.setContentType("text/evf;charset=utf-8");
                resp.setHeader("Connection", "keep-alive");
                resp.setHeader("Keep-Alive", "timeout=2000");
                PushAdapter pushAdapter = new PushAdapter(continuation, pushAgent);
                continuation.setAttribute("adapter", pushAdapter);
                continuation.addContinuationListener(new ContinuationListener() {
                    @Override
                    public void onComplete(Continuation continuation) {
                        PushAdapter adapter = (PushAdapter) continuation.getAttribute("adapter");
                        if (null != adapter) {
                            continuation.setAttribute("adapter", null);
                        }
                    }

                    @Override
                    public void onTimeout(Continuation continuation) {
                        onComplete(continuation);
                    }

                });
                resp.flushBuffer();
            }
            if (continuation.isExpired()) {
                return;
            }
            Writer writer = getWriter(resp);
            PushAdapter adapter = (PushAdapter) continuation.getAttribute("adapter");
            Message message;
            while (true) {
                message = adapter.getPushAgent().pull();
                if (null == message)
                    break;
                try {
                    writer.write(message.getContent());
                    writer.flush();
                    writer.write("\r\n");
                    writer.flush();
                    resp.flushBuffer();
                } catch (Exception e) {
                    throw e;

                }
            }
            //若没有该客户端的消息,则请求被挂起
            continuation.suspend();
        }

    }

    private Writer getWriter(HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        return new OutputStreamWriter(os, "UTF-8");
    }


}