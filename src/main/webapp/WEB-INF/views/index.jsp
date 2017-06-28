<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<p class="titulo">${atributos.titulo}</p>
			<p class="mensagem">${atributos.mensagem}</p>
			<p class="autor">${atributos.autor}</p>
			<p class="data">${atributos.data}</p>
			<p class="mensagem_do_controller">${mensagem}</p>
		</div>
		<div id="form_cadastrar_usuario">
			<form method="post" action="adicionarUsuario">
				Nome: <input type="text" name="nome"/><br>
				E-mail: <input type="text" name="email"/><br>
				Senha: <input type="password" name="senha"/><br>
				<button type="submit">Cadastrar Usuário</button>
			</form>
		</div>
		<div id="form_exibir_usuarios">
			<table>
				<tbody>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>E-mail</th>
						<th>Senha</th>
						<th>Ação</th>
					</tr>
					<c:forEach var="usuario" items="${lista_de_usuarios}">
						<tr>
							<form method="post" action="atualizarUsuario">
								<input type="hidden" name="id" value="${usuario.id}">
								<td>${usuario.id}</td>
								<td><input type="text" name="nome" value="${usuario.nome}"></td>
								<td><input type="text" name="email" value="${usuario.email}"></td>
								<td><input type="password" name="senha" value="${usuario.senha}"></td>
								<td>
									<button type="submit">Atualizar</button>
									<a href="removerUsuario?id=${usuario.id}"><button type="button">Remover</button></a>
								</td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>