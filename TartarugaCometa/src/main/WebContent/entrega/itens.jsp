<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Itens da Entrega</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

<h2>Entrega #${entrega.id}</h2>

<p><strong>Status:</strong> ${entrega.status}</p>
<p><strong>Frete:</strong>R$ ${entrega.frete}</p>
<p><strong>Remetente:</strong> ${entrega.remetente.nome}</p>
<p><strong>Destinatário:</strong> ${entrega.destinatario.nome}</p>

<p>
    <strong>Endereço Origem:</strong>
    ${entrega.enderecoOrigem.rua}, ${entrega.enderecoOrigem.numero}
    - ${entrega.enderecoOrigem.bairro}
</p>

<p>
    <strong>Endereço Destino:</strong>
    ${entrega.enderecoDestino.rua}, ${entrega.enderecoDestino.numero}
    - ${entrega.enderecoDestino.bairro}
</p>

<hr>

<c:if test="${entrega.status != 'entregue'}">
    <h3>Editar Status da Entrega</h3>

    <form action="${pageContext.request.contextPath}/alteraEntrega" method="post">
        <input type="hidden" name="id" value="${entrega.id}">
        <input type="hidden" name="frete" value="${entrega.frete}">

        <select name="status">
            <option value="pendente" ${entrega.status == 'pendente' ? 'selected' : ''}>Pendente</option>
            <option value="caminho" ${entrega.status == 'caminho' ? 'selected' : ''}>Em Caminho</option>
            <option value="entregue">Entregue</option>
        </select>

        <input type="submit" value="Atualizar Status">
    </form>
    <hr>
</c:if>

<h3>Produtos da Entrega</h3>

<c:if test="${empty itens}">
    Nenhum produto adicionado a esta entrega.
</c:if>

<c:if test="${not empty itens}">
<table border="1">
<tr>
    <th>Produto</th>
    <th>Peso</th>
    <th>Volume</th>
    <th>Valor do Produto</th>
    <th>Quantidade</th>
    <th>Total do Item</th>
    <th>Ações</th>
</tr>
<c:set var="totalEntrega" value="0" />

<c:forEach items="${itens}" var="item">
<c:set var="totalEntrega"
       value="${totalEntrega + (item.produto.valor * item.quantidade)}" />

    <tr>
        <td>${item.produto.nomeDoProduto}</td>
        <td>${item.produto.peso}</td>
        <td>${item.produto.volume}</td>
        <td>
            R$
            <fmt:formatNumber value="${item.produto.valor}"
                              minFractionDigits="2" />
        </td>
        <td>${item.quantidade}</td>
                <td>
            R$
            <fmt:formatNumber
                value="${item.produto.valor * item.quantidade}"
                minFractionDigits="2" />
        </td>
        
        <td>
            <c:if test="${entrega.status == 'pendente'}">
                <c:url var="urlRemover" value="/removerItemEntrega">
                    <c:param name="idItem" value="${item.id}" />
                    <c:param name="entregaId" value="${entrega.id}" />
                </c:url>
                <a href="${urlRemover}"
                   onclick="return confirm('Remover este produto da entrega?');">
                   Remover
                </a>
            </c:if>
            <c:if test="${entrega.status != 'pendente'}">
                <span style="color: gray;">
                    Remover bloqueado (status: ${entrega.status})
                </span>
            </c:if>
        </td>
    </tr>
</c:forEach>

</table>
</c:if>

<hr>

<c:if test="${entrega.status == 'pendente'}">
    <form action="${pageContext.request.contextPath}/cadastrarItensEntrega" method="post">

        <input type="hidden" name="entregaId" value="${entrega.id}">
        
        <h3>Adicionar Produto à Entrega</h3>

        Produto:
        <select name="produtoId" required>
            <option value="">Selecione um produto</option>

            <c:forEach items="${produtos}" var="produto">
                <option value="${produto.id}">
                    ${produto.nomeDoProduto}
                </option>
            </c:forEach>
        </select>

        Quantidade:
        <input type="number" name="quantidade" min="1" required>

        <input type="submit" value="Adicionar Produto">
    </form>
</c:if>


<c:if test="${entrega.status == 'entregue'}">
    <p style="color:red;"><strong>Entrega concluída. Não é possível alterar produtos.</strong></p>
</c:if>

<br>

<a href="${pageContext.request.contextPath}/entregaListar">
    Voltar para Entregas
</a>

</body>
</html>
