
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 27.12.2021
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="title_update_book"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="title_update_book"></fmt:message></h1></div>

<div align="center">
    <form>
        <input type="hidden" name="command" value="UpdateBook">
        <table>
            <tr>
                <td></td>
                <td><fmt:message key="old_data"></fmt:message></td>
                <td><fmt:message key="new_data"></fmt:message></td>
            </tr>
            <tr>
                <td><fmt:message key="id"></fmt:message></td>
                <td><c:out value="${books.bookDtoId}"></c:out></td>
                <td>${book.bookDtoId}</td>
            </tr>
            <tr>
                <td><fmt:message key="book_title"></fmt:message></td>
                <td><c:out value="${books.title}"></c:out></td>
                <td><input type="text" name="title" placeholder="<fmt:message key="book_title"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="book_isbn"></fmt:message></td>
                <td><c:out value="${books.isbn}"></c:out></td>
                <td><input type="text" name="isbn" placeholder="<fmt:message key="book_isbn"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="library_city"></fmt:message></td>
                <td><c:out value="${books.cityLibrary}"></c:out></td>
                <td>
                    <select name="city">
                        <c:forEach var="libraries" items="${libraries}">
                            <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="author_name"></fmt:message></td>
                <td>
                    <c:forEach var="author" items="${books.authors}">
                        <c:out value="${author.name}"></c:out>
                    </c:forEach>
                </td>
                <td><input type="text" name="author" placeholder="<fmt:message key="author_name"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="genre_category"></fmt:message></td>
                <td>
                    <c:forEach var="genre" items="${books.genres}">
                        <c:out value="${genre.category}"></c:out>
                    </c:forEach>
                </td>
                <td> <input type="text" name="genre" placeholder="<fmt:message key="genre_category"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="book_publisher"></fmt:message></td>
                <td><c:out value="${books.publisher}"></c:out></td>
                <td><input type="text" name="publisher" placeholder="<fmt:message key="book_publisher"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="book_year"></fmt:message></td>
                <td><c:out value="${books.year}"></c:out></td>
                <td><input type="text" name="year" placeholder="<fmt:message key="book_year"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="book_shelf"></fmt:message></td>
                <td><c:out value="${books.shelf}"></c:out></td>
                <td><input type="text" name="shelf" placeholder="<fmt:message key="book_shelf"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="book_quantity"></fmt:message></td>
                <td><c:out value="${books.quantity}"></c:out></td>
                <td><input type="number" name="quantity" min="1" placeholder="<fmt:message key="book_quantity"></fmt:message>"></td>
            </tr>
            <tr>
                <td><fmt:message key="book_description"></fmt:message></td>
                <td><c:out value="${books.description}"></c:out></td>
                <td><textarea rows="5" cols="50" name="description" placeholder="<fmt:message key="book_description"></fmt:message>"></textarea></td>
            </tr>
            <td colspan="3">
                <p align="center"><fmt:message key="message_for_add_book_about_authors"></fmt:message></p>
            </td>
            <td colspan="3">
                <td><input type="submit" value="<fmt:message key="update_command"></fmt:message>"></td>
            </td>
        </table>
    </form>
</div>
</body>
</html>
