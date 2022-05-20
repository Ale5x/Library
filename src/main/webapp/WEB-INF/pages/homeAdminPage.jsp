<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 25.12.2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="user_menu_admin_page"></fmt:message></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body background="${pageContext.request.contextPath}/images/58652c1422b52.jpg" class="bg_img">
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<br><br>
<div align="center">
    <h1><fmt:message key="user_menu_admin_page"></fmt:message></h1>
</div>
<br>
<table>
    <tr>
        <h3><fmt:message key="message_count_new_order"></fmt:message> - ${ordersSize}</h3>
    </tr>
</table>
<div align="center">
    <table border="1" cellpadding="5">
        <c:if test="${not empty user.role and user.role eq 'MANAGER'}">
            <p>
                <tr>
                    <td><fmt:message key="admin_menu_action_with-admin"></fmt:message></td>
                    <td><a href="Controller?command=ManagerCatalog"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
                </tr>
                <tr>
                    <td><fmt:message key="admin_menu_libraries"></fmt:message></td>
                    <td><a href="Controller?command=LibraryCatalog"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
                </tr>
            </p>
        </c:if>
        <tr>
            <td><fmt:message key="admin_menu_issuing_books"></fmt:message></td>
            <td><a href="Controller?command=GiveOutBookUser"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_return_books"></fmt:message></td>
            <td><a href="Controller?command=ReturnBookCatalog"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_books"></fmt:message></td>
            <td><a href="Controller?command=CatalogBook"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_loan_card"></fmt:message></td>
            <td><a href="Controller?command=LoanCardCatalog"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_user"></fmt:message></td>
            <td><a href="Controller?command=UserCatalog"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_add_book"></fmt:message></td>
            <td><a href="Controller?command=CreateBook"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_catalog_order"></fmt:message></td>
            <td><a href="Controller?command=OrderCatalog"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
        <tr>
            <td><fmt:message key="admin_menu_statistic"></fmt:message></td>
            <td><a href="Controller?command=GoToStatistics"><input type="button" value="<fmt:message key="button_go"></fmt:message>"></a></td>
        </tr>
    </table>
</div>
</body>
</html>
