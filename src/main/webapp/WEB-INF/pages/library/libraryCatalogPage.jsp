<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
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

<table>
    <tr>
        <td>
            <form>
                <input type="hidden" name="command" value="LibraryCatalog">
                <table>
                    <tr>
                        <td><fmt:message key="enter_library_id"></fmt:message></td>
                        <td><input type="number" name="libraryId" min="1" placeholder="<fmt:message key="enter_library_id"></fmt:message>"></td>
                        <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                    </tr>
                </table>
            </form>
            <form>
                <input type="hidden" name="command" value="LibraryCatalog">
                <table>
                    <tr>
                        <td><fmt:message key="enter_library_city"></fmt:message></td>
                        <td><input type="text" name="city" placeholder="<fmt:message key="enter_library_city"></fmt:message>"></td>
                        <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
                    </tr>
                </table>
            </form>
            <form>
                <input type="hidden" name="command" value="LibraryCatalog">
                <table>
                    <tr>
                        <td><fmt:message key="library_change_status"></fmt:message></td>
                        <td>
                            <select name="status">
                                <option value="opened"><fmt:message key="library_status_opened"></fmt:message></option>
                                <option value="closed"><fmt:message key="library_status_closed"></fmt:message></option>
                            </select>
                        </td>
                        <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>" name="status"></td>
                    </tr>
                </table>
            </form>
            <form>
                <input type="hidden" name="command" value="LibraryCatalog">
                <table>
                    <tr>
                        <input type="submit" value="<fmt:message key="library_show_all"></fmt:message>" name="getAll">
                    </tr>
                </table>
            </form>
        </td>
        <td>
            <form>
                <input type="hidden" name="command" value="CreateLibrary">
                <table>
                    <tr>
                        <th><fmt:message key="library_create_new_library"></fmt:message></th>
                    </tr>
                    <tr>
                        <td><fmt:message key="enter_library_city"></fmt:message></td>
                        <td><input type="text" name="city" placeholder="<fmt:message key="enter_library_city"></fmt:message>" maxlength="30"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="enter_library_street"></fmt:message></td>
                        <td><input type="text" name="street" placeholder="<fmt:message key="enter_library_street"></fmt:message>" maxlength="30"></td>
                    </tr>
                    <tr><td><input type="submit" value="<fmt:message key="button_add"></fmt:message>"></tr></td>
                </table>
            </form>
        </td>
    </tr>
</table>

<div>
    <table class="table table-hover">
        <c:if test="${not empty libraries}">
            <tr class="thead-dark">
                <th>#</th>
                <th><fmt:message key="id"></fmt:message></th>
                <th><fmt:message key="library_city"></fmt:message></th>
                <th><fmt:message key="library_street"></fmt:message></th>
                <th><fmt:message key="status"></fmt:message></th>
                <th><fmt:message key="orders"></fmt:message></th>
                <th><fmt:message key="loan_card"></fmt:message></th>
                <th><fmt:message key="books"></fmt:message></th>
                <th><fmt:message key="update_command"></fmt:message></th>
                <th><fmt:message key="change_status"></fmt:message></th>
            </tr>
            <c:forEach var="library" items="${libraries}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"></c:out></td>
                    <td><c:out value="${library.libraryId}"></c:out></td>
                    <td><c:out value="${library.city}"></c:out></td>
                    <td><c:out value="${library.street}"></c:out></td>
                    <td><c:out value="${library.status}"></c:out></td>
                    <td> <a class="btn btn-info" href="?command=OrderCatalog&city=${library.city}"><fmt:message key="orders"></fmt:message></a></td>
                    <td> <a class="btn btn-info" href="?command=LoanCardCatalog&city=${library.city}"><fmt:message key="loan_card"></fmt:message></a></td>
                    <td> <a class="btn btn-info" href="?command=CatalogBook&city=${library.city}"><fmt:message key="books"></fmt:message></a></td>
                    <td>
                        <a class="btn btn-dark" href="?command=GoToUpdateLibrary&libraryId=${library.libraryId}&city=${library.city}&street=${library.street}"><fmt:message key="button_update"></fmt:message></a>
                    </td>
                    <td>
                        <a class="btn btn-success" href="?command=ActionLibrary&libraryId=${library.libraryId}&status=opened"><fmt:message key="library_status_opened"></fmt:message></a>
                        <a class="btn btn-danger" href="?command=ActionLibrary&libraryId=${library.libraryId}&status=closed"><fmt:message key="library_status_closed"></fmt:message></a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
