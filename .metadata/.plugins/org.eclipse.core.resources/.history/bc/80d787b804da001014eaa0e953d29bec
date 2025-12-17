<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Clientes</title>
</head>
<body>

    <h2>Lista de Clientes</h2>

    <c:if test="${empty clientes}">
        Nenhum cliente cadastrado.
    </c:if>

    <ul>
        <c:forEach items="${clientes}" var="cliente">
            <li>
                ${cliente.nome} - ${cliente.cpfCnpj}
                 <a href="mostraCliente?id=${cliente.id}">Editar</a>
               	 <a href="removeCliente?id=${cliente.id}">Remover</a>
            </li>
        </c:forEach>
    </ul>

    <a href="/TartarugaCometa/clienteCadastrar">
        Cadastrar novo cliente
    </a>

</body>
</html>
