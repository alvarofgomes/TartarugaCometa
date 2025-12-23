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
    
        Nome do Produto:
        <input type="text" name="nomeDoProduto" maxlength="50"
               oninput="this.value = this.value.replace(/[0-9]/g, '')" required><br><br>

        Peso:
        <input type="number" step="0.01" name="peso" required><br><br>

        Volume:
        <input type="number" step="0.01" name="volume"required><br><br>

        Valor do Frete:
        <input type="number" step="0.01" name="valor"required><br><br>

        <input type="submit" value="Cadastrar">
    </form>

</body>
</html>
