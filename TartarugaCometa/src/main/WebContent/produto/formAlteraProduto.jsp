<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Produto</title>
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

// Função para formatar valor inicial quando a página carrega
window.onload = function() {
    // Preenche o campo de nome do produto
    const nomeProduto = document.querySelector('input[name="nomeDoProduto"]');
    nomeProduto.value = "${produto.nomeDoProduto}";
    
    // Preenche e formata o campo de peso
    const pesoVisivel = document.querySelector('input[placeholder="0,000"]');
    const pesoHidden = document.getElementById('peso');
    if ("${produto.peso}") {
        const pesoFormatado = parseFloat("${produto.peso}").toFixed(3).replace(".", ",");
        pesoVisivel.value = pesoFormatado;
        pesoHidden.value = "${produto.peso}";
    }
    
    // Preenche e formata o campo de volume
    const volumeVisivel = document.querySelector('input[placeholder="0,00"]');
    const volumeHidden = document.getElementById('volume');
    if ("${produto.volume}") {
        const volumeFormatado = parseFloat("${produto.volume}").toFixed(2).replace(".", ",");
        volumeVisivel.value = volumeFormatado;
        volumeHidden.value = "${produto.volume}";
    }
    
    // Preenche e formata o campo de valor
    const valorVisivel = document.querySelector('input[placeholder="R$ 0,00"]');
    const valorHidden = document.getElementById('valor');
    if ("${produto.valor}") {
        const valorFormatado = parseFloat("${produto.valor}").toFixed(2).replace(".", ",");
        valorVisivel.value = "R$ " + valorFormatado;
        valorHidden.value = "${produto.valor}";
    }
};
</script>
</head>

<body>

<h2>Editar Produto</h2>

<form action="/TartarugaCometa/alteraProduto" method="post">

    <input type="hidden" name="id" value="${produto.id}">

    <div class="form-group">
        <label for="nomeDoProduto">Nome do Produto:</label>
        <input type="text" id="nomeDoProduto" name="nomeDoProduto" maxlength="50" 
               value="${produto.nomeDoProduto}" required>
    </div>

    <div class="form-group">
        <label for="pesoVisivel">Peso:</label>
        <input type="text" id="pesoVisivel"
               placeholder="0,000"
               oninput="formatarPeso(this, document.getElementById('peso'))" 
               maxlength="7" required>
        <input type="hidden" name="peso" id="peso">
    </div>

    <div class="form-group">
        <label for="volumeVisivel">Volume:</label>
        <input type="text" id="volumeVisivel"
               placeholder="0,00"
               oninput="formatarDecimal(this, document.getElementById('volume'))" 
               maxlength="7" required>
        <input type="hidden" name="volume" id="volume">
    </div>

    <div class="form-group">
        <label for="valorVisivel">Valor do Produto:</label>
        <input type="text" id="valorVisivel"
               placeholder="R$ 0,00"
               oninput="formatarDecimal(this, document.getElementById('valor'))" 
               maxlength="10" required>
        <input type="hidden" name="valor" id="valor">
    </div>

    <input type="submit" value="Atualizar Produto">
    <a href="/TartarugaCometa/produtoListar" style="margin-left: 10px;">
        <input type="button" value="Voltar">
    </a>
</form>

</body>
</html>