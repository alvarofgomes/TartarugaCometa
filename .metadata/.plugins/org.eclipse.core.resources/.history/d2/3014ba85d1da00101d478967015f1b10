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

        String idEntrega = request.getParameter("id");

        if (idEntrega == null || idEntrega.isEmpty()) {
            throw new RuntimeException("ID da entrega não informado");
        }

        int id = Integer.parseInt(idEntrega);

        entregaBo.deletarEntregaBO(id);

        response.sendRedirect(request.getContextPath() + "/entregaListar");
    }
}