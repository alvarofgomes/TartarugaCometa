package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EntregaBO;

@WebServlet("/removeEntrega")
public class RemoveEntregaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntregaBO entregaBo = new EntregaBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            entregaBo.deletarEntregaBO(id);

            response.sendRedirect(request.getContextPath() + "/entregaListar");

        } catch (Exception e) {
            throw new ServletException("Erro ao remover entrega", e);
        }
    }
}