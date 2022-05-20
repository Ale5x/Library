<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 29.12.2021
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="admin_menu_return_books"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center"><h1><fmt:message key="admin_menu_return_books"></fmt:message></h1></div>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_enter_id"></fmt:message></td>
            <td><input type="number" name="loanCardId" min="1" placeholder="<fmt:message key="placeholder_loan_card_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="enter_user_id"></fmt:message></td>
            <td><input type="number" name="userId" min="1" placeholder="<fmt:message key="placeholder_user_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="book_enter_book_id"></fmt:message></td>
            <td><input type="number" name="bookId" min="1" placeholder="<fmt:message key="placeholder_book_id"></fmt:message>"></td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <td><fmt:message key="loan_card_by_city"></fmt:message></td>
            <td>
                <select name="city">
                    <c:forEach var="library" items="${libraries}">
                        <option value="${library.city}"><c:out value="${library.city}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="<fmt:message key="button_find"></fmt:message>"></td>
        </tr>
    </table>
</form>
<form>
    <input type="hidden" name="command" value="ReturnBookCatalog">
    <table>
        <tr>
            <input type="submit" value="<fmt:message key="loan_card_show_all_loan_books"></fmt:message>" name="getAll">
        </tr>
    </table>
</form>
<div align="center">
    <form>
        <c:if test="${not empty loanCards}">
            <p><fmt:message key="message_count_found_result"></fmt:message><c:out value="${loanCardsSize}"/></p>
        </c:if>
        <c:if test="${not empty loanCards}">
        <table class="table table-hover">
            <c:forEach var="card" items="${loanCards}" varStatus="status">
                <c:if test="${card.loanCardDtoId > 0}">
                    <tr class="tr">
                        <tr>
                            <td>
                                <b>#</b>
                                <c:out value="${status.index + 1}"></c:out>
                            </td>
                        </tr>
                            <tr>
                                <td>
                                    <b><fmt:message key="id"></fmt:message>:</b>
                                    <c:out value="${card.loanCardDtoId}"></c:out>
                                </td>
                            </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="book_id"></fmt:message>:</b>
                                <c:out value="${card.bookId}"></c:out>
                                <a class="btn btn-info" href="?command=CatalogBook&bookId=${card.bookId}"><fmt:message key="details"></fmt:message></a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="user_id"></fmt:message>:</b>
                                <c:out value="${card.userId}"></c:out>
                                <a class="btn btn-info" href="?command=UserCatalog&userId=${card.userId}"><fmt:message key="details"></fmt:message></a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="book_title">:</fmt:message></b>
                                <c:out value="${card.title}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="book_isbn"></fmt:message>:</b>
                                <c:out value="${card.isbn}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="loan_card_taking_date"></fmt:message>:</b>
                                <c:out value="${card.takingBook}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="loan_card_deadline"></fmt:message>:</b>
                                <c:out value="${card.deadline}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="loan_card_return"></fmt:message>:</b>
                                <c:out value="${card.returnBook}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="library_city"></fmt:message>:</b>
                                <c:out value="${card.cityLibrary}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="loan_card_type_use"></fmt:message>:</b>
                                <c:if test="${card.typeUse eq 'READ_ROOM'}">
                                    <fmt:message key="loan_card_type_use_library"></fmt:message>
                                </c:if>
                                <c:if test="${card.typeUse eq 'TAKE_HOME'}">
                                    <fmt:message key="loan_card_type_use_home"></fmt:message>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="status"></fmt:message>:</b>
                                <c:if test="${card.status eq 'OPEN'}">
                                    <fmt:message key="loan_card_status_opened"></fmt:message>
                                </c:if>
                                <c:if test="${card.status eq 'CLOSED'}">
                                    <fmt:message key="loan_card_status_closed"></fmt:message>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key="return_book"></fmt:message>:</b>
                                <a class="btn btn-success" href="?command=ActionReturnBook&loanCardId=${card.loanCardDtoId}"><fmt:message key="return_book_command"></fmt:message></a>
                                <a class="btn btn-danger" href="?command=ActionReturnBook&loanCardId=${card.loanCardDtoId}&violation=yes"><fmt:message key="book_return_with_violation"></fmt:message></a>
                            </td>
                        </tr>
                    </tr>
                </c:if>
            </c:forEach>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>
