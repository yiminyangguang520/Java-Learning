<html lang="en">
<body>
<h2>Order List</h2>

<div> ${orderList}</div>

<br/><br/>
<div>User Info: ${userInfo}</div>
<form action="/logout" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>
</body>
</html>