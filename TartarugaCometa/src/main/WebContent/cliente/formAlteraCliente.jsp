<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Cliente</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
<script>
document.addEventListener('DOMContentLoaded', function() {

    function detectarTipoPessoa(cpfCnpj) {
        if (!cpfCnpj) return 'FISICA';
        const apenasNumeros = cpfCnpj.replace(/\D/g, '');
        return apenasNumeros.length > 11 ? 'JURIDICA' : 'FISICA';
    }

    function aplicarMascaraCPFCNPJ(value) {
        if (!value) return '';
        const apenasNumeros = value.replace(/\D/g, '');
        
        if (apenasNumeros.length <= 11) {

            return apenasNumeros.replace(/(\d{3})(\d)/, '$1.$2')
                                .replace(/(\d{3})(\d)/, '$1.$2')
                                .replace(/(\d{3})(\d{1,2})$/, '$1-$2');
        } else {

            return apenasNumeros.replace(/^(\d{2})(\d)/, '$1.$2')
                                .replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3')
                                .replace(/\.(\d{3})(\d)/, '.$1/$2')
                                .replace(/(\d{4})(\d)/, '$1-$2');
        }
    }

    const cpfCnpjInput = document.querySelector('input[name="cpfCnpj"]');
    const tipoPessoaSelect = document.getElementById('tipoPessoa');

    if (cpfCnpjInput.value) {
        const tipo = detectarTipoPessoa(cpfCnpjInput.value);
        tipoPessoaSelect.value = tipo;
        
        cpfCnpjInput.value = aplicarMascaraCPFCNPJ(cpfCnpjInput.value);

        if (tipo === 'FISICA') {
            cpfCnpjInput.maxLength = 14;
            cpfCnpjInput.placeholder = '000.000.000-00';
        } else {
            cpfCnpjInput.maxLength = 18;
            cpfCnpjInput.placeholder = '00.000.000/0000-00';
        }
    }

    tipoPessoaSelect.addEventListener('change', function() {
        const valorAtual = cpfCnpjInput.value.replace(/\D/g, '');
        
        if (this.value === 'FISICA') {
            cpfCnpjInput.maxLength = 14;
            cpfCnpjInput.placeholder = '000.000.000-00';

            if (valorAtual.length > 11) {
                cpfCnpjInput.value = aplicarMascaraCPFCNPJ(valorAtual.substring(0, 11));
            }
        } else {
            cpfCnpjInput.maxLength = 18;
            cpfCnpjInput.placeholder = '00.000.000/0000-00';

            cpfCnpjInput.value = aplicarMascaraCPFCNPJ(valorAtual);
        }
    });

    cpfCnpjInput.addEventListener('input', function(e) {
        const cursorPos = e.target.selectionStart;
        const apenasNumeros = e.target.value.replace(/\D/g, '');
        
        if (tipoPessoaSelect.value === 'FISICA') {

            const numerosLimitados = apenasNumeros.substring(0, 11);
            e.target.value = aplicarMascaraCPFCNPJ(numerosLimitados);
            e.target.maxLength = 14;
        } else {

            const numerosLimitados = apenasNumeros.substring(0, 14);
            e.target.value = aplicarMascaraCPFCNPJ(numerosLimitados);
            e.target.maxLength = 18;
        }

        setTimeout(() => {
            e.target.selectionStart = cursorPos;
            e.target.selectionEnd = cursorPos;
        }, 0);
    });
});
</script>
</head>
<body>

    <h2>Editar Cliente</h2>
    
    <form action="alteraCliente" method="post">
        <input type="hidden" name="id" value="${cliente.id}" />
        
        Nome: <input type="text" name="nome" value="${cliente.nome}" maxlength="50" required/><br/><br/>
        
        Tipo de Pessoa:
        <select name="tipoPessoa" id="tipoPessoa">
            <option value="FISICA">Pessoa Física</option>
            <option value="JURIDICA">Pessoa Jurídica</option>
        </select><br/><br/>
        
        CPF/CNPJ: <input type="text" name="cpfCnpj" value="${cliente.cpfCnpj}" placeholder="000.000.000-00 ou 00.000.000/0000-00" required/><br/><br/>
        
        <input type="submit" value="Salvar Alterações"/>
    </form>

</body>
</html>