<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">



</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="" />

<title>Login</title>
</head>
<body>

<h3>Login</h3>

<form action="login.do" method="POST">
<table>
	<tr>
		<td>ID :</td>
		<td><input type="text" name="username" value="${param.username}" autofocus></td>
		<td>${errorMSG.id}</td>
	</tr>
	<tr>
		<td>PWD : </td>
		<td><input type="text"  name="password" value="${param.password}"></td>
		<td>${errorMSG.pwd}</td>
	</tr>
	<tr>
		<td></td>
		<td align="right"><input type="submit" value="Login"></td>
	</tr>
</table>

</form>
<div>${errorMSG.error}</div>




</body>
</html>