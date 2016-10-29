package com.jiaoyiping.websample.asyncServlet.jetty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/25
  * Time: 23:46
  * To change this template use File | Settings | Editor | File and Code Templates
 */
@WebServlet(urlPatterns = "/send")
public class MesssageSendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //不要在自己实现的servlet中调用 super.doGet(0)或者是super.doPost()
        //因为在tomcat它们的默认实现是报405(HTTP1.1)或者400(其他版本的HTTP)
        //        super.doPost(req, resp);

        String target = req.getParameter("target");
        String messageStr = req.getParameter("message");

        Map<String, PushAgent> agentMap = (Map<String, PushAgent>) req.getServletContext().getAttribute("agentmap");
        if (agentMap.keySet().contains(target)) {
            Message message = new Message();
            message.setTarget(target);
            message.setContent(messageStr);
            if (agentMap.get(target).isInited()) {
                agentMap.get(target).onEvent(message);
            }
            agentMap.get(target).send(message);
            PrintWriter out = resp.getWriter();
            out.print("发送成功");
            out.flush();
            out.close();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}