
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="admin_menu_libraries"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="admin_menu_libraries"></fmt:message></h1></div>
<div align="center">
    <form>
        <input type="hidden" name="command" value="UpdateLibrary">
        <table>
            <tr>
                <td><fmt:message key="data"></fmt:message></td>
                <td><fmt:message key="old_data"></fmt:message></td>
                <td><fmt:message key="new_data"></fmt:message></td>
            </tr>
            <tr>
                <td><fmt:message key="id"></fmt:message></td>
                <td><c:out value="${library.libraryId}"></c:out></td>
                <td>${library.libraryId}</td>
            </tr>
            <tr>
                <td><fmt:message key="library_city"></fmt:message></td>
                <td><c:out value="${library.city}"></c:out></td>
                <td><input type="text" name="city" placeholder="${library.city}"></td>
            </tr>
            <tr>
                <td><fmt:message key="library_street"></fmt:message></td>
                <td><c:out value="${library.street}"></c:out></td>
                <td><input type="text" name="street" placeholder="${library.street}"></td>
            </tr>
            <tr>
                <td colspan="3"><input class="button" type="submit" value="<fmt:message key="button_update"></fmt:message>"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
