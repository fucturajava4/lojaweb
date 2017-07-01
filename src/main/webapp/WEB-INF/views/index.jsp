<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="cabecalho.jsp"/>

		<div class="container">
			<p class="titulo">${titulo}</p>
			<p class="mensagem">${texto}</p>
			<p class="autor">${autor}</p>
			<p class="data">${data}</p>
		</div>
		<div class="index_opcoes">
			<a href="usuarios"><button>Gerenciar usuários</button></a>
			<a href="relatorio?tipo=acessos"><button>Relatório de acessos</button></a>
			<a href="logout"><button>Logout</button></a>
		</div>
		
<jsp:include page="rodape.jsp"/>