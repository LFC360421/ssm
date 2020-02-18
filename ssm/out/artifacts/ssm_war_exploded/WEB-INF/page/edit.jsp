<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<div align="center" style="background: gray;float: none;width: 30%">

    <div align="center">
        <font face="华文行楷" size="4">修改教师信息</font>
    </div>
    <br>
    <form method="get" action="./update">
        <font face="华文新魏" size="4">
            教师工号: <input name="id" value="${t.id}" type="text"><br><br>
            教师姓名: <input name="name" value="${t.name}" type="text"> <br><br>
            教师年龄: <input name="age" value="${t.age}" type="text"> <br><br>
            所教专业: <input name="major" value="${t.major}" type="text"> <br><br>
            详细信息: <input name="detail" value="${t.detail}" type="text"> <br><br>
        </font>
        <div align="center">
            <input type="submit" value="编辑">
        </div>
    </form>
</div>

</body>
</html>
