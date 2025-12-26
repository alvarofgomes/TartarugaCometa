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

    <c:if test="${empty entregas}">
        Nenhuma entrega cadastrada.
    </c:if>

    <ul>
        <c:forEach items="${entregas}" var="entrega">
            <li>
                <strong>Entrega #${entrega.id}</strong><br>

					Remetente: ${entrega.remetente.nome} <br>
					Destinatário: ${entrega.destinatario.nome} <br>
					
					Endereço de Origem:
					${entrega.enderecoOrigem.rua}, ${entrega.enderecoOrigem.numero}
					- ${entrega.enderecoOrigem.bairro} <br>
					
					Endereço de Destino:
					${entrega.enderecoDestino.rua}, ${entrega.enderecoDestino.numero}
					- ${entrega.enderecoDestino.bairro} <br>


				<c:if test="${entrega.status != 'entregue'}">
				    <a href="entregaDetalhe?id=${entrega.id}">
				        Adicionar Produtos |
				    </a>
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
            <hr>
        </c:forEach>
    </ul>

    <a href="/TartarugaCometa/entregaCadastrar">
        <input type="submit" value="Cadastrar" />
    </a>
	
    <br><br>

    <a href="/TartarugaCometa/index.jsp">
        <input type="submit" value="Voltar" />
    </a>

</body>
</html>
