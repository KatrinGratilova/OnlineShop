<!DOCTYPE html>
<html>
<head>
    <title>ONLINE SHOP</title>
</head>
<body>
<h1>ONLINE SHOP</h1>
<hr>
<h4>Product information</h4>
<#list images as img>
    <img src="/images/${img.id}?if_exists" height="60px"/><br><br>
</#list>
<b>Name: </b> ${product.name}<br>
<b>Description: </b> ${product.description}<br>
<b>Price: </b> ${product.price} $<br>
<b>Author: </b> <a href="/users/${product.user.id}"> ${product.user.name}</a><br>
<b>City: </b> ${product.city}<br>

</body>
</html>