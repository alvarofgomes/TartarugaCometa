<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Endereco</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

	<h2>Cadastro de Endereco</h2>
	
	<form action="/TartarugaCometa/enderecoCadastrar" method="post">
	
		Rua: <input type="text" name="rua" maxlength="50"><br><br>
		Numero: <input type="text" name="numero" maxlength="10"><br><br>
		Bairro: <input type="text" name="bairro"maxlength="50"><br><br>
		Cidade: <input type="text" name="cidade"maxlength="50"><br><br>
		Estado: <input type="text" name="estado" maxlength="20"><br><br>
		Cep: <input type="text" name="cep" maxlength="9" inputmode="numeric" oninput="this.value = this.value.replace(/\D/g, '')"><br><br>
		Id: <input type="text" name="id"><br><br>
	
		<input type="submit" value="Cadastrar" />
	
	</form>

</body>
</html>