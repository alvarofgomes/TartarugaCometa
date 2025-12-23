<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Cliente</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
<script>
document.addEventListener('DOMContentLoaded', function() {

    const cpfCnpjInput = document.querySelector('input[name="cpfcnpj"]');
    cpfCnpjInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        
        if (value.length <= 11) {

            value = value.replace(/(\d{3})(\d)/, '$1.$2');
            value = value.replace(/(\d{3})(\d)/, '$1.$2');
            value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
        } else {

            value = value.replace(/^(\d{2})(\d)/, '$1.$2');
            value = value.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
            value = value.replace(/\.(\d{3})(\d)/, '.$1/$2');
            value = value.replace(/(\d{4})(\d)/, '$1-$2');
        }
        e.target.value = value;
    });
    

    const cepInput = document.querySelector('input[name="cep"]');
    cepInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length > 5) {
            value = value.replace(/(\d{5})(\d)/, '$1-$2');
        }
        e.target.value = value.substring(0, 9);
    });
    
    const tipoPessoaSelect = document.getElementById('tipoPessoa');
    if (tipoPessoaSelect) {
        tipoPessoaSelect.addEventListener('change', function() {
            const cpfCnpjField = document.querySelector('input[name="cpfcnpj"]');
            if (this.value === 'FISICA') {
                cpfCnpjField.maxLength = 14;
                cpfCnpjField.placeholder = '000.000.000-00';
            } else {
                cpfCnpjField.maxLength = 18;
                cpfCnpjField.placeholder = '00.000.000/0000-00';
            }
        });
    }
});
</script>
</head>
<body>

    <h2>Cadastro de Cliente</h2>

    <form action="/TartarugaCometa/clienteCadastrar" method="post">

        <h3>Dados do Cliente</h3>
        
        Tipo de Pessoa:
        <select name="tipoPessoa" id="tipoPessoa">
            <option value="FISICA">Pessoa Física</option>
            <option value="JURIDICA">Pessoa Jurídica</option>
        </select><br><br>
        
        Nome/Razão Social:<input type="text" name="nome" maxlength="50" required><br><br>
        CPF/CNPJ:<input type="text" name="cpfcnpj" maxlength="18" placeholder="000.000.000-00 ou 00.000.000/0000-00" required><br><br>

        <h3>Endereço</h3>
        
        Rua:<input type="text" name="rua" maxlength="50"><br><br>
        Número:<input type="text" name="numero" maxlength="15"><br><br>
        Bairro:<input type="text" name="bairro" maxlength="50"><br><br>
        Cidade:<input type="text" name="cidade" maxlength="50"><br><br>
        Estado:<input type="text" name="estado" maxlength="2" placeholder="PE"><br><br>
        CEP:<input type="text" name="cep" maxlength="9" placeholder="00000-000" required><br><br>

        <input type="submit" value="Cadastrar">
    </form>

</body>
</html>