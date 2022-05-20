<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="admin_menu_catalog_user"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="admin_menu_catalog_user"></fmt:message></h1></div>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td><fmt:message key="search_user_id"></fmt:message></td>
            <td><input type="number" name="userId" min="1" placeholder="<fmt:message key="search_user_id"></fmt:message>"></td>
            <td><input  type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td><fmt:message key="search_by_email"></fmt:message></td>
            <td><input type="text" name="email" placeholder="<fmt:message key="search_by_email"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td><fmt:message key="show_user_by_role"></fmt:message></td>
            <td>
                <select name="role">
                        <option value="user"><fmt:message key="user_role_user"></fmt:message></option>
                        <option value="admin"><fmt:message key="user_role_admin"></fmt:message></option>
                        <option value="manager"><fmt:message key="user_role_manager"></fmt:message></option>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>" name="role"></td>
        </tr>
    </table>
</form>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td><fmt:message key="show_user_by_status"></fmt:message></td>
            <td>
                <select name="status">
                    <option value="active"><fmt:message key="user_status_active"></fmt:message></option>
                    <option value="blocked"><fmt:message key="user_status_blocked"></fmt:message></option>
                    <option value="delete"><fmt:message key="user_status_delete"></fmt:message></option>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form action="Controller">
    <input type="hidden" name="command" value="UserCatalog">
    <table>
        <tr>
            <td><fmt:message key="show_all_user"></fmt:message></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>" name="getAll"></td>
        </tr>
    </table>
</form>
    <br><br><br>
    <c:if test="${not empty users}">
        <div align="center"><p><fmt:message key="message_count_found_result"></fmt:message><c:out value="${userSize}"/></p></div>
        <table class="table table-hover">
            <tr class="thead-dark">
                <th>#</th>
                <th><fmt:message key="id"></fmt:message></th>
                <th><fmt:message key="user_first_name"></fmt:message></th>
                <th><fmt:message key="user_last_name"></fmt:message></th>
                <th><fmt:message key="user_email"></fmt:message></th>
                <th><fmt:message key="user_date_registration"></fmt:message></th>
                <th><fmt:message key="user_number_of_violations"></fmt:message></th>
                <th><fmt:message key="user_role"></fmt:message></th>
                <th><fmt:message key="status"></fmt:message></th>
                <th><fmt:message key="change_status"></fmt:message></th>
                <th><fmt:message key="loan_card"></fmt:message></th>
                <th><fmt:message key="orders"></fmt:message></th>
            </tr>

            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${user.userId}"></c:out></td>
                    <td><c:out value="${user.firstName}"></c:out></td>
                    <td><c:out value="${user.lastName}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><c:out value="${user.dateRegistration}"></c:out></td>
                    <td><c:out value="${user.countViolations}"></c:out></td>
                    <td><c:out value="${user.role}"></c:out></td>
                    <td><c:out value="${user.status}"></c:out></td>
                    <td>
                        <a class="btn btn-success" href="?command=ActionUser&userId=${user.userId}&status=active"><fmt:message key="user_status_active"></fmt:message></a>
                        <a class="btn btn-warning" href="?command=ActionUser&userId=${user.userId}&status=blocked"><fmt:message key="block_command"></fmt:message></a>
                        <a class="btn btn-danger" href="?command=ActionUser&userId=${user.userId}&status=delete"><fmt:message key="delete_command"></fmt:message></a>
                    </td>
                    <td>
                        <a class="btn btn-info" href="?command=LoanCardCatalog&userId=${user.userId}"><fmt:message key="history"></fmt:message></a>
                    </td>
                    <td>
                        <a class="btn btn-info" href="?command=OrderCatalog&userId=${user.userId}"><fmt:message key="orders"></fmt:message></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
