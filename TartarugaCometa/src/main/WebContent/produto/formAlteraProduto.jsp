<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Produto</title>
</head>
<body>

	<h2>Editar Produto</h2>

	<form action="alteraProduto" method="post">

		
    	<input type="hidden" name="id" value="${produto.id}">

	    Nome do Produto:<input type="text" name="nome" value="${produto.nomeDoProduto}"><br>
		Peso:<input type="text" name="peso" value="${produto.peso}"><br>
		Volume:<input type="text" name="volume" value="${produto.volume}"><br>
		Valor:<input type="text" name="valorfrete" value="${produto.valor}"><br>
	
	    <input type="submit" value="Salvar">
	</form>


</body>
</html>