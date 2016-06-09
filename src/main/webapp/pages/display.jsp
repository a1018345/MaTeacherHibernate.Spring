<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="" />
<title>Display</title>
<style type="text/css">
#table1 {
	border: 2px solid black;
	border-collapse: collapse;
}

#table1 td {
	border: 2px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>




	<br />
	<%--  <c:choose> --%>
	<%-- <c:when test="${empty selectList}"> --%>



	<h3>Select Product Table Result : 1 row(s) selected</h3>

	<a href="<c:url value="/pages/product.jsp" />">Product Table</a>
	<table id="table1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Make</th>
				<th>Expire</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${product}">
				<c:url var="path" value="/pages/product.jsp">
					<c:param name="id" value="${bean.id}"></c:param>
					<c:param name="name" value="${bean.name}"></c:param>
					<c:param name="price" value="${bean.price}"></c:param>
					<c:param name="make" value="${bean.make}"></c:param>
					<c:param name="expire" value="${bean.expire}"></c:param>
				</c:url>

				<tr>
					<td><a href="${path}">${bean.id}</a></td>
					<td>${bean.name}</td>
					<td>${bean.price}</td>
					<td>${bean.make}</td>
					<td>${bean.expire}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%-- </c:when> --%>
	<%-- <c:otherwise> --%>


	<%-- <h3>Select Product Table Result :${count} row(s) selected</h3> --%>
	<!-- <table id="table1">  -->
	<!-- 	<thead> -->
	<!-- 	<tr> -->
	<!-- 		<th>ID</th> -->
	<!-- 		<th>Name</th> -->
	<!-- 		<th>Price</th> -->
	<!-- 		<th>Make</th> -->
	<!-- 		<th>Expire</th> -->
	<!-- 	</tr> -->
	<!-- 	</thead> -->
	<%-- 	<c:forEach var="list" items="${selectList}" > --%>

	<!-- 	<tbody> -->
	<!-- 	<tr> -->
	<%-- 		<td>${list.id}</td> --%>
	<%-- 		<td>${list.name}</td> --%>
	<%-- 		<td>${list.price}</td> --%>
	<%-- 		<td>${list.make}</td> --%>
	<%-- 		<td>${list.expire}</td> --%>
	<!-- 	</tr> -->
	<!-- 	</tbody> -->
	<%-- 	</c:forEach> --%>

	<!-- </table> -->


	<%-- </c:otherwise> --%>

	<%-- </c:choose> --%>




</body>
</html>