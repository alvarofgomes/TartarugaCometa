<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Endereços</title>
</head>
<body>

<h2>Lista de Endereços</h2>

<c:if test="${empty enderecos}">
    Nenhum endereço cadastrado.
</c:if>

<ul>
    <c:forEach items="${enderecos}" var="endereco">
        <li>
            Cliente:
            ${endereco.cliente.id} - ${endereco.cliente.nome}

            Endereço:
            ${endereco.rua}, nº ${endereco.numero} -
            ${endereco.bairro}, ${endereco.cidade}/${endereco.estado}
            - CEP: ${endereco.cep}
           
            <a href="mostraEndereco?id=${endereco.id}">Editar</a>
            <a href="removeEndereco?id=${endereco.id}">Remover</a>
        </li>
        <hr>
    </c:forEach>
</ul>

<a href="/TartarugaCometa/enderecoCadastrar">
    Cadastrar novo endereço
</a>

</body>
</html>