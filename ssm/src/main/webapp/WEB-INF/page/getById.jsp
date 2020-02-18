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

    <div style="width: 45%;padding-top: 15px;padding-bottom: 15px;background: wheat">
        <table width="80%" align="center" border='1'>
            <tr>
                <td width="20%"><font face="华文中宋" size="4">学号</font></td>
                <td width="20%"><font face="华文中宋" size="4">姓名</font></td>
                <td width="20%"><font face="华文中宋" size="4">年龄</font></td>
                <td width="20%"><font face="华文中宋" size="4">专业</font></td>
                <td width="20%"><font face="华文中宋" size="4">详细信息</font></td>
                <td width="10%"><font face="华文中宋" size="4">编辑</font></td>
                <td width="10%"><font face="华文中宋" size="4">删除</font></td>
            </tr>
            <%--只有一条数据时,forEach标签不使用var属性--%>
            <c:forEach items="ls" var="l">
                <tr>
                    <td><font face="华文新魏" size="4">${ls.id}</font></td>
                    <td><font face="华文新魏" size="4">${ls.name}</font></td>
                    <td><font face="华文新魏" size="4">${ls.age}</font></td>
                    <td><font face="华文新魏" size="4">${ls.major}</font></td>
                    <td><font face="华文新魏" size="4">${ls.detail}</font></td>
                    <td><font face="华文新魏" size="4"><a href="./edit?id=${ls.id}">编辑</a></font></td>
                    <td><font face="华文新魏" size="4"><a href="/delete?id=${ls.id}">删除</a></font></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
