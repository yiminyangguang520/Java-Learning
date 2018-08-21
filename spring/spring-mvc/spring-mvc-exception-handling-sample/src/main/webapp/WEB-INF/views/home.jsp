<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Sample Exceptions</h1>
<div id="message1" style="color: red"></div>
<table>
    <thead>
    <tr>
        <td>Action</td>
        <td>Scenario</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><a href="/exception1">Exception 1</a></td>
        <td>Plain HTML text message</td>
    </tr>
    <tr>
        <td><input type="submit" onclick="exception2()" value="Exception 2"/></td>
        <td>Ajax Exception</td>
    </tr>
    <tr>
        <td><a href="/exception3">Exception 3</a></td>
        <td>Exception With Http Status</td>
    </tr>
    </tbody>
</table>

<script src="resources/js/spring-mvc-exception.js"></script>
<script src="resources/js/jquery.js"></script>
</body>
</html>