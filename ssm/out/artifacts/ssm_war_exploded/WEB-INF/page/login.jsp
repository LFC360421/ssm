<%@page pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div align="center" style="background: wheat;width: 35%;margin-left: 30px">
    <div align="center">
        <font face="华文新魏" size="4">使用工号和姓名登录</font>
    </div>
    <br>
    <form action="./login">
        工号：<input type="text" name="id"><br><br>
        姓名：<input type="text" name="name"><br><br>
        <div align="center">
            <input type="submit" value="登录">
        </div>
    </form>
    <br>
    <div align="center">
        <font face="华文新魏" size="4" color="red">${error}</font>
    </div>
</div>

</body>
</html>

