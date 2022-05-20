<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.12.2021
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="admin_menu_catalog_order"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="admin_menu_catalog_order"></fmt:message></h1></div>

<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td><fmt:message key="search_user_id"></fmt:message></td>
            <td><input type="number" name="userId" min="1" placeholder="<fmt:message key="user_id_for_row"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
        <tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td><fmt:message key="search_order_id"></fmt:message></td>
            <td><input type="number" name="orderId" min="1" placeholder="<fmt:message key="search_order_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
        <tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
            <td><fmt:message key="order_show_by_city"></fmt:message></td>
            <td>
                <select name="city">
                    <c:forEach var="libraries" items="${libraries}">
                        <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>

            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
</table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td><fmt:message key="order_show_by_status"></fmt:message></td>
            <td>
                <select name="status">
                    <option value="opened"><fmt:message key="order_status_opened"></fmt:message></option>
                    <option value="approved"><fmt:message key="order_status_approved"></fmt:message></option>
                    <option value="rejected"><fmt:message key="order_status_rejected"></fmt:message></option>
                    <option value="arrived"><fmt:message key="order_status_arrived"></fmt:message></option>
                    <option value="closed"><fmt:message key="order_status_closed"></fmt:message></option>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
</table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td><fmt:message key="order_show_by_city_and_status"></fmt:message></td>
            <td>
                <select name="city">
                    <c:forEach var="libraries" items="${libraries}">
                        <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                    </c:forEach>
                </select>
                <select name="status">
                    <option value="opened"><fmt:message key="order_status_opened"></fmt:message></option>
                    <option value="approved"><fmt:message key="order_status_approved"></fmt:message></option>
                    <option value="rejected"><fmt:message key="order_status_rejected"></fmt:message></option>
                    <option value="arrived"><fmt:message key="order_status_arrived"></fmt:message></option>
                    <option value="closed"><fmt:message key="order_status_closed"></fmt:message></option>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="OrderCatalog">
    <table>
        <tr>
            <td><fmt:message key="order_show_all"></fmt:message></td>
            <td><input type="submit" value="<fmt:message key="order_show_all"></fmt:message>" name="getAll"></td>
        </tr>
</table>
</form>

<br>
<div align="center">
    <c:if test="${not empty orders}">
        <p><fmt:message key="message_count_found_result"></fmt:message> <c:out value="${ordersSize}"/></p>
    </c:if>
<table class="table table-sm">
    <c:if test="${not empty orders}">
            <c:forEach var="orders" items="${orders}" varStatus="status">
            <tr class="tr" >
                <tr>
                    <td>
                        <b>#</b>
                        <c:out value="${status.index + 1}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="id"></fmt:message>:</b> <c:out value="${orders.orderDtoId}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="user_id"></fmt:message>:</b> <c:out value="${orders.userId}"></c:out> <a class="btn btn-info" href="?command=UserCatalog&userId=${orders.userId}"><fmt:message key="details"></fmt:message></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="order_id_admin"></fmt:message>:</b>
                        <c:if test="${orders.adminId != 0}">
                            <c:out value="${orders.adminId}"></c:out> <a class="btn btn-info" href="?command=UserCatalog&userId=${orders.adminId}"><fmt:message key="details"></fmt:message></a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="book_id"></fmt:message>:</b> <c:out value="${orders.bookId}"></c:out> <a class="btn btn-info" href="?command=CatalogBook&bookId=${orders.bookId}"><fmt:message key="details"></fmt:message></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="book_title"></fmt:message>:</b> <c:out value="${orders.title}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="book_isbn"></fmt:message>:</b> <c:out value="${orders.isbn}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="book_year"></fmt:message>:</b> <c:out value="${orders.year}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="order_date"></fmt:message>:</b> <c:out value="${orders.date}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                       <b><fmt:message key="library_city"></fmt:message>:</b> <c:out value="${orders.cityLibrary}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="order_comment"></fmt:message>:</b> <c:out value="${orders.comment}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                       <b><fmt:message key="status"></fmt:message>:</b>
                        <c:if test="${orders.status eq 'OPENED'}">
                            <fmt:message key="order_status_opened"></fmt:message>
                        </c:if>
                        <c:if test="${orders.status eq 'APPROVED'}">
                            <fmt:message key="order_status_approved"></fmt:message>
                        </c:if>
                        <c:if test="${orders.status eq 'ARRIVED'}">
                            <fmt:message key="order_status_arrived"></fmt:message>
                        </c:if>
                        <c:if test="${orders.status eq 'REJECTED'}">
                            <fmt:message key="order_status_rejected"></fmt:message>
                        </c:if>
                        <c:if test="${orders.status eq 'CLOSED'}">
                            <fmt:message key="order_status_closed"></fmt:message>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="user_number_of_violations"></fmt:message>:</b> <c:out value="${orders.countViolations}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b><fmt:message key="change_status"></fmt:message>:</b>
                        <c:if test="${orders.status eq 'OPENED'}">
                            <a class="btn btn-success" href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=approved"><fmt:message key="order_status_approved"></fmt:message></a>
                            <a class="btn btn-danger" href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=rejected"><fmt:message key="order_status_rejected"></fmt:message></a>
                        </c:if>
                        <c:if test="${orders.status != 'OPENED'}">
                            <a class="btn btn-light" href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=opened"><fmt:message key="order_status_opened"></fmt:message></a>
                            <a class="btn btn-primary" href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=arrived"><fmt:message key="order_status_arrived"></fmt:message></a>
                            <a class="btn btn-light" href="?command=ActionOrder&orderId=${orders.orderDtoId}&status=closed"><fmt:message key="order_status_closed"></fmt:message></a>
                        </c:if>
                    </td>
                </tr>
            </tr>
            </c:forEach>
        </table>
        </c:if>
</div>
</body>
</html>
