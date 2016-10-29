package com.jiaoyiping.websample.asyncServlet.jetty;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/25
  * Time: 23:12
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.io.Serializable;

public class Message implements Serializable,Comparable {
    private static final long serialVersionUID = 73081345100638944L;
    private String id;
    private String source;
    private String target;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}