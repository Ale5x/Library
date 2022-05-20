<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="message_page"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<div class="container">
    <div class="center">
        <button class="button" type="button" name="back" onclick="history.back()"><fmt:message key="back"></fmt:message></button>
    </div>
</div>
<br><br>
<div align="center">
    <h1><fmt:message key="message_page"></fmt:message></h1>
    <h2>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1001}">
            <fmt:message key="message_code_1001"></fmt:message>
            <c:remove var="MESSAGE_CODE_1001" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1002}">
            <fmt:message key="message_code_1002"></fmt:message>
            <c:remove var="MESSAGE_CODE_1002" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_10021}">
            <fmt:message key="message_code_10021"></fmt:message>
            <c:remove var="MESSAGE_CODE_10021" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1003}">
            <fmt:message key="message_code_1003"></fmt:message>
            <c:remove var="MESSAGE_CODE_1003" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_10031}">
            <fmt:message key="message_code_10031"></fmt:message>
            <c:remove var="MESSAGE_CODE_10031" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1004}">
            <fmt:message key="message_code_1004"></fmt:message>
            <c:remove var="MESSAGE_CODE_1004" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1005}">
            <fmt:message key="message_code_1005"></fmt:message>
            <c:remove var="MESSAGE_CODE_1005" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1006}">
            <fmt:message key="message_code_1006"></fmt:message>
            <c:remove var="MESSAGE_CODE_1006" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1007}">
            <fmt:message key="message_code_1007"></fmt:message>
            <c:remove var="MESSAGE_CODE_1007" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1008}">
            <fmt:message key="message_code_1008"></fmt:message>
            <c:remove var="MESSAGE_CODE_1008" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1009}">
            <fmt:message key="message_code_1009"></fmt:message>
            <c:remove var="MESSAGE_CODE_1009" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1010}">
            <fmt:message key="message_code_1010"></fmt:message>
            <c:remove var="MESSAGE_CODE_1010" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1011}">
            <fmt:message key="message_code_1011"></fmt:message>
            <c:remove var="MESSAGE_CODE_1011" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1012}">
            <fmt:message key="message_code_1012"></fmt:message>
            <c:remove var="MESSAGE_CODE_1012" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1013}">
            <fmt:message key="message_code_1013"></fmt:message>
            <c:remove var="MESSAGE_CODE_1013" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1014}">
            <fmt:message key="message_code_1014"></fmt:message>
            <c:remove var="MESSAGE_CODE_1014" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1015}">
            <fmt:message key="message_code_1015"></fmt:message>
            <c:remove var="MESSAGE_CODE_1015" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1016}">
            <fmt:message key="message_code_1016"></fmt:message>
            <c:remove var="MESSAGE_CODE_1016" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1017}">
            <fmt:message key="message_code_1017"></fmt:message>
            <c:remove var="MESSAGE_CODE_1017" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1018}">
            <fmt:message key="message_code_1018"></fmt:message>
            <c:remove var="MESSAGE_CODE_1018" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1019}">
            <fmt:message key="message_code_1019"></fmt:message>
            <c:remove var="MESSAGE_CODE_1019" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.MESSAGE_CODE_1020}">
            <fmt:message key="message_code_1020"></fmt:message>
            <c:remove var="MESSAGE_CODE_1020" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.successfulMessage}">
                <fmt:message key="successful_message"></fmt:message>
            <c:remove var="successfulMessage" scope="session" />
        </c:if>
    </h2>
</div>
<div align="center">
    <h2 style="color:red">
        <c:if test="${not empty MESSAGE_ERROR_CODE_1001}">
                <fmt:message key="message_error_code_1001"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1001" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1002}">
            <fmt:message key="message_error_code_1002"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1002" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1003}">
            <fmt:message key="message_error_code_1003"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1003" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1004}">
            <fmt:message key="message_error_code_1004"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1004" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1005}">
            <fmt:message key="message_error_code_1005"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1005" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1006}">
            <fmt:message key="message_error_code_1006"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1006" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1007}">
            <fmt:message key="message_error_code_1007"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1007" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1008}">
            <fmt:message key="message_error_code_1008"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1008" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1009}">
            <fmt:message key="message_error_code_1009"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1009" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1010}">
            <fmt:message key="message_error_code_1010"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1010" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1011}">
            <fmt:message key="message_error_code_1011"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1011" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1012}">
            <fmt:message key="message_error_code_1012"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1012" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1013}">
            <fmt:message key="message_error_code_1013"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1013" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1014}">
            <fmt:message key="message_error_code_1014"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1014" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1015}">
            <fmt:message key="message_error_code_1015"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1015" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1016}">
            <fmt:message key="message_error_code_1016"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1016" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1017}">
            <fmt:message key="message_error_code_1017"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1017" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1018}">
            <fmt:message key="message_error_code_1018"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1018" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1019}">
            <fmt:message key="message_error_code_1019"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1019" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1020}">
            <fmt:message key="message_error_code_1020"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1020" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1021}">
            <fmt:message key="message_error_code_1021"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1021" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1022}">
            <fmt:message key="message_error_code_1022"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1022" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1023}">
            <fmt:message key="message_error_code_1023"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1023" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1024}">
            <fmt:message key="message_error_code_1024"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1024" scope="session" />
        </c:if>
        <c:if test="${not empty MESSAGE_ERROR_CODE_1025}">
            <fmt:message key="message_error_code_1025"></fmt:message>
            <c:remove var="MESSAGE_ERROR_CODE_1025" scope="session" />
        </c:if>
        <c:if test="${not empty negativeMessage}">
            <fmt:message key="negative_message"></fmt:message>
            <c:remove var="negativeMessage" scope="session" />
        </c:if>
    </h2>
</div>
</body>
</html>
