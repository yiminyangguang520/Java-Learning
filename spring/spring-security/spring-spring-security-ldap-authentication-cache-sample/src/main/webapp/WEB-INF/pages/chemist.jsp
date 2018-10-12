<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring Security LDAP Authentication Demo - jCombat</title>
</head>
<body>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<center>
    <h3>Spring Security LDAP Authentication Authorization Demo - jCombat</h3>
    <h2>Hello ${username}, you are now logged in!</h2>
    <p>We know you are doing a great job as a <strong>Chemist</strong></p>
</center>
</body>
</html>