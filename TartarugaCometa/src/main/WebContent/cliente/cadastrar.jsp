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

    <div class="container">
        <h1>Cadastro de Cliente</h1>
        
        <form action="/TartarugaCometa/clienteCadastrar" method="post">
            
            <h3>Dados do Cliente</h3>
            
            <div class="form-group">
                <label for="tipoPessoa">Tipo de Pessoa:</label>
                <select name="tipoPessoa" id="tipoPessoa" required>
                    <option value="">Selecione...</option>
                    <option value="FISICA">Pessoa Física</option>
                    <option value="JURIDICA">Pessoa Jurídica</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="nome">Nome/Razão Social:</label>
                <input type="text" id="nome" name="nome" maxlength="50" 
                       placeholder="Digite o nome completo" required>
            </div>
            
            <div class="form-group">
                <label for="cpfcnpj">CPF/CNPJ:</label>
                <input type="text" id="cpfcnpj" name="cpfcnpj" maxlength="18" 
                       placeholder="000.000.000-00" required>
            </div>
            
            <h3>Endereço</h3>
            
            <div class="form-row">
                <div class="form-col">
                    <div class="form-group">
                        <label for="rua">Rua:</label>
                        <input type="text" id="rua" name="rua" maxlength="50" 
                               placeholder="Nome da rua">
                    </div>
                </div>
                <div class="form-col">
                    <div class="form-group">
                        <label for="numero">Número:</label>
                        <input type="text" id="numero" name="numero" maxlength="15" 
                               placeholder="Número">
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="bairro">Bairro:</label>
                <input type="text" id="bairro" name="bairro" maxlength="50" 
                       placeholder="Nome do bairro">
            </div>
            
            <div class="form-row">
                <div class="form-col">
                    <div class="form-group">
                        <label for="cidade">Cidade:</label>
                        <input type="text" id="cidade" name="cidade" maxlength="50" 
                               oninput="this.value = this.value.replace(/[^A-Za-zÀ-ÿ\sçÇ]/g, '')"
                               placeholder="Nome da cidade">
                    </div>
                </div>
                <div class="form-col">
                    <div class="form-group">
                        <label for="estado">Estado:</label>
                        <input type="text" id="estado" name="estado" maxlength="2" 
                               oninput="this.value = this.value.replace(/[^A-Za-zÀ-ÿ\sçÇ]/g, '')"
                               placeholder="PE">
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="cep">CEP:</label>
                <input type="text" id="cep" name="cep" maxlength="9" 
                       placeholder="00000-000" required>
            </div>
            
            <div class="form-group text-center">
                <input type="submit" value="Cadastrar Cliente" class="btn">
                <a href="/TartarugaCometa/index.jsp" class="btn btn-secondary">Cancelar</a>
            </div>
            
        </form>
    </div>

</body>
</html>