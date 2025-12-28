<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produto Listar</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

<h2>Produtos</h2>

<ul>
    <c:forEach items="${produtos}" var="produto">
        <li>
            <strong>Nome do Produto:</strong> ${produto.nomeDoProduto}<br><br>
            
            <strong>Peso:</strong>
            <fmt:formatNumber value="${produto.peso}"
                              minFractionDigits="3"
                              maxFractionDigits="3" /><br><br>
            
            <strong>Volume:</strong>
            <fmt:formatNumber value="${produto.volume}"
                              minFractionDigits="2"
                              maxFractionDigits="2" /><br><br>
            
            <strong>Valor do Produto:</strong>
            <fmt:formatNumber value="${produto.valor}"
                              type="currency"
                              currencySymbol="R$" /><br><br>
            
            <a href="mostraProduto?id=${produto.id}">Editar</a> |
            <a href="removeProduto?id=${produto.id}"
               onclick="return confirm('Tem certeza que deseja remover este produto?');">
               Remover
            </a>
        </li>
    </c:forEach>
</ul>

<c:if test="${empty produtos}">
    <p>Nenhum produto cadastrado.</p>
</c:if>

<br>

<a href="/TartarugaCometa/produtoCadastrar">
    <input type="submit" value="Cadastrar" />
</a>

<br><br>

<a href="/TartarugaCometa/index.jsp">
    <input type="submit" value="Voltar" />
</a>

</body>
</html>