<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Produto</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

	<h2>Cadastro de Produto</h2>

	<form action="/TartarugaCometa/produtoCadastrar" method="post">
	
	    Nome do Produto: <input type="text" name="nomeDoProduto"><br><br>
	    Peso: <input type="text" name="peso"><br><br>
	    Volume: <input type="text" name="volume"><br><br>
	    Valor: <input type="text" name="valorfrete"><br><br>
	    ID do Cliente: <input type="text" name="id"><br><br>
	
	    <input type="submit" value="Cadastrar">
	
	</form>

</body>
</html>