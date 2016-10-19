package com.jiaoyiping.websample;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/19
  * Time: 14:27
  * To change this template use File | Settings | Editor | File and Code Templates
 */


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = "/*")
public class I18NFilter implements Filter {
    private static final String COOKIE_LANGUAGE = "cookie_language";
    private static final String SYSTEM_LANGUAGE = "system_language";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String systemLanguage = getSystemLanguage(httpServletRequest);
        request.setAttribute(SYSTEM_LANGUAGE, systemLanguage);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("init....");
    }

    private String getSystemLanguage(HttpServletRequest request) {
        String systemLanguage = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_LANGUAGE.equals(cookie.getName())) {
                    systemLanguage = cookie.getValue();
                    break;
                }
            }
        }

        if (systemLanguage == null || "".equals(systemLanguage)) {
            systemLanguage = request.getLocale().toString();
        }
        if (systemLanguage == null || "".equals(systemLanguage)) {
            systemLanguage = Locale.getDefault().toString();
        }
        return systemLanguage;
    }
}