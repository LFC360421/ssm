<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>报表</title>
</head>
  <body>
	  <div  style="text-align:center">
		   每个地区的销售状况报表 <br><br>
		  <a href="./getBarChart">点击显示柱状图</a><br>
		  <a href="./getPieChart">点击显示饼状图</a><br>
		  <a href="./getLineChart">点击显示曲线图</a>
		 <br><br>
		  某一类商品销售状况报表<br><br>
           <form method="post" action="./getBarChart2">
                    <input type="text" name="goodsId" placeholder="输入商品编号:">
                                     <input type="submit" value="点击显示柱状图">
              </form><br>
             <form action="./getPieChart2" method="post">
                              <input type="text" name="goodsId" placeholder="输入商品编号:">
                              <input type="submit" value="点击显示饼状图">
                          </form><br>
               <form action="./getLineChart2" method="post">
                                <input type="text" name="goodsId" placeholder="输入商品编号:">
                                <input type="submit" value="点击显示曲线图">
                            </form><br>
	  </div>

  </body>
</html>
