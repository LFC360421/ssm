<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSM 项目</title>
</head>
<body>

<div style=" width: 50%;margin-top: 20px;float: left;background: gray">

<div style="background: wheat;margin: 15px">
<div align="center" style="padding-top: 10px">
    <font face="华文行楷" size="4">教师人数: ${total}</font>
</div>
    <br>
<table width="80%" align="center" border='1' cellspacing="0">
    <tr>
        <td width="20%"><font face="华文中宋" size="4">工号</font></td>
        <td width="20%"><font face="华文中宋" size="4">姓名</font></td>
        <td width="20%"><font face="华文中宋" size="4">年龄</font></td>
        <td width="20%"><font face="华文中宋" size="4">专业</font></td>
        <td width="20%"><font face="华文中宋" size="4">详细信息</font></td>
        <td width="10%"><font face="华文中宋" size="4">编辑</font></td>
        <td width="10%"><font face="华文中宋" size="4">删除</font></td>
    </tr>
    <c:forEach items="${ts}" var="t" varStatus="st">
        <tr>
            <td><font face="华文新魏" size="4">${t.id}</font></td>
            <td><font face="华文新魏" size="4">${t.name}</font></td>
            <td><font face="华文新魏" size="4">${t.age}</font></td>
            <td><font face="华文新魏" size="4">${t.major}</font></td>
            <td><font face="华文新魏" size="4">${t.detail}</font></td>
            <td><font face="华文新魏" size="4"><a href="${path}/ssm/edit?id=${t.id}">编辑</a></font></td>
            <td><font face="华文新魏" size="4"><a href="${path}/ssm/delete?id=${t.id}">删除</a></font></td>
        </tr>
    </c:forEach>
</table>
<div style="padding-bottom: 15px;text-align: center">
    <font face="华文新魏" size="4">
        <a href="?start=0">首页</a>
        <a href="?start=${page.start-page.count}">上一页</a>
        <a href="?start=${page.start+page.count}">下一页</a>
        <a href="?start=${page.last}">末  页</a>
    </font>
</div>
</div>


<div style="width: 40%;margin: 15px;float: left">
<div align="center" style="padding-top: 15px">
    <font face="华文行楷" size="4">添加教师信息</font>
</div>
    <br>
<form action="./add" style="text-align: center">
    <font face="华文新魏" size="4">
        教师工号: <input type="text" name="id" placeholder="id">(请输入7位数字)<br><br>
        教师姓名: <input type="text" name="name" placeholder="name"><br><br>
        教师年龄: <input type="text" name="age" placeholder="age"><br><br>
        所教专业: <input type="text" name="major" placeholder="major"><br><br>
        详细信息: <input type="text" name="detail" placeholder="detail"><br><br>
        <div align="center">
            <input type="submit" value="添加">
        </div>
    </font>
</form>
</div>

<div align="center" style="width: 35%;height: 20%;margin: 15px;float: right">
    <div align="center" style="padding-top: 15px">
        <font face="华文行楷" size="4">根据工号查询</font>
    </div>
    <br>
    <form action="./getById" method="post">
        <input type="text" name="id" placeholder="输入工号:">
        <input type="submit" value="查">
    </form>
    <br>
    <a href="${path}/ssm/excelupload">excle导入导出</a></font></td>
    <br>
     <a href="${path}/ssm/test1">图表</a>

</div>

</div>

</body>
</html>
