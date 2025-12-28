<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Endereço</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
document.addEventListener('DOMContentLoaded', function() {
    const cepInput = document.querySelector('input[name="cep"]');
    cepInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length > 5) {
            value = value.replace(/(\d{5})(\d)/, '$1-$2');
        }
        e.target.value = value.substring(0, 9);
    });

    const clienteIdInput = document.querySelector('input[name="clienteId"]');
    if (!clienteIdInput.value) {
        alert('Erro: Cliente não identificado. Voltando para lista de clientes.');
        window.location.href = '${pageContext.request.contextPath}/clienteListar';
    }
});
</script>
</head>
<body>

    <div class="container">
        <h1>Cadastro de Endereço</h1>
        
        <c:if test="${empty param.clienteId and empty clienteId}">
            <div class="alert alert-error">
                Erro: Cliente não identificado. 
                <a href="${pageContext.request.contextPath}/clienteListar">Voltar para lista de clientes</a>
            </div>
        </c:if>
        
        <c:if test="${not empty param.clienteId or not empty clienteId}">
            <form action="${pageContext.request.contextPath}/enderecoCadastrar" method="post">
                
                <input type="hidden" name="clienteId" 
                       value="${not empty param.clienteId ? param.clienteId : clienteId}">
                
                <div class="form-group">
                    <label for="rua">Rua:</label>
                    <input type="text" id="rua" name="rua" maxlength="50" 
                           placeholder="Nome da rua" required>
                </div>
                
                <div class="form-row">
                    <div class="form-col">
                        <div class="form-group">
                            <label for="numero">Número:</label>
                            <input type="text" id="numero" name="numero" maxlength="10" 
                                   placeholder="Número" required>
                        </div>
                    </div>
                    <div class="form-col">
                        <div class="form-group">
                            <label for="bairro">Bairro:</label>
                            <input type="text" id="bairro" name="bairro" maxlength="50" 
                                   placeholder="Nome do bairro" required>
                        </div>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-col">
                        <div class="form-group">
                            <label for="cidade">Cidade:</label>
                            <input type="text" id="cidade" name="cidade" maxlength="50" 
                                   oninput="this.value = this.value.replace(/[^A-Za-zÀ-ÿ\sçÇ]/g, '')"
                                   placeholder="Nome da cidade" required>
                        </div>
                    </div>
                    <div class="form-col">
                        <div class="form-group">
                            <label for="estado">Estado:</label>
                            <input type="text" id="estado" name="estado" maxlength="2" 
                                   oninput="this.value = this.value.replace(/[^A-Za-zÀ-ÿ\sçÇ]/g, '')"
                                   placeholder="PE" required>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="cep">CEP:</label>
                    <input type="text" id="cep" name="cep" maxlength="9" 
                           placeholder="00000-000" required>
                </div>
                
                <div class="form-group text-center">
                    <input type="submit" value="Cadastrar Endereço" class="btn">
                    <a href="${pageContext.request.contextPath}/clienteListar" 
                       class="btn btn-secondary">Cancelar</a>
                </div>
                
            </form>
        </c:if>
    </div>

</body>
</html>