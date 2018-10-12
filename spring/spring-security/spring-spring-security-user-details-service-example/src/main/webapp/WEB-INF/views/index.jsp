<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Security 5</title>
</head>
<body>
<h1>Spring Security - Custom UserDetailsService Example</h1>
<h2>${message}</h2>

<form action="/logout" method="post">
    <input value="Logout" type="submit">
</form>
</body>
</html>