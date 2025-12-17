<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Cliente</title>
<style>
a{text-decoration: none;} 
</style>
</head>
<body>

	<h2>Editar Cliente</h2>
	
	<form action="alteraCliente" method="post">
	    <input type="hidden" name="id" value="${cliente.id}" />
	    
	    Nome: <input type="text" name="nome" value="${cliente.nome}" /><br/>
	    CPF/CNPJ: <input type="text" name="cpfCnpj" value="${cliente.cpfCnpj}" /><br/>
	    <br><br>
	    <input type="submit" value="Salvar Alterações"/>
	</form>
	
	<a href="clienteListar"><input type="submit" value="Voltar para lista" /></a>

</body>
</html>