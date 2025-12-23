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
	
	    Status:<input type="text" name="status" required>
	    <br><br>
	    Frete:<input type="number" name="frete" step="0.01" min="0" required>
	    <br><br>
		Remetente (ID Cliente):<input type="number" name="remetenteId" required>
	    <br><br>
	    Destinatário (ID Cliente):<input type="number" name="destinatarioId" required>
	    <br><br>	
	    Endereço Origem (ID):<input type="number" name="enderecoOrigemId" required>
	    <br><br>
	    Endereço Destino (ID):<input type="number" name="enderecoDestinoId" required>
	    <br><br>
	
	    <input type="submit" value="Cadastrar">
	</form>


</body>
</html>
