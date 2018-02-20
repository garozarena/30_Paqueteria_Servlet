<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align:center;">
		<form action="../AdminUser" method="POST">
			Nombre: <input type="text" name="user"><br> 
			Contraseña: <input type="password" name="password"><br>
			<input type="submit" value="Enviar">
		</form>
	</div>
</body>
</html>