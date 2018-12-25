<%--
  Created by IntelliJ IDEA.
  User: bohdan
  Date: 25/12/18
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" class="ua.nure.kn.dudka.usermanagment.User" scope="session"/>

<html>
<head>
    <title>User management/ Details</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/details" method="post">
    <p>ID: ${user.id}</p>
    <p>First name: ${user.firstName}</p>
    <p>Last name: ${user.lastName}</p>
    <p>Date of Birth: ${user.dateOfBirth}</p>
    <input type="submit" name="backButton" value="Back">
</form>
</body>
</html>
