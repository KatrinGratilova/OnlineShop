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
<b>Author name: </b> ${product.author}<br>
<b>City: </b> ${product.city}<br>
<hr>
<form action="/products/delete/${product.id}" method="post">
    <input type="submit" value="Delete"/>
</form>
</body>
</html>