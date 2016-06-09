<%@page import="javax.naming.InitialContext,javax.sql.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
InitialContext context=new InitialContext();
DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/TestSQLServer");

Connection con=ds.getConnection();

PreparedStatement ps=con.prepareStatement("select * from product");
ResultSet rs=ps.executeQuery();
if(rs.next()){
	out.println(rs.getInt(1));
	out.println(rs.getString(2));
}


%>



</body>
</html>