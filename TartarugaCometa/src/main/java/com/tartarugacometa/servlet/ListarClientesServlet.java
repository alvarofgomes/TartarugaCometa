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
import com.tartarugacometa.dao.ClienteDAO;
import com.tartarugacometa.dao.EnderecoDAO;
import com.tartarugacometa.model.Cliente;

@WebServlet("/clienteListar")
public class ListarClientesServlet extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> clientes = clienteDAO.listarClientesComEnderecosDAO();

        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/cliente/listar.jsp").forward(request, response);
    }
}