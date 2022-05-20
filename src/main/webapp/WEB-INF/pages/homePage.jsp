<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="private_room"></fmt:message></title>
    <jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
    <link rel="stylesheet" href="css/style.css">
</head>
<body background="${pageContext.request.contextPath}/images/58850edb6895f.jpg" class="bg_img">
<br><br>
<div align="center">
    <h1><fmt:message key="title_welcome"></fmt:message> ${user.firstName} ${user.lastName}</h1>
<br>
<c:if test="${user.status eq 'BLOCKED'}">
    <div color="red"><h1><fmt:message key="message_blocked_user"></fmt:message></h1></div>
</c:if>
<br>
    <table border="1" cellpadding="5" class="table_color">
        <c:if test="${not empty user.role and user.role eq 'ADMIN' or user.role eq 'MANAGER'}">
            <p>
                <tr>
                    <td><fmt:message key="user_menu_admin_page"></fmt:message></td>
                    <td><a href="Controller?command=AdminPage"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
                </tr>
            </p>
        </c:if>
        <tr>
            <td><fmt:message key="user_menu_show_catalog_page"></fmt:message></td>
            <td><a href="Controller?command=GoToCatalogByPage"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="user_menu_search_book"></fmt:message></td>
            <td><a href="index.jsp"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="user_menu_story_book_user"></fmt:message></td>
            <td><a href="Controller?command=LoanCardUser"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="user_menu_update_user"></fmt:message></td>
            <td><a href="Controller?command=UpdateUser"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="user_menu_my_order"></fmt:message></td>
            <td><a href="Controller?command=OrderUser"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="user_menu_my_wish_list"></fmt:message></td>
            <td><a href="Controller?command=WishBooksUser"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
    </table>
</div>
</body>
</html>