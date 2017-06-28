<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login</title>
		<!-- Incluindo o arquivo de estilos da página que age sobre os elementos em body -->
		<link rel="stylesheet" href="resources/css/default.css">
	</head>
	<body>
		<form method="post" action="logar">
			Login
			Nome: <input type="text" name="email"/><br>
			Senha: <input type="password" name="senha"/><br>
			<button type="submit">Entrar</button>
		</form>
	</body>
</html>