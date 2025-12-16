<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produto Listar</title>
</head>
<body>

	<h2>Produtos</h2>

	<c:if test="${empty produtos}">
	    Nenhum produto cadastrado.
	</c:if>

	<ul>
	    <c:forEach items="${produtos}" var="produto">
	        <li>
			
	           Nome Do Produto:${produto.nomeDoProduto}
	           Peso:${produto.peso}
	           Volume:${produto.volume}
	           Valor:${produto.valor}
	           
	           <a href="mostraProduto?id=${produto.id}">Editar</a>
	           <a href="removeProduto?id=${produto.id}">Remover</a>
	           
	        </li>
	        <hr>
	    </c:forEach>
	</ul>

	<a href="/TartarugaCometa/produtoCadastrar">Novo Produto</a>

</body>
</html>