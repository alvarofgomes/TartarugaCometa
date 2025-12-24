<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Endereço</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
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

});
</script>
</head>
<body>

	<h2>Editar Endereço</h2>
	
	<c:if test="${not empty erro}">
	    <p style="color:red">${erro}</p>
	</c:if>
	
	<form action="${pageContext.request.contextPath}/alteraEndereco" method="post">
	    <input type="hidden" name="id" value="${endereco.id}" />
	
	    Rua: <input type="text" name="rua" value="${endereco.rua}" maxlength="50"/><br/>
	    Número: <input type="text" name="numero" value="${endereco.numero}" maxlength="50"/><br/>
	    Bairro: <input type="text" name="bairro" value="${endereco.bairro}" maxlength="50"/><br/>
	    Cidade: <input type="text" name="cidade" value="${endereco.cidade}" maxlength="100"/><br/>
	    Estado: <input type="text" name="estado" value="${endereco.estado}" maxlength="50"/><br/>
	    CEP: <input type="text" name="cep" value="${endereco.cep}" maxlength="9" inputmode="numeric" oninput="this.value = this.value.replace(/\D/g, '')" placeholder="00000-000" required/><br/>
	
	    <input type="submit" value="Salvar Alterações"/>
	</form>

</body>
</html>