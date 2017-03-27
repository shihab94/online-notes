<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>idex</title>
</head>
<body>
	<div class="form">
		<form action="loginservlet" method="post">
			UserName:<input type="text" name="userName">
			Password:<input type="password" name="password">
			<input type="submit" name="login" value="LogIn">
		</form>
		<form action="signup.jsp" method="post">
			<input type="submit" name="signup" value="SignUp">
		</form>
	</div>
</body>
</html>