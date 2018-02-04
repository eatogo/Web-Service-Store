<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Hello World!</h1>
	<c:if test="${not empty param.name}">
		<p>Name: ${param.name}</p>
	</c:if>
	<fieldset>
		<legend>搜尋各菜式餐點</legend>
		<ul>
			<li><a href="RequestFoodByType.do?food_type=chinese">中式</a></li>
			<li><a href="RequestFoodByType.do?food_type=japanese">日式</a></li>
			<li><a href="RequestFoodByType.do?food_type=american">美式</a></li>
			<li><a href="RequestFoodByType.do?food_type=thailand">泰式</a></li>
			<li><a href="RequestFoodByType.do?food_type=korean">韓式</a></li>
			<li><a href="RequestFoodByType.do?food_type=italian">義式</a></li>
			<li><a href="RequestFoodByType.do?food_type=hongkong">港式</a></li>
			<li><a href="RequestFoodByType.do?food_type=beverage">茶飲類</a></li>
		</ul>
	</fieldset>
</body>
</html>