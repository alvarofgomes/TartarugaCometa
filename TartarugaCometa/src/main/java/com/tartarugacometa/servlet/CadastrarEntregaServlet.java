package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EntregaBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Entrega;

@WebServlet("/entregaCadastrar")
public class CadastrarEntregaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntregaBO entregaBo = new EntregaBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/entrega/cadastrar.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String status = request.getParameter("status");
            status = status.replaceAll("[0-9]", "");
            Double frete = Double.valueOf(request.getParameter("frete"));

            String idCliente = request.getParameter("id");
            int id = Integer.parseInt(idCliente);

            Cliente cliente = new Cliente();
            cliente.setId(id);

            Entrega entrega = new Entrega();
            entrega.setStatus(status);
            entrega.setFrete(frete);
            entrega.setCliente(cliente);

            entregaBo.cadastrarEntregaBO(entrega);

            request.setAttribute("entrega", entrega.getStatus());
            RequestDispatcher rd = request.getRequestDispatcher("/entrega/sucesso.jsp");
            rd.forward(request, response);

        } catch (ValidacaoException e) {
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/entrega/erro.jsp");
            rd.forward(request, response);
        }
    }
}
