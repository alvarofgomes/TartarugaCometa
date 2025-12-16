package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;

@WebServlet("/alteraCliente")
public class AlteraClienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClienteBO clienteBo = new ClienteBO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        	
        	String idCliente = request.getParameter("id");

            if (idCliente == null || idCliente.trim().isEmpty()) {
                throw new ValidacaoException("ID do cliente não informado.");
            }

            int id;
            try {
                id = Integer.parseInt(idCliente);
            } catch (Exception e) {
                throw new ValidacaoException("ID do cliente inválido.");
            }
          
            String nome = request.getParameter("nome").trim();
            String cpfCnpj = request.getParameter("cpfCnpj").trim().replaceAll("\\s+", ""); 

            Cliente cliente = new Cliente();
            cliente.setId(id);
            cliente.setNome(nome);
            cliente.setCpfCnpj(cpfCnpj);

            clienteBo.atualizarClienteBO(cliente);

            response.sendRedirect(request.getContextPath() + "/clienteListar");
        } catch (ValidacaoException e) {
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/cliente/formAlteraCliente.jsp");
            rd.forward(request, response);
        }
        
    }
}