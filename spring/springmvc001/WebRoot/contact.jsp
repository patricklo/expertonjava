<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'contact.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form:form method="post" action="addContact.do">
    	<table>
    		<tr>
    			<td><form:label path="firstname">FirstName</form:label></td>
    			<td><form:input path="firstname"></form:input></td>
    		</tr>
    		
    		<tr>
    			<td><form:label path="lastname">LastName</form:label></td>
    			<td><form:input path="lastname"></form:input></td>
    		</tr>
    		
    		<tr>
    		<td><form:label path="email">Email</form:label></td>
    			<td><form:input path="email"></form:input></td>
    		</tr>
    		
    		<tr>
    			<td><form:label path="telephone">Telephone</form:label></td>
    			<td><form:input path="telephone"></form:input></td>
    		</tr>
    		
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="Add Contact">
    			</td>
    		</tr>
    	</table>
    </form:form>
  </body>
</html>
