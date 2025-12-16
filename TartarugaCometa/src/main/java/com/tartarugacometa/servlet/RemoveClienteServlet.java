package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ClienteBO;

@WebServlet("/removeCliente")
public class RemoveClienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClienteBO clienteBo = new ClienteBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            clienteBo.deletarClienteBO(id);

            response.sendRedirect(request.getContextPath() + "/clienteListar");

        } catch (Exception e) {
            throw new ServletException("Erro ao remover cliente", e);
        }
    }
}