<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Entrega</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
<script>
function formatarMoeda(campo) {
    let valor = campo.value.replace(/\D/g, "");

    if (valor === "") {
        campo.value = "";
        return;
    }

    valor = (parseInt(valor, 10) / 100).toFixed(2);
    valor = valor.replace(".", ",");
    valor = valor.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

    campo.value = valor;
}

function atualizarSelecoes() {
    const remetenteId = document.getElementById("remetente").value;
    const destinatarioId = document.getElementById("destinatario").value;

    const enderecoOrigem = document.getElementById("enderecoOrigem");
    const enderecoDestino = document.getElementById("enderecoDestino");

    for (let option of enderecoOrigem.options) {
        if (!option.dataset.cliente) continue;
        option.style.display =
            option.dataset.cliente === remetenteId ? "block" : "none";
    }

    for (let option of enderecoDestino.options) {
        if (!option.dataset.cliente) continue;
        option.style.display =
            option.dataset.cliente === destinatarioId ? "block" : "none";
    }

    const destinatarioSelect = document.getElementById("destinatario");
    for (let option of destinatarioSelect.options) {
        if (!option.value) continue;
        option.style.display =
            option.value === remetenteId ? "none" : "block";
    }

    const remetenteSelect = document.getElementById("remetente");
    for (let option of remetenteSelect.options) {
        if (!option.value) continue;
        option.style.display =
            option.value === destinatarioId ? "none" : "block";
    }
}
</script>
</head>
<body>

    <div class="container">
        <h1>Cadastro de Entrega</h1>
        
        <form action="/TartarugaCometa/entregaCadastrar" method="post">
            
            <input type="hidden" name="status" value="pendente">
            
            <div class="form-group">
                <label for="frete">Valor do Frete:</label>
                <input type="text" id="frete" name="frete" 
                       placeholder="R$ 0,00" 
                       oninput="formatarMoeda(this)" required>
            </div>
            
            <div class="form-row">
                <div class="form-col">
                    <div class="form-group">
                        <label for="remetente">Remetente:</label>
                        <select name="remetenteId" id="remetente" 
                                onchange="atualizarSelecoes()" required>
                            <option value="">Selecione o remetente</option>
                            <c:forEach items="${clientes}" var="c">
                                <option value="${c.id}">${c.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-col">
                    <div class="form-group">
                        <label for="destinatario">Destinatário:</label>
                        <select name="destinatarioId" id="destinatario" 
                                onchange="atualizarSelecoes()" required>
                            <option value="">Selecione o destinatário</option>
                            <c:forEach items="${clientes}" var="c">
                                <option value="${c.id}">${c.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-col">
                    <div class="form-group">
                        <label for="enderecoOrigem">Endereço de Origem:</label>
                        <select name="enderecoOrigemId" id="enderecoOrigem" required>
                            <option value="">Selecione o endereço</option>
                            <c:forEach items="${enderecos}" var="e">
                                <option value="${e.id}" data-cliente="${e.cliente.id}">
                                    ${e.rua}, ${e.numero} - ${e.bairro} (${e.cidade})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-col">
                    <div class="form-group">
                        <label for="enderecoDestino">Endereço de Destino:</label>
                        <select name="enderecoDestinoId" id="enderecoDestino" required>
                            <option value="">Selecione o endereço</option>
                            <c:forEach items="${enderecos}" var="e">
                                <option value="${e.id}" data-cliente="${e.cliente.id}">
                                    ${e.rua}, ${e.numero} - ${e.bairro} (${e.cidade})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <div class="form-group text-center">
                <input type="submit" value="Cadastrar Entrega" class="btn">
                <a href="/TartarugaCometa/index.jsp" class="btn btn-secondary">Cancelar</a>
            </div>
            
        </form>
    </div>

</body>
</html>