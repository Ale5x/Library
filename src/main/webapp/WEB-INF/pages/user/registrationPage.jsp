<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="registration"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

</head>
<body background="${pageContext.request.contextPath}/images/61d9d03789b309.300990841.jpg" class="bg_img">
<div class="position-fixed">
    <div class="left">
        <a class="btn btn-info" href="?sessionLocale=en&command=ChangeLocale"><fmt:message key="language.en"/>  |</a>
        <a class="btn btn-info" href="?sessionLocale=ru&command=ChangeLocale"><fmt:message key="language.ru"/>  |</a>
        <button class="btn btn-info" type="button" name="back" onclick="history.back()"><fmt:message key="back"></fmt:message></button>
        <a class="btn btn-info" href="Controller?command=GoToMainPage"><fmt:message key="main_page"></fmt:message></a>
    </div>
</div>
<br><br>
<div align="center"><h1><fmt:message key="registration"></fmt:message></h1></div>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="Registration">
    <div align="center">
        <table>
            <tr>
                <td><fmt:message key="user_email"></fmt:message></td>
                <td><input type="text" name="email" placeholder="<fmt:message key="user_email"></fmt:message>" maxlength="30"></td>
            </tr>
            <tr>
                <td><fmt:message key="password_user"></fmt:message></td>
                <td><input type="password" name="password" placeholder="<fmt:message key="password_user"></fmt:message>" maxlength="30"></td>
            </tr>
            <tr>
                <td colspan="2"> <i>
                    <p><fmt:message key="message_for_registration"></fmt:message></p>
                </i>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="user_first_name"></fmt:message></td>
                <td><input type="text" name="firstName" placeholder="<fmt:message key="user_first_name"></fmt:message>" maxlength="30"></td>
            </tr>
            <tr>
                <td><fmt:message key="user_last_name"></fmt:message></td>
                <td><input type="text" name="lastName" placeholder="<fmt:message key="user_last_name"></fmt:message>"maxlength="30"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="<fmt:message key="registration"></fmt:message>"></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
