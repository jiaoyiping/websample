package com.jiaoyiping.websample.asyncServlet.jetty;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/25
  * Time: 22:56
  * To change this template use File | Settings | Editor | File and Code Templates
 */

public interface PushAgent {

    Terminal getTerminal();

    String getAddress();

    String getToken();

    Message send(Message message);

    Message pull();

    void addListener(MessageListener messageListener);

    void onEvent(Message message);

    boolean isInited();
}
