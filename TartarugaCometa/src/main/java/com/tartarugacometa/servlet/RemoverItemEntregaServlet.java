package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EntregaBO;
import com.tartarugacometa.BO.ItensEntregasBO;
import com.tartarugacometa.model.Entrega;

@WebServlet("/removerItemEntrega")
public class RemoverItemEntregaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ItensEntregasBO itensBO = new ItensEntregasBO();
    private EntregaBO entregaBO = new EntregaBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idItem = Integer.parseInt(request.getParameter("idItem"));
            int entregaId = Integer.parseInt(request.getParameter("entregaId"));

            Entrega entrega = entregaBO.buscarEntregaPorIdBO(entregaId);

            if ("entregue".equalsIgnoreCase(entrega.getStatus())) {
                response.sendRedirect(
                    request.getContextPath() + "/entregaDetalhe?id=" + entregaId
                );
                return;
            }

            itensBO.removerItem(idItem, entrega);

            response.sendRedirect(
                request.getContextPath() + "/entregaDetalhe?id=" + entregaId
            );

        } catch (Exception e) {
            response.sendRedirect(
                request.getContextPath() + "/entregaListar?erro=removerItem"
            );
        }
    }
}
