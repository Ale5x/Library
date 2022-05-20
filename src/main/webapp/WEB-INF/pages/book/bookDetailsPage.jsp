<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 31.01.2022
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="title_book_details"></fmt:message></title>
    <jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<br><br>
<div align="center"><h1><fmt:message key="title_book_details"></fmt:message></h1></div>
<br>
<table align="center">
        <tr>
            <td>
                <h3><b><c:out value="${books.title}"></c:out>  (<c:out value="${books.year}"></c:out>)  <c:forEach var="author" items="${books.authors}">
                    <c:out value="${author.name}"></c:out>
                </c:forEach></b></h3>
            </td>
        </tr>
        <tr>
        <tr>
            <td>
                <b><fmt:message key="book_id"></fmt:message>:</b> <c:out value="${books.bookDtoId}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_isbn"></fmt:message>:</b> <c:out value="${books.isbn}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_author"></fmt:message>:</b>
                <c:forEach var="author" items="${books.authors}">
                    <c:out value="${author.name}"></c:out>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_genre"></fmt:message>:</b>

                <c:forEach var="genre" items="${books.genres}">
                    <c:out value="${genre.category}"></c:out>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_publisher"></fmt:message>:</b>
                <c:out value="${books.publisher}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_year"></fmt:message>:</b>
                <c:out value="${books.year}"></c:out>
            </td>
        </tr>
        <tr>
            <td>
                <b><fmt:message key="book_description"></fmt:message>:</b>
                <c:out value="${books.description}"></c:out>
            </td>
        </tr>
    </table>
</body>
</html>
