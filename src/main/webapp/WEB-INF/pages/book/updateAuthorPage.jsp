<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 17.01.2022
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="title_author_update"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="title_author_update"></fmt:message></h1></div>
<div align="center">
    <form>
        <input type="hidden" name="command" value="UpdateAuthor">
        <table>
            <tr>
                <td><fmt:message key="data"></fmt:message></td>
                <td><fmt:message key="old_data"></fmt:message></td>
                <td><fmt:message key="new_data"></fmt:message></td>
            </tr>
            <tr>
                <td><fmt:message key="id"></fmt:message></td>
                <td><c:out value="${authors.authorId}"></c:out></td>
                <td>${authors.authorId}</td>
            </tr>
            <tr>
                <td><fmt:message key="author_name"></fmt:message></td>
                <td><c:out value="${authors.name}"></c:out></td>
                <td><input type="text" name="name" placeholder="${authors.name}"></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" value="<fmt:message key="button_update"></fmt:message>"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
