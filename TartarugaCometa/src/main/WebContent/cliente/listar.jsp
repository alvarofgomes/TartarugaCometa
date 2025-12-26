<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dados Dos Clientes</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h2>Dados Dos Clientes</h2>

<ul>
<c:forEach items="${clientes}" var="cliente">

    <strong>Cliente:</strong><br>
    Nome: ${cliente.nome}<br>
    
    <c:set var="cpfCnpj" value="${cliente.cpfCnpj}" />
    
    <c:set var="cpfCnpjTemp" value="${fn:replace(cpfCnpj, '.', '')}" />
    <c:set var="cpfCnpjTemp" value="${fn:replace(cpfCnpjTemp, '-', '')}" />
    <c:set var="cpfCnpjTemp" value="${fn:replace(cpfCnpjTemp, '/', '')}" />
    <c:set var="cpfCnpjTemp" value="${fn:replace(cpfCnpjTemp, ' ', '')}" />
    <c:set var="cpfCnpjNumeros" value="${cpfCnpjTemp}" />
    
    CPF/CNPJ: 
    <c:choose>

        <c:when test="${fn:length(cpfCnpjNumeros) == 11}">

            ${fn:substring(cpfCnpjNumeros, 0, 3)}.
            ${fn:substring(cpfCnpjNumeros, 3, 6)}.
            ${fn:substring(cpfCnpjNumeros, 6, 9)}-
            ${fn:substring(cpfCnpjNumeros, 9, 11)}
        </c:when>
        

        <c:when test="${fn:length(cpfCnpjNumeros) == 14}">

            ${fn:substring(cpfCnpjNumeros, 0, 2)}.
            ${fn:substring(cpfCnpjNumeros, 2, 5)}.
            ${fn:substring(cpfCnpjNumeros, 5, 8)}/
            ${fn:substring(cpfCnpjNumeros, 8, 12)}-
            ${fn:substring(cpfCnpjNumeros, 12, 14)}
        </c:when>
        
        <c:when test="${fn:length(cpfCnpjNumeros) == 12}">

            ${fn:substring(cpfCnpjNumeros, 0, 2)}.
            ${fn:substring(cpfCnpjNumeros, 2, 5)}.
            ${fn:substring(cpfCnpjNumeros, 5, 8)}/
            ${fn:substring(cpfCnpjNumeros, 8, 11)}-
            ${fn:substring(cpfCnpjNumeros, 11, 12)}
            <span style="color: red; font-style: italic;"> (CNPJ incompleto)</span>
        </c:when>
        
        <c:otherwise>
            ${cliente.cpfCnpj}
            <c:if test="${fn:length(cpfCnpjNumeros) < 11 or fn:length(cpfCnpjNumeros) > 14}">
                <span style="color: red; font-style: italic;"> (formato inválido: ${fn:length(cpfCnpjNumeros)} dígitos)</span>
            </c:if>
        </c:otherwise>
    </c:choose>
    <br><br>

    <c:choose>
        <c:when test="${empty cliente.enderecos}">
            Cliente sem endereço cadastrado<br>
            
            <a href="${pageContext.request.contextPath}/enderecoCadastrar?clienteId=${cliente.id}">
               Adicionar Endereço
            </a>
            <br><br>
            
        </c:when>

        <c:otherwise>
            <c:forEach items="${cliente.enderecos}" var="endereco">
                <strong>Endereço:</strong><br>
                ${endereco.rua}, nº ${endereco.numero}<br>
                ${endereco.bairro} - ${endereco.cidade}/${endereco.estado}<br>
                
                <c:set var="cep" value="${endereco.cep}" />
                
                <c:set var="cepTemp" value="${fn:replace(cep, '.', '')}" />
                <c:set var="cepTemp" value="${fn:replace(cepTemp, '-', '')}" />
                <c:set var="cepTemp" value="${fn:replace(cepTemp, ' ', '')}" />
                <c:set var="cepNumeros" value="${cepTemp}" />
                
                CEP: 
                <c:choose>
                    <c:when test="${fn:length(cepNumeros) == 8}">

                        ${fn:substring(cepNumeros, 0, 5)}-${fn:substring(cepNumeros, 5, 8)}
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${fn:length(cepNumeros) >= 5}">
                                ${fn:substring(cepNumeros, 0, 5)}-${fn:substring(cepNumeros, 5, fn:length(cepNumeros))}
                            </c:when>
                            <c:otherwise>
                                ${endereco.cep}
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${fn:length(cepNumeros) != 8}">
                            <span style="color: #666; font-style: italic;"> (${fn:length(cepNumeros)} dígitos)</span>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <br>

                <a href="mostraEndereco?id=${endereco.id}">Editar Endereço</a> |
                <a href="removeEndereco?id=${endereco.id}"
                   onclick="return confirm('Remover este endereço?');">
                   Remover Endereço
                </a>
                <br><br>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <a href="mostraCliente?id=${cliente.id}">Editar Cliente</a> |
    <a href="removeCliente?id=${cliente.id}"
       onclick="return confirm('Remover cliente e TODOS os endereços?');">
       Remover Cliente
    </a>

    <hr>
</c:forEach>

</ul>

<br>

<a href="${pageContext.request.contextPath}/clienteCadastrar">
    <input type="button" value="Cadastrar" />
</a>

<br><br>

<a href="${pageContext.request.contextPath}/index.jsp">
    <input type="button" value="Voltar" />
</a>

</body>
</html>