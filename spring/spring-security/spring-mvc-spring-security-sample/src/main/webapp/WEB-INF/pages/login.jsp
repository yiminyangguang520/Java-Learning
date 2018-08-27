<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head_script.jsp" %>
    <title>登录</title>
</head>
<body>
<%@include file="common/header.jsp" %>

<div>
    <h1>登录：</h1>
    <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
        <dl>
            <dt>登录名:</dt>
            <dd><label><input name="j_username" type="text"></label></dd>
            <dt>密码:</dt>
            <dd><label><input name="j_password" type="password"></label></dd>
        </dl>
        <button type="submit" class="btn btn-default">登录</button>
    </form>
</div>

</body>
<%@include file="common/footer.jsp" %>
</html>
