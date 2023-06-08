<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/ensyu/LoginServlet" method="post">
	<input type="email" name="email" placeholder="info@example.com"><br>
	<input type="password" name="pass" placeholder="パスワードを入力"><br>
	<input type="submit" name="email" value="ログイン">
</form>
</body>
</html>