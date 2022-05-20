<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.12.2021
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="title_work_with_admin"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="title_work_with_admin"></fmt:message></h1></div>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ManagerCatalog">
    <table>
        <tr>
            <td><fmt:message key="enter_admin_id"></fmt:message></td>
            <td><input type="number" name="userId" min="1" placeholder="<fmt:message key="id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ManagerCatalog">
    <table>
        <tr>
            <td><fmt:message key="enter_admin_email"></fmt:message></td>
            <td><input type="text" name="email" placeholder="<fmt:message key="user_email"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ManagerCatalog">
    <table>
        <tr>
            <td><fmt:message key="show_all_admin"></fmt:message></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>" name="allAdmins"></td>
        </tr>
    </table>
</form>

<c:if test="${not empty users}">
<div align="center">
    <c:if test="${not empty books}">
        <b><fmt:message key="message_count_found_result"></fmt:message><c:out value="${userSize}"/></b>
    </c:if>
<table class="table table-hover">
    <c:forEach var="users" items="${users}" varStatus="status">
    <tr class="tr">
        <tr>
            <th>#</th>
        <td>
            <c:out value="${status.index + 1}"></c:out>
        </td>
        </tr>
        <tr>
            <th><fmt:message key="user_id_for_row"></fmt:message></th>
            <td>
                <c:out value="${users.userId}"></c:out>
            </td>
            <td>
                <a class="btn btn-info" href="?command=UserCatalog&userId=${users.userId}"><fmt:message key="details"></fmt:message></a>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="user_first_name"></fmt:message></th>
            <td>
                <c:out value="${users.firstName}"></c:out>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="user_last_name"></fmt:message></th>
            <td>
                <c:out value="${users.lastName}"></c:out>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="user_email"></fmt:message></th>
            <td>
                <c:out value="${users.email}"></c:out>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="user_date_registration"></fmt:message></th>
            <td>
                <c:out value="${users.dateRegistration}"></c:out>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="user_number_of_violations"></fmt:message></th>
            <td>
                <c:out value="${users.countViolations}"></c:out>
            </td>
            <td>
                <a class="btn btn-danger" href="?command=ActionUser&userId=${users.userId}&addViolation=addViolation"><fmt:message key="user_add_violation"></fmt:message></a>
                <a class="btn btn-success" href="?command=ActionUser&userId=${users.userId}&removeViolation=removeViolation"><fmt:message key="user_remove_violation"></fmt:message></a>

            </td>
        </tr>
        <tr>
            <th><fmt:message key="user_role"></fmt:message></th>
            <td>
                <c:out value="${users.role}"></c:out>
            </td>
            <td>
                <a class="btn btn-light" href="?command=ActionAdminCommand&userId=${users.userId}&role=user"><fmt:message key="user_role_user"></fmt:message></a>
                <a class="btn btn-light" href="?command=ActionAdminCommand&userId=${users.userId}&role=admin"><fmt:message key="user_role_admin"></fmt:message></a>
                <a class="btn btn-light" href="?command=ActionAdminCommand&userId=${users.userId}&role=manager"><fmt:message key="user_role_manager"></fmt:message></a>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="status"></fmt:message></th>
            <td>
                <c:out value="${users.status}"></c:out>
            </td>
            <td>
                <a class="btn btn-success" href="?command=ActionUser&userId=${users.userId}&status=active"><fmt:message key="user_status_active"></fmt:message></a>
                <a class="btn btn-warning" href="?command=ActionUser&userId=${users.userId}&status=blocked"><fmt:message key="block_command"></fmt:message></a>
                <a class="btn btn-danger" href="?command=ActionUser&userId=${users.userId}&status=delete"><fmt:message key="delete_command"></fmt:message></a>
            </td>
        </tr>
    </tr>
    </c:forEach>
        </table>
</div>
</c:if>
</body>
</html>
