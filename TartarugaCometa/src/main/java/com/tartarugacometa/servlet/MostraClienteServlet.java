package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.model.Cliente;

@WebServlet("/mostraCliente")
public class MostraClienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClienteBO clienteBo = new ClienteBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idCliente = request.getParameter("id");

        if (idCliente != null && !idCliente.isEmpty()) {
            Integer id = Integer.valueOf(idCliente);
            Cliente cliente = clienteBo.buscarClientePorIdBO(id);
            request.setAttribute("cliente", cliente);

            RequestDispatcher rd = request.getRequestDispatcher("/cliente/formAlteraCliente.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("Erro: ID não fornecido.");
        }
    }
}