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
		
			<h1>New Product Registration</h1>
		
			<form:form action="${s:mvcUrl('PC#save').build()}" method="post" commandName="product" enctype="multipart/form-data">
				<div class="form-group">
			        <label>Title: </label>
			        <form:input path="title" cssClass="form-control"/>
			        <form:errors path="title"/>
			    </div>
			    <div class="form-group">
			        <label>Description: </label>
			        <form:textarea rows="10" cols="20" path="description" cssClass="form-control"/>
			        <form:errors path="description"/>
			    </div>
			    <div class="form-group">
			        <label>Pages: </label>
			        <form:input path="pages" cssClass="form-control"/>
			        <form:errors path="pages"/>
			    </div>
			    
			    <div class="form-group">
			    	<label>Release Date: </label>
			    	<form:input path="releaseDate" cssClass="form-control"/>
			    	<form:errors path="releaseDate"/>
			    </div>
			    
			    <c:forEach items="${types}" var="priceType" varStatus="status">
			        <div class="form-group">
			            <label>${priceType}</label>
			            <form:input type="text" path="prices[${status.index}].amount" cssClass="form-control"/>
			            <form:input type="hidden" path="prices[${status.index}].type" value="${priceType}" />
			        </div>
			    </c:forEach>
			    
			    <div class="form-group">
			    	<label>Summary</label>
			    	<input name="summary" type="file" class="form-control"/>
			    </div>
			    
			    <button type="submit" class="btn btn-primary">Register</button>
			</form:form>
		</div>
	</body>
</html>