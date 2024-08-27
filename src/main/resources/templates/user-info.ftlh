<!DOCTYPE html>
<html>
<head>
    <title>ONLINE SHOP</title>
</head>
<body>
<h1>ONLINE SHOP</h1>
<hr>
<h3>More information about user ${user.name}</h3>
<b>Phone number: </b>${user.phoneNumber} <br>
<b>Email: </b>${user.email} <br>

<h4>User's goods ${user.name}</h4>
<#list products as product>
    <div>
        <p><b>${product.name}</b> ${product.price} usd. | <a href="/products/${product.id}">See more...</a></p>
    </div>
<#else>
    <h3> No goods yet</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>