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
import com.tartarugacometa.model.Entrega;

@WebServlet("/alteraEntrega")
public class AlteraEntregaServlet extends HttpServlet {

    private EntregaBO entregaBo = new EntregaBO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	try {
            String idEntrega = request.getParameter("id");

            if (idEntrega == null || idEntrega.trim().isEmpty()) {
                throw new ValidacaoException("ID da entrega não informado.");
            }

            int id;
            try {
                id = Integer.parseInt(idEntrega);
            } catch (Exception e) {
                throw new ValidacaoException("ID da entrega inválido.");
            }

            String status = request.getParameter("status").trim();
            status = status.replaceAll("[0-9]", "");
            
            Entrega entrega = entregaBo.buscarEntregaPorIdBO(id);

            entrega.setStatus(status);
            entrega.setFrete(Double.parseDouble(request.getParameter("frete")));

            entregaBo.atualizarEntregaBO(entrega);

            response.sendRedirect(request.getContextPath() + "/entregaDetalhe?id=" + entrega.getId());

        } catch (ValidacaoException e) {
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/entrega/formAlteraEntrega.jsp");
            rd.forward(request, response);
        }
    }
}