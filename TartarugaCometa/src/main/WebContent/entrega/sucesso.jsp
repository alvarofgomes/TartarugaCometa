<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Entrega Cadastrada</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

    <h2>Entrega cadastrada com sucesso!</h2>

    <p>Status: ${entrega.status}</p>
    <p>Frete: ${entrega.frete}</p>

    <p>Remetente (ID): ${entrega.remetente.id}</p>
    <p>Destinatário (ID): ${entrega.destinatario.id}</p>

    <p>Endereço Origem (ID): ${entrega.enderecoOrigem.id}</p>
    <p>Endereço Destino (ID): ${entrega.enderecoDestino.id}</p>

    <br>
    <a href="/TartarugaCometa/entregaListar">
        <input type="button" value="Voltar para lista">
    </a>

</body>
</html>
