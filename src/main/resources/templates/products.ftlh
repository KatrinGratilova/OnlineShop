<!DOCTYPE html>
<html>
<head>
    <title>ONLINE SHOP</title>
</head>
<body>
<h1> ONLINE SHOP </h1>
<hr>
<#if user.email??>
    <h3>User name: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Log out">
    </form>
<#else>
    <a href="/login">Войти</a></h1>
</#if>
<hr>
<h4>Goods from all over the world</h4>
<form action="/" method="get">
    Search by name: <input type="text" name="name"><br>
    <input type="submit" value="Search"/>
</form>
<#list products as productEntity>
    <div>
        <p><b>${productEntity.name}</b> ${productEntity.price} usd. | <a href="/products/${productEntity.id}">See
                more...</a></p>
    </div>
<#else>
    <h3>There are no products
    </h3>
</#list>
<#if user.email??>
    <hr>
    <h3>Create new good</h3>
    <form action="/products/create" method="post" enctype="multipart/form-data">
        Name: <input type="text" name="name"/><br><br>
        Description: <input type="text" name="description"/><br><br>
        Price: <input type="number" name="price"/><br><br>
        City: <input type="text" name="city"/><br><br>
        First Image: <input type="file" name="file1"/><br><br>
        Second Image: <input type="file" name="file2"/><br><br>
        Third Image: <input type="file" name="file3"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Add good"/>
    </form>
</#if>
</body>
</html>