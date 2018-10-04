<html lang="en">
<body>
 <h2>Place Order Form</h2>
 <div>User Info: ${userInfo}</div>
 <br/>
<form action="placeOrder" method="post" >
Item Name:  <input type="text" name="item" />
Quantity: <input type="text" name="quantity" /><br/>
 <input type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"/>
  <input type="submit" value="Submit" />
</form>
<br/><br/>
  <form action="/logout" method="post">
     <input type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"/>
  <input type="submit" value="Logout">
</form>
</body>
</html>