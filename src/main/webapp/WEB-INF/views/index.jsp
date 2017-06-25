<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bem vindo!</title>
		<!-- Incluindo o arquivo de estilos da página que age sobre os elementos em body -->
		<link rel="stylesheet" href="resources/css/default.css">
	</head>
	<body>
		<div class="container">
			<div class="titulo">${titulo}</div>
			<div class="mensagem">${mensagem}</div>
			<div class="autor">${autor}</div>
			<div class="data">${data}</div>
		</div>
	</body>
</html>