<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>excle导入导出</title>
</head>
<body>

<a href="./downloadexcel">导出Excel</a>
</br></br></br></br>
<form action="./importexcel" method="post" enctype="multipart/form-data">

<input type="file" name="file">

<input type="submit" value="上传">
</form>
</body>
</html>
