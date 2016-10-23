<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/19
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/i18n_${system_language}"/>

<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript" src="js/jquery-3.1.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#language').find('a').click(function () {
                var language = $(this).data('value');
                document.cookie = "cookie_language=" + language +
                        ";expires=365";
                location.reload();
            });
        });
    </script>
</head>
<body>

<div style="font-size: large;color: darkcyan">
    <fmt:message key="common.bookname"/>

</div>


<div id="footer">
    <div id="copyright">
        <span><fmt:message key="common.copyright"/></span>
    </div>
    <div id="language">
        <span><fmt:message key="common.language"/>:</span>
        <a href="#" data-value="zh_CN">中文</a>
        <span>|</span>
        <a href="#" data-value="en_US">English</a>
    </div>
</div>
</body>
</html>
