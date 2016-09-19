<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Books of Java, Android, iPhone, PHP, Ruby and alot more - Coding House</title>
	</head>
	<body>
		<h1>List of Books</h1>
		<h3> ${success} </h3>
		<h3> ${fail} </h3>
		<table>
			<tr>
				<td>Title</td>
				<td>Description</td>
				<td>Pages</td>
			</tr>
			
			<c:forEach items="${products}" var="product">
				<tr>
					<td><a href="${s:mvcUrl('PC#details').arg(0, product.id).build()}">${product.title}</a></td>
					<td>${product.description}</td>
					<td>${product.pages}</td>
				</tr>
			</c:forEach>
			
		</table>
	</body>
</html>