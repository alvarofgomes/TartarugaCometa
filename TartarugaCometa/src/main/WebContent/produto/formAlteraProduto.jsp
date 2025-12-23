<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Produto</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

    <h2>Editar Produto</h2>

    <form action="alteraProduto" method="post">

        <input type="hidden" name="id" value="${produto.id}">

        Nome do Produto:
        <input type="text" name="nomeDoProduto"
               value="${produto.nomeDoProduto}" maxlength="50"
               oninput="this.value = this.value.replace(/[0-9]/g, '')"><br><br>

        Peso:
        <input type="number" step="0.01" name="peso"
               value="${produto.peso}"><br><br>

        Volume:
        <input type="number" step="0.01" name="volume"
               value="${produto.volume}"><br><br>

        Valor do Frete:
        <input type="number" step="0.01" name="valor"
               value="${produto.valor}"><br><br>

        <input type="submit" value="Salvar">
    </form>

    <a href="produtoListar">
        <input type="submit" value="Voltar para lista" />
    </a>

</body>
</html>
