<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Entrega</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

	<h2>Cadastro de Entrega</h2>

	<form action="/TartarugaCometa/entregaCadastrar" method="post">
	
	    Status: <input type="text" name="status" maxlength="50" oninput="this.value = this.value.replace(/[0-9]/g, '')" placeholder="pendente-caminho-entregue"><br><br>
	    Frete: <input type="text" name="frete" maxlength="10"><br><br>
	    ID do Cliente: <input type="text" name="id"><br><br>
	
	    <input type="submit" value="Cadastrar">
	
	</form>

</body>
</html>