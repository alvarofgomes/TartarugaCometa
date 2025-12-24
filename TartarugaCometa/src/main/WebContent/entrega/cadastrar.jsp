<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Entrega</title>
<link rel="stylesheet" href="/TartarugaCometa/css/style.css">
</head>
<body>

    <h2>Cadastro de Entrega</h2>

	<form action="/TartarugaCometa/entregaCadastrar" method="post">
	
		<input type="hidden" name="status" value="pendente">
	    Frete:<input type="number" name="frete" required>
	    <br><br>
		Remetente:
		<select name="remetenteId" required>
		    <option value="">Selecione o remetente</option>
		    <c:forEach items="${clientes}" var="c">
		        <option value="${c.id}">
		            ${c.nome}
		        </option>
		    </c:forEach>
		</select>
	    <br><br>
		Destinatário:
		<select name="destinatarioId" required>
		    <option value="">Selecione o destinatário</option>
		    <c:forEach items="${clientes}" var="c">
		        <option value="${c.id}">
		            ${c.nome}
		        </option>
		    </c:forEach>
		</select>
	    <br><br>	
		Endereço de Origem:
		<select name="enderecoOrigemId" required>
		    <option value="">Selecione o endereço</option>
		    <c:forEach items="${enderecos}" var="e">
		        <option value="${e.id}">
		            ${e.rua}, ${e.numero} - ${e.bairro} (${e.cidade})
		        </option>
		    </c:forEach>
		</select>
	    <br><br>
		Endereço de Destino:
		<select name="enderecoDestinoId" required>
		    <option value="">Selecione o endereço</option>
		    <c:forEach items="${enderecos}" var="e">
		        <option value="${e.id}">
		            ${e.rua}, ${e.numero} - ${e.bairro} (${e.cidade})
		        </option>
		    </c:forEach>
		</select>
	    <br><br>
	
	    <input type="submit" value="Cadastrar">
	</form>


</body>
</html>
