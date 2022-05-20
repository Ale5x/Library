<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 12.12.2021
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="WEB-INF/pages/error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <jstl:redirect url="/Controller?command=GoToMainPage" />

  <br><br><br><br>
  <a href="Controller?command=UserCatalog"><input type="submit" value="Catalog user"></a>

  <br><br><br><br>
  <a href="Controller?command=GoToPrivateRoom"><input type="submit" value="Privaaet room"></a>

  <br><br><br><br>
  <a href="Controller?command=GoToHello"><input type="submit" value="Catalog"></a>

  <br><br><br><br>
  <a href="Controller?command=GoToPersonalCard"><input type="submit" value="Personal card"></a>
  <br><br><br><br>
  <a href="Controller?command=GoToRequestsUser" target="_blank"><input type="submit" value="Requests"></a>
  <br><br><br><br>
  <a href="Controller?command=GoToRequestsCatalog" target="_blank"><input type="submit" value="Requests Catalog"></a>
  </body>
</html>
