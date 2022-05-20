<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 23.12.2021
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="user_menu_story_book_user"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="user_menu_story_book_user"></fmt:message></h1></div>
<div align="center"><h3><c:out value="${firstName} ${lastName}"></c:out></h3></div>
<br><br><br>
    <div align="center">
        <form>
            <table class="table table-hover">
                <tr class="thead-dark">
                    <th>#</th>
                    <th><fmt:message key="book_title"></fmt:message></th>
                    <th><fmt:message key="book_isbn"></fmt:message></th>
                    <th><fmt:message key="loan_card_taking_date"></fmt:message></th>
                    <th><fmt:message key="loan_card_deadline"></fmt:message></th>
                    <th><fmt:message key="loan_card_return"></fmt:message></th>
                    <th><fmt:message key="library_city"></fmt:message></th>
                    <th><fmt:message key="loan_card_type_use"></fmt:message></th>
                    <th><fmt:message key="status"></fmt:message></th>
                </tr>
                <c:if test="${not empty loanCards}">
                    <c:forEach var="card" items="${loanCards}" varStatus="status">
                        <tr>
                            <td><c:out value="${status.index + 1}"></c:out></td>
                            <td><c:out value="${card.title}"></c:out></td>
                            <td><c:out value="${card.isbn}"></c:out></td>
                            <td><c:out value="${card.takingBook}"></c:out></td>
                            <td><c:out value="${card.returnBook}"></c:out></td>
                            <td><c:out value="${card.deadline}"></c:out></td>
                            <td><c:out value="${card.cityLibrary}"></c:out></td>
                            <td>
                                <c:if test="${card.typeUse eq 'READ_ROOM'}">
                                    <fmt:message key="loan_card_type_use_library"></fmt:message>
                                </c:if>
                                <c:if test="${card.typeUse eq 'TAKE_HOME'}">
                                    <fmt:message key="loan_card_type_use_home"></fmt:message>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${card.status eq 'OPEN'}">
                                    <fmt:message key="loan_card_status_opened"></fmt:message>
                                </c:if>
                                <c:if test="${card.status eq 'CLOSED'}">
                                    <fmt:message key="loan_card_status_closed"></fmt:message>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </div>
</body>
</html>
