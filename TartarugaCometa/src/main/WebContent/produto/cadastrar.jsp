<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Produto</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
<script>
function formatarMoeda(campoVisivel, campoHidden) {
    let valor = campoVisivel.value.replace(/\D/g, "");

    if (valor === "") {
        campoVisivel.value = "";
        campoHidden.value = "";
        return;
    }

    let numero = (parseInt(valor, 10) / 100).toFixed(2);

    campoHidden.value = numero;

    campoVisivel.value = numero
        .replace(".", ",")
        .replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function formatarDecimal(campoVisivel, campoHidden) {
    let valor = campoVisivel.value.replace(/\D/g, "");

    if (valor === "") {
        campoVisivel.value = "";
        campoHidden.value = "";
        return;
    }

    let numero = (parseInt(valor, 10) / 100).toFixed(2);

    campoHidden.value = numero;

    campoVisivel.value = numero
        .replace(".", ",")
        .replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function formatarDecimal3(campoVisivel, campoHidden) {
    let valor = campoVisivel.value.replace(/\D/g, "");

    if (valor === "") {
        campoVisivel.value = "";
        campoHidden.value = "";
        return;
    }

    let numero = (parseInt(valor, 10) / 1000).toFixed(3);

    campoHidden.value = numero;

    campoVisivel.value = numero
        .replace(".", ",")
        .replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}
function formatarPeso(campoVisivel, campoHidden) {
    let valor = campoVisivel.value.replace(/\D/g, "");

    if (valor === "") {
        campoVisivel.value = "";
        campoHidden.value = "";
        return;
    }

    let numero = (parseInt(valor, 10) / 1000).toFixed(3);

    campoHidden.value = numero;

    campoVisivel.value = numero.replace(".", ",");
}
</script>
</head>
<body>

    <h2>Cadastro de Produto</h2>

<form action="/TartarugaCometa/produtoCadastrar" method="post">

    Nome do Produto:
    <input type="text" name="nomeDoProduto" maxlength="50" required>
    <br><br>

	Peso:
<input type="text"
       placeholder="0,000"
       oninput="formatarPeso(this, document.getElementById('peso'))"
       required>

<input type="hidden" name="peso" id="peso">


    <br><br>

	Volume:
	<input type="text"
	       placeholder="0,00"
	       oninput="formatarDecimal(this, document.getElementById('volume'))"
	       required>
	
	<input type="hidden" name="volume" id="volume">

    <br><br>

    Valor do Produto:
    <input type="text"
           placeholder="R$ 0,00"
           oninput="formatarDecimal(this, document.getElementById('valor'))"
           required>
    <input type="hidden" name="valor" id="valor">
    <br><br>

    <input type="submit" value="Cadastrar">
</form>


</body>
</html>
