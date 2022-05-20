<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 08.01.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title>Header</title>
<%--    <link rel="stylesheet" href="css/style.css">--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="position-fixed" >
<%--    <div class="row justify-content-around ">--%>

        <a class="btn btn-info" href="?sessionLocale=en&command=ChangeLocale"><fmt:message key="language.en"/>  |</a>
        <a class="btn btn-info" href="?sessionLocale=ru&command=ChangeLocale"><fmt:message key="language.ru"/>  |</a>
<%--    </div>--%>
    <c:if test="${user.role != 'GUEST'}">
        <button class="btn btn-info" type="button" name="back" onclick="history.back()"><fmt:message key="back"></fmt:message></button>
        <a class="btn btn-info" href="Controller?command=GoToMainPage"><fmt:message key="main_page"></fmt:message></a>

        <a class="btn btn-info" href="?command=GoToHome"><fmt:message key="private_room"></fmt:message></a>

        <c:if test="${user.role != 'GUEST' and user.role eq 'ADMIN' or user.role eq 'MANAGER'}">
            <a class="btn btn-info" href="?command=GoToAdminPage"><fmt:message key="admin_room"></fmt:message></a>
        </c:if>

        <button class="btn btn-info" type="button" name="back" onclick="history.forward()"><fmt:message key="next"></fmt:message></button>
<%--    </div>--%>
<%--        <div class="right">--%>
        <a class="btn btn-info" href="?command=SignOut"><fmt:message key="login_sign_out"></fmt:message></a>

<%--    </div>--%>

<%--    <div class="center">--%>
        </c:if>
    </div>
</div>
</body>
</html>
