<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="cabecalho.jsp"/>

		<div class="login">
			<form method="post" action="logar">
				<p>Nome ou e-mail</p>
				<input type="text" name="nomeOuEmail"/><br>
				<p>Senha</p>
				<input type="password" name="senha"/><br>
				<button type="submit">Login</button>
			</form>
		</div>
		
<jsp:include page="rodape.jsp"/>