<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Entregas</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

<h2>Entregas</h2>

<ul>
    <c:forEach items="${entregas}" var="entrega">
        <li>
            <strong>Entrega #${entrega.id}</strong><br><br>
            
            <strong>Remetente:</strong> ${entrega.remetente.nome}<br>
            <strong>Destinatário:</strong> ${entrega.destinatario.nome}<br><br>
            
            <strong>Endereço de Origem:</strong><br>
            ${entrega.enderecoOrigem.rua}, ${entrega.enderecoOrigem.numero}<br>
            ${entrega.enderecoOrigem.bairro}<br><br>
            
            <strong>Endereço de Destino:</strong><br>
            ${entrega.enderecoDestino.rua}, ${entrega.enderecoDestino.numero}<br>
            ${entrega.enderecoDestino.bairro}<br><br>
            
            <strong>Status:</strong> ${entrega.status}<br><br>
            
            <c:if test="${entrega.status != 'entregue'}">
                <a href="entregaDetalhe?id=${entrega.id}">
                    Adicionar Produtos
                </a> |
            </c:if>
            <c:if test="${entrega.status == 'entregue'}">
                <span style="color: #49708a;">
                    Produto Entregue 
                </span>
                |
            </c:if>
            <a href="removeEntrega?id=${entrega.id}"
               onclick="return confirm('Tem certeza?');">Remover</a>
        </li>
    </c:forEach>
</ul>

<c:if test="${empty entregas}">
    <p>Nenhuma entrega cadastrada.</p>
</c:if>

<br>

<a href="/TartarugaCometa/entregaCadastrar">
    <input type="submit" value="Cadastrar" />
</a>

<br><br>

<a href="/TartarugaCometa/index.jsp">
    <input type="submit" value="Voltar" />
</a>

</body>
</html>