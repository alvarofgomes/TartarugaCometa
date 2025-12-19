<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Endereço</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

	<h2>Editar Endereço</h2>
	s
	<c:if test="${not empty erro}">
	    <p style="color:red">${erro}</p>
	</c:if>
	
	<form action="alteraEndereco" method="post">
	    <input type="hidden" name="id" value="${endereco.id}" />
	
	    Rua: <input type="text" name="rua" value="${endereco.rua}" maxlength="50"/><br/>
	    Número: <input type="text" name="numero" value="${endereco.numero}" maxlength="50"/><br/>
	    Bairro: <input type="text" name="bairro" value="${endereco.bairro}" maxlength="50"/><br/>
	    Cidade: <input type="text" name="cidade" value="${endereco.cidade}" maxlength="100"/><br/>
	    Estado: <input type="text" name="estado" value="${endereco.estado}" maxlength="50"/><br/>
	    CEP: <input type="text" name="cep" value="${endereco.cep}" maxlength="8" inputmode="numeric" oninput="this.value = this.value.replace(/\D/g, '')"/><br/>
	
	    <input type="submit" value="Salvar Alterações"/>
	</form>
	
	<a href="enderecoListar"><input type="submit" value="Voltar para lista" /></a>

</body>
</html>
