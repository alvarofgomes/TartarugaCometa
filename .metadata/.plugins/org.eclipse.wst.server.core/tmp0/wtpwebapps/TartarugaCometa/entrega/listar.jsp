<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Entregas Listar</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

	<h2>Entregas</h2>
	
	<c:if test="${empty entregas}">
	    Nenhuma entrega cadastrada.
	</c:if>
	
	<ul>
	    <c:forEach items="${entregas}" var="entrega">
	        <li>
	            Cliente:${entrega.cliente.nome}
	            ID: ${entrega.cliente.id}
	
	            Status:${entrega.status}
	            Frete:${entrega.frete}
	
	            <a href="mostraEntrega?id=${entrega.id}">Editar</a>
	            <a href="removeEntrega?id=${entrega.id}" onclick="return confirm('Tem certeza que deseja remover esta entrega?');">Remover</a>
	        </li>
	        <hr>
	    </c:forEach>
	</ul>

	<a href="/TartarugaCometa/entregaCadastrar"><input type="submit" value="Cadastrar nova Entrega" /></a>
    <br><br>
    <a href="/TartarugaCometa/index.jsp"><input type="submit" value="Voltar para o menu" /></a>
	
</body>
</html>