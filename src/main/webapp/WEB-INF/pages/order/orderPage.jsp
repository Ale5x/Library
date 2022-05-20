<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 16.12.2021
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="order_book"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="order_book"></fmt:message></h1></div>
<form method="get">
    <input type="hidden" name="command" value="OrderBook">
    <div align="center">
        <table>
            <tr>
                <td><fmt:message key="book_id"></fmt:message></td>
                <td><c:out value="${books.bookDtoId}"></c:out></td>
            </tr>
            <tr>
                <td><fmt:message key="book_title"></fmt:message></td>
                <td><c:out value="${books.title}"></c:out></td>
            </tr>
            <tr>
                <td><fmt:message key="book_isbn"></fmt:message></td>
                <td><c:out value="${books.isbn}"></c:out></td>
            </tr>
            <tr>
                <td><fmt:message key="book_publisher"></fmt:message></td>
                <td><c:out value="${books.publisher}"></c:out></td>
            </tr>
            <tr>
                <td><fmt:message key="book_year"></fmt:message></td>
                <td><c:out value="${books.year}"></c:out></td>
            </tr>
            <tr>
                <td><fmt:message key="book_author"></fmt:message></td>
                <td>
                    <c:forEach var="author" items="${books.authors}">
                        <c:out value="${author.name}"></c:out>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="book_genre"></fmt:message></td>
                <td>
                    <c:forEach var="genre" items="${books.genres}">
                        <c:out value="${genre.category}"></c:out>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="book_shelf"></fmt:message>:</td>
                <td><c:out value="${books.shelf}"></c:out></td>
            </tr>
            <tr>
                <td><fmt:message key="book_description"></fmt:message></td>
                <td><c:out value="${books.description}"></c:out></td>
            </tr>
            <tr>
                <td><fmt:message key="library_city"></fmt:message></td>
                <td>
                    <select name="city">
                        <c:forEach var="libraries" items="${libraries}">
                            <option value="${libraries.city}"><c:out value="${libraries.city}"></c:out></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="order_comment"></fmt:message></td>
                <td> <p><textarea rows="5" cols="50" name="comment" placeholder="Комментарий"></textarea></p></td>
            </tr>
            <tr>
                <td><input type="submit" value="<fmt:message key="order_command"></fmt:message>"></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
