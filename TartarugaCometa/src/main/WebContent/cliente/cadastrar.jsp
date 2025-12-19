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

    <h3>Dados do Cliente</h3>
    
    Nome:<input type="text" name="nome" maxlength="50" ><br><br>
    CPF/CNPJ:<input type="text" name="cpfcnpj" maxlength="14" inputmode="numeric" oninput="this.value = this.value.replace(/\D/g, '')"><br><br>

    <h3>Endereço</h3>
    
    Rua:<input type="text" name="rua" maxlength="50"><br><br>
    Número:<input type="text" name="numero" maxlength="15"><br><br>
    Bairro:<input type="text" name="bairro" maxlength="50"><br><br>
    Cidade:<input type="text" name="cidade" maxlength="50"><br><br>
    Estado:<input type="text" name="estado" maxlength="50"><br><br>
    CEP:<input type="text" name="cep" maxlength="8" inputmode="numeric" oninput="this.value = this.value.replace(/\D/g, '')"><br><br>

    <input type="submit" value="Cadastrar">
</form>

</body>
</html>