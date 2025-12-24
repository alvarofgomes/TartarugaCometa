<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Endereco</title>
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

    <h2>Cadastro de Endereço</h2>

    <c:if test="${empty param.clienteId and empty clienteId}">
        <div style="color: red; padding: 10px; border: 1px solid red;">
            Erro: Cliente não identificado. <a href="${pageContext.request.contextPath}/clienteListar">Voltar para lista de clientes</a>
        </div>
    </c:if>
    
    <c:if test="${not empty param.clienteId or not empty clienteId}">
        <form action="${pageContext.request.contextPath}/enderecoCadastrar" method="post">

            <input type="hidden" name="clienteId" 
                   value="${not empty param.clienteId ? param.clienteId : clienteId}">
        
            Rua: <input type="text" name="rua" maxlength="50" required><br><br>
            Número: <input type="text" name="numero" maxlength="10" required><br><br>
            Bairro: <input type="text" name="bairro" maxlength="50" required><br><br>
            Cidade: <input type="text" name="cidade" maxlength="50" required><br><br>
            Estado: <input type="text" name="estado" maxlength="2" placeholder="PE" required><br><br>
            CEP: <input type="text" name="cep" maxlength="9" placeholder="00000-000" required><br><br>
        
            <input type="submit" value="Cadastrar" />
        
        </form>
    </c:if>
    
    <br>
    <a href="${pageContext.request.contextPath}/clienteListar">
        <input type="button" value="Voltar para lista de clientes" />
    </a>

</body>
</html>