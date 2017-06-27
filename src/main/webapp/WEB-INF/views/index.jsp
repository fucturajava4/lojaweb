<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bem vindo!</title>
		<!-- Incluindo o arquivo de estilos da p�gina que age sobre os elementos em body -->
		<link rel="stylesheet" href="resources/css/default.css">
	</head>
	<body>
		<div class="container">
			<p class="titulo">${atributos.titulo}</p>
			<p class="mensagem">${atributos.mensagem}</p>
			<p class="autor">${atributos.autor}</p>
			<p class="data">${atributos.data}</p>
		</div>
		<form method="post" action="adicionarUsuario">
			Nome: <input type="text" name="nome"/><br>
			E-mail: <input type="text" name="email"/><br>
			Senha: <input type="password" name="senha"/><br>
			<button type="submit">Cadastrar Usu�rio</button>
		</form>
	</body>
</html>