<html lang="en">
<body>
<h2>No Access</h2>
<b style="color:red">User does not have access to perform requested action.</b>
<br/><br/>
<div>User Info: ${userInfo}</div>
<br/>
<br/>
<form action="/logout" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>
</body>
</html>