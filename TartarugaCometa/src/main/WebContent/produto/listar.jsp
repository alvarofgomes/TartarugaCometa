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

    <c:if test="${empty produtos}">
        Nenhum produto cadastrado.
    </c:if>

    <ul>
        <c:forEach items="${produtos}" var="produto">
<li>
    Nome do Produto: ${produto.nomeDoProduto}<br>

    Peso:
    <fmt:formatNumber value="${produto.peso}"
                      minFractionDigits="3"
                      maxFractionDigits="3" /><br>

    Volume:
    <fmt:formatNumber value="${produto.volume}"
                      minFractionDigits="2"
                      maxFractionDigits="2" /><br>

    Valor do Produto:
    <fmt:formatNumber value="${produto.valor}"
                      type="currency"
                      currencySymbol="R$" /><br>

    <a href="mostraProduto?id=${produto.id}">Editar</a>
    |
    <a href="removeProduto?id=${produto.id}"
       onclick="return confirm('Tem certeza que deseja remover este produto?');">
       Remover
    </a>
</li>
            <hr>
        </c:forEach>
    </ul>

    <a href="/TartarugaCometa/produtoCadastrar">
        <input type="submit" value="Cadastrar" />
    </a>
    <br><br>
    <a href="/TartarugaCometa/index.jsp">
        <input type="submit" value="Voltar" />
    </a>

</body>
</html>
