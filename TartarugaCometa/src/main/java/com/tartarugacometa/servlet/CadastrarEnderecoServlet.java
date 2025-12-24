package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EnderecoBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;

@WebServlet("/enderecoCadastrar")
public class CadastrarEnderecoServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private EnderecoBO enderecoBo = new EnderecoBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Passa o clienteId para o JSP
        String clienteId = request.getParameter("clienteId");
        if (clienteId != null) {
            request.setAttribute("clienteId", clienteId);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/endereco/cadastrar.jsp");
        rd.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            
            String rua = request.getParameter("rua");
            String numero = request.getParameter("numero");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String cep = request.getParameter("cep");
            cep = cep.replaceAll("\\D", "");
            
            // CORREÇÃO AQUI: pega "clienteId" em vez de "id"
            String clienteIdParam = request.getParameter("clienteId");
            
            if (clienteIdParam == null || clienteIdParam.trim().isEmpty()) {
                throw new ValidacaoException("ID do cliente não informado.");
            }
            
            int clienteId;
            try {
                clienteId = Integer.parseInt(clienteIdParam);
            } catch (NumberFormatException e) {
                throw new ValidacaoException("ID do cliente inválido.");
            }
            
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);
            
            Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
            endereco.setCliente(cliente); 
            
            enderecoBo.cadastrarEnderecoBO(endereco);
            
            request.setAttribute("endereco", endereco.getEstado());

            RequestDispatcher rd = request.getRequestDispatcher("/endereco/sucesso.jsp");
            rd.forward(request, response);

        } catch (ValidacaoException e) {
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/endereco/erro.jsp");
            rd.forward(request, response);
        }
    }
}