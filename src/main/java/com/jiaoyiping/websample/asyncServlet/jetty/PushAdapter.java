package com.jiaoyiping.websample.asyncServlet.jetty;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/25
  * Time: 23:37
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import org.eclipse.jetty.continuation.Continuation;

public class PushAdapter {
    private Continuation continuation;
    private PushAgent pushAgent;

    public PushAdapter(Continuation continuation, PushAgent pushAgent) {
        this.continuation = continuation;
        this.pushAgent = pushAgent;
        this.pushAgent.addListener(message -> {
            if (PushAdapter.this.continuation.isSuspended()) {
                PushAdapter.this.continuation.resume();
            }
        });
    }

    public Continuation getContinuation() {
        return continuation;
    }

    public void setContinuation(Continuation continuation) {
        this.continuation = continuation;
    }

    public PushAgent getPushAgent() {
        return pushAgent;
    }


    public void setPushAgent(PushAgent pushAgent) {
        this.pushAgent = pushAgent;
    }
}