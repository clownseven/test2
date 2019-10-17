<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>www.michaelweb.com</title>
</head>
<body>
	<%!
	
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://3.112.204.243:3306/fanya";
	public static final String DBUSER = "root";
	public static final String DBPASS = "xusong";%>
	<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		String name = null;
	%>
	<%
		try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			String sql = "SELECT name FROM user WHERE name=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("name"));
			pstmt.setString(2, request.getParameter("password"));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				name = rs.getString(1);
				out.print(name);
				flag = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
	%>
	<%
		if (flag) {
	%>
	<jsp:forward page="validate.jsp">
		<jsp:param name="name" value="<%=name%>" />
	</jsp:forward>
	<%
		} else {
	%>
	<jsp:forward page="first.jsp" />
	<%
		}
	%>
</body>
</html>

