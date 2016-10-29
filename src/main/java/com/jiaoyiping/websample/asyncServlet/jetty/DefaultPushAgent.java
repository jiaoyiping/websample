package com.jiaoyiping.websample.asyncServlet.jetty;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/25
  * Time: 23:17
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class DefaultPushAgent implements PushAgent {

    private Terminal terminal;
    //客户端通过长连接连接到服务器时,服务器不断地从该队列poll(),若果拿到新的消息,则返回给客户端
    private Queue<Message> messages = new PriorityQueue<>();
    private MessageListener messageListener;

    @Override
    public Terminal getTerminal() {
        return this.terminal;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public Message send(Message message) {
        synchronized (message) {
            messages.add(message);
        }

        return message;
    }

    @Override
    public Message pull() {
        synchronized (messages) {
            return messages.poll();
        }

    }

    @Override
    public void addListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void onEvent(Message message) {
        this.messageListener.onMessage(message);
    }

    @Override
    public boolean isInited() {
        return this.messageListener != null;
    }


    public DefaultPushAgent(Terminal terminal) {
        this.terminal = terminal;
    }
}