package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EntregaBO;
import com.tartarugacometa.model.Entrega;

@WebServlet("/mostraEntrega")
public class MostraEntregaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntregaBO entregaBo = new EntregaBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paramId = request.getParameter("id");

        if (paramId != null && !paramId.isEmpty()) {

            Integer id = Integer.valueOf(paramId);
            Entrega entrega = entregaBo.buscarEntregaPorIdBO(id);

            request.setAttribute("entrega", entrega);
            RequestDispatcher rd = request.getRequestDispatcher("/entrega/formAlteraEntrega.jsp");
            rd.forward(request, response);

        } else {
            response.sendRedirect("/TartarugaCometa/entregaListar");
        }
    }
}