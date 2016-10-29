package com.jiaoyiping.websample.asyncServlet.jetty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/25
  * Time: 23:55
  * To change this template use File | Settings | Editor | File and Code Templates
 */
@WebListener
public class PushListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, PushAgent> agentMap = new HashMap<>();
        agentMap.put("zhangsan", new DefaultPushAgent(new Terminal() {{
            setAddress("zhangsan");
            setToken("zhangsan_token");
        }}));
        agentMap.put("lisi", new DefaultPushAgent(new Terminal() {{
            setAddress("lisi");
            setToken("lisi_token");
        }}));

        sce.getServletContext().setAttribute("agentmap",agentMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}