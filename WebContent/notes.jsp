<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if(session.getAttribute("user") == null || session.getAttribute("user") == "")
			response.sendRedirect("index.jsp");
	%>
	<div class="side_bar">
		<h4>Recent-></h4>
		<ol>
			<form action="ViewNotesServlet" method="post">
				<c:forEach items="${notes}" var="item">
					<input type="submit" class="notesButton" name="notes" value="${item.notes}"> <br>
				</c:forEach>
				<input type="hidden" name="tableName" value=<%= session.getAttribute("user") %>>
			</form>
		</ol>
	</div>
	<p id="date"></p>
	<div class="search_bar">
		<form action="" method="get">
			Search:<input type="text" name="search">
			<input type="submit" value="search">
		</form>
	</div>
	<div class="main">
		<form action="savenotesservlet" method="post">
			<textarea rows="10" cols="30" style="font-family: Impact" name="notes"></textarea>
			<input type="submit" name="save" value="save">
			<input type="hidden" name="tableName" value=<%= session.getAttribute("user") %> >
		</form>
	</div>
</body>
</html>