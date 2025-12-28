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
    campoVisivel.value = numero.replace(".", ",")
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
    campoVisivel.value = numero.replace(".", ",")
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

    <div class="container">
        <h1>Cadastro de Produto</h1>
        
        <form action="/TartarugaCometa/produtoCadastrar" method="post">
            
            <div class="form-group">
                <label for="nomeDoProduto">Nome do Produto:</label>
                <input type="text" id="nomeDoProduto" name="nomeDoProduto" 
                       maxlength="50" placeholder="Digite o nome do produto" required>
            </div>
            
            <div class="form-row">
                <div class="form-col">
                    <div class="form-group">
                        <label for="pesoVisivel">Peso:</label>
                        <input type="text" id="pesoVisivel"
                               placeholder="0,000"
                               oninput="formatarPeso(this, document.getElementById('peso'))" 
                               maxlength="7" required>
                        <input type="hidden" name="peso" id="peso">
                    </div>
                </div>
                <div class="form-col">
                    <div class="form-group">
                        <label for="volumeVisivel">Volume:</label>
                        <input type="text" id="volumeVisivel"
                               placeholder="0,00"
                               oninput="formatarDecimal(this, document.getElementById('volume'))" 
                               maxlength="7" required>
                        <input type="hidden" name="volume" id="volume">
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="valorVisivel">Valor do Produto:</label>
                <input type="text" id="valorVisivel"
                       placeholder="R$ 0,00"
                       oninput="formatarMoeda(this, document.getElementById('valor'))" 
                       maxlength="10" required>
                <input type="hidden" name="valor" id="valor">
            </div>
            
            <div class="form-group text-center">
                <input type="submit" value="Cadastrar Produto" class="btn">
                <a href="/TartarugaCometa/index.jsp" class="btn btn-secondary">Cancelar</a>
            </div>
            
        </form>
    </div>

</body>
</html>