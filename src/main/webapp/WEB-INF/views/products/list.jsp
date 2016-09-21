<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<c:url value="/" var="contextPath" />
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css" />
		<script src="${contextPath}resources/js/bootstrap.min.js"></script>
		
		<title>Books of Java, Android, iPhone, PHP, Ruby and alot more - Coding House</title>
	</head>
	<body>
		<nav class="navbar navbar-inverse">
		  <div class="container">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Coding House</a>
		    </div>
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li><a href="${s:mvcUrl('PC#list').build()}">List of Products</a></li>
    			<li><a href="${s:mvcUrl('PC#form').build()}">new Product</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div>
		</nav>
	
		<div class="container">
			<h1>List of Books</h1>
			<h3> ${success} </h3>
			<h3> ${fail} </h3>
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Pages</th>
				</tr>
				
				<c:forEach items="${products}" var="product">
					<tr>
						<td><a href="${s:mvcUrl('PC#details').arg(0, product.id).build()}">${product.title}</a></td>
						<td>${product.description}</td>
						<td>${product.pages}</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
	</body>
</html>