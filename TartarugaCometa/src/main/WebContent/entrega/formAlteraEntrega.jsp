<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Entrega</title>
</head>
<body>

	<h2>Editar Entrega</h2>

	<form action="alteraEntrega" method="post">
	    <input type="hidden" name="id" value="${entrega.id}">
	    <input type="hidden" name="clienteId" value="${clienteId}">
	
	    Status: <input type="text" name="status" value="${entrega.status}"><br>
	    Frete: <input type="number" name="frete" value="${entrega.frete}"><br>
	
	    <input type="submit" value="Salvar">
	</form>

</body>
</html>