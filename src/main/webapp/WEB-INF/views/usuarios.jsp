<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="cabecalho.jsp"/>

		<button id="cadastrar_usuario" onclick="cadastrarUsuario();">Cadastrar</button>
		<div class="usuarios">
			<c:forEach var="usuario" items="${usuarios}">
				<div class="usuario">
					<form action="atualizarUsuario" method="post">
						<div class="form-atualizar-usuario-content">
							<input type="hidden" name="id" value="${usuario.id}">
							<p>Nome</p>
							<input type="text" name="nome" value="${usuario.nome}">
							<p>E-mail</p>
							<input type="text" name="email" value="${usuario.email}">
							<p>Senha</p>
							<input type="password" name="senha" value="${usuario.senha}">
						</div>
						<div class="form-atualizar-usuario-action">
							<a href="removerUsuario?id=${usuario.id}"><button type="button">Remover</button></a>
							<button type="submit">Atualizar</button>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
		<div id="form_cadastrar_usuario" title="Cadastrar novo usuário" style="display:none;">
			<form method="post" action="adicionarUsuario">
				<p>Nome</p>
				<input type="text" name="nome">
				<p>E-mail</p>
				<input type="text" name="email">
				<p>Senha</p>
				<input type="password" name="senha">
			</form>
		</div>
		
<jsp:include page="rodape.jsp"/>