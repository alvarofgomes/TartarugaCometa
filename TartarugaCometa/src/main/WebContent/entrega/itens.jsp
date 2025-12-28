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

<div style="background-color: #ffffff; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
    <p><strong>Status:</strong> ${entrega.status}</p>
    <p><strong>Frete:</strong> R$ ${entrega.frete}</p>
    <p><strong>Remetente:</strong> ${entrega.remetente.nome}</p>
    <p><strong>Destinatário:</strong> ${entrega.destinatario.nome}</p>
    
    <p><strong>Endereço Origem:</strong><br>
    ${entrega.enderecoOrigem.rua}, ${entrega.enderecoOrigem.numero}<br>
    ${entrega.enderecoOrigem.bairro}</p>
    
    <p><strong>Endereço Destino:</strong><br>
    ${entrega.enderecoDestino.rua}, ${entrega.enderecoDestino.numero}<br>
    ${entrega.enderecoDestino.bairro}</p>
</div>

<c:if test="${entrega.status != 'entregue'}">
    <h3>Editar Status da Entrega</h3>
    
    <form action="${pageContext.request.contextPath}/alteraEntrega" method="post">
        <input type="hidden" name="id" value="${entrega.id}">
        <input type="hidden" name="frete" value="${entrega.frete}">
        
        <div class="form-group">
            <label for="status">Status:</label>
            <select name="status" id="status" required>
                <option value="pendente" ${entrega.status == 'pendente' ? 'selected' : ''}>Pendente</option>
                <option value="caminho" ${entrega.status == 'caminho' ? 'selected' : ''}>Em Caminho</option>
                <option value="entregue">Entregue</option>
            </select>
        </div>
        
        <input type="submit" value="Atualizar Status">
    </form>
</c:if>

<h3>Produtos da Entrega</h3>

<c:if test="${empty itens}">
    <p>Nenhum produto adicionado a esta entrega.</p>
</c:if>

<c:if test="${not empty itens}">
    <div style="background-color: #ffffff; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
        <table style="width: 100%; border-collapse: collapse;">
            <tr style="background-color: #f2f2f2;">
                <th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Produto</th>
                <th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Peso</th>
                <th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Volume</th>
                <th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Valor do Produto</th>
                <th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Quantidade</th>
                <th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Total do Item</th>
                <th style="padding: 8px; text-align: left; border: 1px solid #ddd;">Ações</th>
            </tr>
            
            <c:set var="totalEntrega" value="0" />
            
            <c:forEach items="${itens}" var="item">
                <c:set var="totalEntrega"
                       value="${totalEntrega + (item.produto.valor * item.quantidade)}" />
                
                <tr>
                    <td style="padding: 8px; border: 1px solid #ddd;">${item.produto.nomeDoProduto}</td>
                    <td style="padding: 8px; border: 1px solid #ddd;">${item.produto.peso}</td>
                    <td style="padding: 8px; border: 1px solid #ddd;">${item.produto.volume}</td>
                    <td style="padding: 8px; border: 1px solid #ddd;">
                        R$ <fmt:formatNumber value="${item.produto.valor}" minFractionDigits="2" />
                    </td>
                    <td style="padding: 8px; border: 1px solid #ddd;">${item.quantidade}</td>
                    <td style="padding: 8px; border: 1px solid #ddd;">
                        R$ <fmt:formatNumber value="${item.produto.valor * item.quantidade}" minFractionDigits="2" />
                    </td>
                    <td style="padding: 8px; border: 1px solid #ddd;">
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
    </div>
</c:if>

<c:if test="${entrega.status == 'pendente'}">
    <h3>Adicionar Produto à Entrega</h3>
    
    <form action="${pageContext.request.contextPath}/cadastrarItensEntrega" method="post">
        <input type="hidden" name="entregaId" value="${entrega.id}">
        
        <div class="form-row">
            <div class="form-col">
                <div class="form-group">
                    <label for="produtoId">Produto:</label>
                    <select name="produtoId" id="produtoId" required>
                        <option value="">Selecione um produto</option>
                        <c:forEach items="${produtos}" var="produto">
                            <option value="${produto.id}">${produto.nomeDoProduto}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-col">
                <div class="form-group">
                    <label for="quantidade">Quantidade:</label>
                    <input type="number" name="quantidade" id="quantidade" min="1" required>
                </div>
            </div>
        </div>
        
        <input type="submit" value="Adicionar Produto">
    </form>
</c:if>

<c:if test="${entrega.status == 'entregue'}">
    <div style="background-color: #ffebee; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
        <p style="color: #d32f2f;"><strong>Entrega concluída. Não é possível alterar produtos.</strong></p>
    </div>
</c:if>

<br>

<a href="${pageContext.request.contextPath}/entregaListar">
    <input type="button" value="Voltar para Entregas" />
</a>

</body>
</html>