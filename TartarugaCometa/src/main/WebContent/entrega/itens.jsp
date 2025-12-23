<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<p><strong>Remetente:</strong> ${entrega.remetente.nome}</p>
<p><strong>Destinatário:</strong> ${entrega.destinatario.nome}</p>

<p>
    <strong>Origem:</strong>
    ${entrega.enderecoOrigem.rua}, ${entrega.enderecoOrigem.numero}
    - ${entrega.enderecoOrigem.bairro}
</p>

<p>
    <strong>Destino:</strong>
    ${entrega.enderecoDestino.rua}, ${entrega.enderecoDestino.numero}
    - ${entrega.enderecoDestino.bairro}
</p>

<hr>

<h3>Editar Status da Entrega</h3>

<form action="${pageContext.request.contextPath}/alteraEntrega" method="post">
    <input type="hidden" name="id" value="${entrega.id}">
    <input type="hidden" name="frete" value="${entrega.frete}">

    <select name="status">
        <option value="pendente" ${entrega.status == 'pendente' ? 'selected' : ''}>Pendente</option>
        <option value="caminho" ${entrega.status == 'caminho' ? 'selected' : ''}>Em Caminho</option>
        <option value="entregue" ${entrega.status == 'entregue' ? 'selected' : ''}>Entregue</option>
    </select>

    <input type="submit" value="Atualizar Status">
</form>

<hr>

<h3>Produtos da Entrega</h3>

<c:if test="${empty itens}">
    Nenhum produto adicionado a esta entrega.
</c:if>

<c:if test="${not empty itens}">
<table border="1">
    <tr>
        <th>Produto</th>
        <th>Quantidade</th>
        <th>Ações</th>
    </tr>

    <c:forEach items="${itens}" var="item">
        <tr>
            <td>${item.produto.nomeDoProduto}</td>
            <td>${item.quantidade}</td>
            <td>
                <c:if test="${entrega.status != 'entregue'}">
                    <c:url var="urlRemover" value="/removerItemEntrega">
                        <c:param name="idItem" value="${item.id}" />
                        <c:param name="entregaId" value="${entrega.id}" />
                    </c:url>

                    <a href="${urlRemover}"
                       onclick="return confirm('Remover este produto da entrega?');">
                       Remover
                    </a>
                </c:if>

                <c:if test="${entrega.status == 'entregue'}">
                    <span style="color: gray;">Entrega finalizada</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>

<hr>

<h3>Adicionar Produto à Entrega</h3>

<c:if test="${entrega.status != 'entregue'}">
    <form action="${pageContext.request.contextPath}/cadastrarItensEntrega" method="post">
        <input type="hidden" name="entregaId" value="${entrega.id}">

        Produto (ID):
        <input type="number" name="produtoId" required>

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
