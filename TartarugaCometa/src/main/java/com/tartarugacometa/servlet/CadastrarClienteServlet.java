package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.BO.EnderecoBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;

//http://localhost:8080/TartarugaCometa/clienteCadastrar
@WebServlet("/clienteCadastrar")
public class CadastrarClienteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private ClienteBO clienteBo = new ClienteBO();
	private EnderecoBO endercoBo = new EnderecoBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/cliente/cadastrar.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    try {

	        String nome = request.getParameter("nome");
	        nome = nome.replaceAll("[0-9]", "");

	        String cpfcnpj = request.getParameter("cpfcnpj");
	        cpfcnpj = cpfcnpj.replaceAll("\\D", "");

	        Cliente cliente = new Cliente(nome, cpfcnpj);
	        clienteBo.cadastrarClienteBO(cliente); 

	        String rua = request.getParameter("rua");
	        String numero = request.getParameter("numero");
	        String bairro = request.getParameter("bairro");
	        String cidade = request.getParameter("cidade");
	        String estado = request.getParameter("estado");
	        String cep = request.getParameter("cep");
	        cep = cep.replaceAll("\\D", "");

	        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
	        endereco.setCliente(cliente);

	        endercoBo.cadastrarEnderecoBO(endereco);

	        RequestDispatcher rd = request.getRequestDispatcher("/cliente/sucesso.jsp");
	        rd.forward(request, response);

	    } catch (ValidacaoException e) {
	        request.setAttribute("erro", e.getMessage());
	        RequestDispatcher rd = request.getRequestDispatcher("/cliente/erro.jsp");
	        rd.forward(request, response);
	    }
	}

	
}