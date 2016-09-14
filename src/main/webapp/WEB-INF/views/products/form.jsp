<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Books of Java, Android, iPhone, PHP, Ruby and alot more - Coding House</title>
	</head>
	<body>
		<form:form action="${s:mvcUrl('PC#save').build()}" method="post" commandName="product">
		    <div>
		        <label>Title: </label>
		        <form:input path="title" />
		        <form:errors path="title"/>
		    </div>
		    <div>
		        <label>Description: </label>
		        <form:textarea rows="10" cols="20" path="description" />
		        <form:errors path="description"/>
		    </div>
		    <div>
		        <label>Pages: </label>
		        <form:input path="pages" />
		        <form:errors path="pages"/>
		    </div>
		    
		    <div>
		    	<label>Release Date: </label>
		    	<form:input path="releaseDate" />
		    	<form:errors path="releaseDate"/>
		    </div>
		    
		    <c:forEach items="${types}" var="priceType" varStatus="status">
		        <div>
		            <label>${priceType}</label>
		            <form:input type="text" path="prices[${status.index}].amount" />
		            <form:input type="hidden" path="prices[${status.index}].type" value="${priceType}" />
		        </div>
		    </c:forEach>
		    
		    <button type="submit">Register</button>
		</form:form>
	</body>
</html>