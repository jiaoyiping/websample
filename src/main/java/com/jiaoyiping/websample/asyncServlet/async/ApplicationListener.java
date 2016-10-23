package com.jiaoyiping.websample.asyncServlet.async;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/22
  * Time: 22:41
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 30, 50000L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5000));
        sce.getServletContext().setAttribute("executor",
                executor);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) sce
                .getServletContext().getAttribute("executor");
        executor.shutdown();
    }
}