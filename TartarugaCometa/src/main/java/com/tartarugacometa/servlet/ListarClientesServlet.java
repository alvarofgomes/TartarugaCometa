package com.tartarugacometa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.model.Cliente;

@WebServlet("/clienteListar")
public class ListarClientesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteBO clienteBo = new ClienteBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> clientes = clienteBo.listarClientesBO();

        request.setAttribute("clientes", clientes);
        RequestDispatcher rd = request.getRequestDispatcher("/cliente/listar.jsp");
        rd.forward(request, response);
    }
}