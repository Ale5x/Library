<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.01.2022
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="position-fixed">
    <a class="btn btn-info" href="?sessionLocale=en&command=ChangeLocale"><fmt:message key="language.en"/>  |</a>
    <a class="btn btn-info" href="?sessionLocale=ru&command=ChangeLocale"><fmt:message key="language.ru"/>  |</a>
    <a class="btn btn-info" href="Controller?command=GoToMainPage"><fmt:message key="main_page"></fmt:message></a>
</div>
<br><br>
<div align="center">
    <h1><fmt:message key="message_404"></fmt:message></h1>
</div>
<img src="${pageContext.request.contextPath}/images/error404.png" class="mx-auto d-block" width="100%">
</body>
</html>
