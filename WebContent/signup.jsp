<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignUp</title>
</head>
<body>
	<div class="form">
		<form action="signupservlet" method="post">
			<label>UserName:</label>
			<input type="text" name="userName">
			<label>Password:</label>
			<input type="password" name="password">
			<label>Email:</label>
			<input type="email" name="email">
			<input type="submit" name="create" value="create">
		</form>
	</div>
</body>
</html>