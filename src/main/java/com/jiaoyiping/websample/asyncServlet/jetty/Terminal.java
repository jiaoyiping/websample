package com.jiaoyiping.websample.asyncServlet.jetty;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/25
  * Time: 22:58
  * To change this template use File | Settings | Editor | File and Code Templates
 */

public class Terminal {
    public String id;
    public String address;
    public String token;
    public String owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Terminal() {
    }
}