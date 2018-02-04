<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>依餐點種類搜尋結果</title>
</head>
<body>
	<h1>${param.food_type}搜尋結果</h1>
	<c:choose>
		<c:when test="${not empty errorMessage}">
			<p>${errorMessage.zeroFoodResult}</p>
		</c:when>
		<c:otherwise>
			<p>${result}</p>
		</c:otherwise>
	</c:choose>
</body>
</html>