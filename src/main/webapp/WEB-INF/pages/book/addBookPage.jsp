<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.12.2021
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="title_add_new_book"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="title_add_new_book"></fmt:message></h1></div>

<div align="center">
    <table>
        <tr>
            <td>
                <table>
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="CreateBook">
                        <tr>
                            <td><fmt:message key="enter_title"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_title"></fmt:message>" name="title" maxlength="70"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="enter_isbn"></fmt:message>: </td>
                            <td><input type="text" placeholder="<fmt:message key="enter_isbn"></fmt:message>" name="isbn" maxlength="30"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="enter_publisher"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_publisher"></fmt:message>" name="publisher" maxlength="30"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="enter_year"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_year"></fmt:message>" name="year" maxlength="30"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="enter_quantity"></fmt:message></td>
                            <td><input type="number" placeholder="<fmt:message key="enter_quantity"></fmt:message>" name="quantity" min="1"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="enter_library"></fmt:message></td>
                            <td>
                                <select name="city">
                                    <c:forEach var="libraries" items="${libraries}">
                                        <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message key="enter_shelf"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_shelf"></fmt:message>" name="shelf" maxlength="5"></td>
                        </tr>
                        <tr>
                            <td>*<fmt:message key="enter_authors"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_authors"></fmt:message>" name="author"></td>
                        </tr>
                        <tr>
                            <td>*<fmt:message key="enter_genres"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_genres"></fmt:message>" name="category"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="enter_description"></fmt:message></td>
                            <td><textarea rows="5" cols="50" name="description"></textarea></td>
                        </tr>
                        <tr>
                            <td colspan="2">*<fmt:message key="message_for_add_book_about_authors"></fmt:message></td>
                        </tr>
                        <tfoot align="center">
                        <tr>
                            <th colspan="2"><input type="submit" VALUE="<fmt:message key="button_add"></fmt:message>" name="addBook"></th>
                        </tr>
                        </tfoot>
                    </form>
                </table>
            </td>
            <td>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="CatalogBook">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="2"><fmt:message key="search_book_by_title"></fmt:message></th>
                        </tr>
                        </thead>
                        <tr>
                            <td><fmt:message key="enter_title"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_title"></fmt:message>" name="title"></td>
                        </tr>
                    </table>
                    <input type="submit" VALUE="<fmt:message key="button_find"></fmt:message>" name="search">
                </form>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="CreateAuthor">
                    <table>
                        <thead>
                            <th><fmt:message key="create_author"></fmt:message></th>
                        </thead>
                        <tr>
                            <td><fmt:message key="enter_authors"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_authors"></fmt:message>" name="authors" maxlength="30"></td>
                        </tr>
                    </table>
                    <input type="submit" VALUE="<fmt:message key="button_add"></fmt:message>" name="add_author">
                </form>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="CreateGenre">
                    <table>
                        <thead>
                        <th><fmt:message key="create_genre"></fmt:message></th>
                        </thead>
                        <tr>
                            <td><fmt:message key="enter_genres"></fmt:message></td>
                            <td><input type="text" placeholder="<fmt:message key="enter_genres"></fmt:message>" name="genre" maxlength="30"></td>
                        </tr>
                    </table>
                    <input type="submit" VALUE="<fmt:message key="button_add"></fmt:message>" name="add_genres">
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
