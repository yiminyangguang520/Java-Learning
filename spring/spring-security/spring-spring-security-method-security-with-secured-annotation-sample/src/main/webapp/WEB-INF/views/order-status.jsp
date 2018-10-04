<html lang="en">
<body>
 <h2>Order Status</h2>
 <p>Status: ${status}</p>
 <br/>
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