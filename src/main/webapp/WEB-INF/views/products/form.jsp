<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Books of Java, Android, iPhone, PHP, Ruby and alot more - Coding House</title>
	</head>
	<body>
		<form action="/coding-house/products" method="post">
		    <div>
		        <label>Title: </label>
		        <input type="text" name="title" />
		    </div>
		    <div>
		        <label>Description: </label>
		        <textarea rows="10" cols="20" name="description"></textarea>
		    </div>
		    <div>
		        <label>Pages: </label>
		        <input type="text" name="pages" />
		    </div>
		    <button type="submit">Register</button>
		</form>
	</body>
</html>