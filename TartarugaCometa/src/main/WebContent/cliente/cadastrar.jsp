<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Cliente</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

	<h2>Cadastro de Cliente</h2>

	<form action="/TartarugaCometa/clienteCadastrar" method="post">
	
		Nome: <input type="text" name="nome" maxlength="50" oninput="this.value = this.value.replace(/[0-9]/g, '')"/><br><br>
		CPF/CNPJ: <input type="text" name="cpfcnpj" minlength="11" maxlength="14" inputmode="numeric" oninput="this.value = this.value.replace(/\D/g, '')" /><br><br>

		<input type="submit" value="Cadastrar" />
	</form>

</body>
</html>