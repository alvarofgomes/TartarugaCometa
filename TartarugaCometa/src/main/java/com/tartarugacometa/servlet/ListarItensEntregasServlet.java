package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EntregaBO;
import com.tartarugacometa.BO.ItensEntregasBO;
import com.tartarugacometa.model.Entrega;
import com.tartarugacometa.model.ItensEntregas;
import com.tartarugacometa.model.Produto;

@WebServlet("/entregaDetalhe")
public class ListarItensEntregasServlet extends HttpServlet {

    private ItensEntregasBO itensBo = new ItensEntregasBO();
    private EntregaBO entregaBo = new EntregaBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/entregaListar");
            return;
        }

        int entregaId = Integer.parseInt(idParam);

        request.setAttribute("entrega", entregaBo.buscarEntregaPorIdBO(entregaId));
        request.setAttribute("itens", itensBo.listarPorEntrega(entregaId));

        request.getRequestDispatcher("/entrega/itens.jsp")
               .forward(request, response);
    }
}
