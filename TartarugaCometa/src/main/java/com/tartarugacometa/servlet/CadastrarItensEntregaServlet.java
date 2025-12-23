package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ItensEntregasBO;
import com.tartarugacometa.model.Entrega;
import com.tartarugacometa.model.ItensEntregas;
import com.tartarugacometa.model.Produto;

@WebServlet("/cadastrarItensEntrega")
public class CadastrarItensEntregaServlet extends HttpServlet {

    private ItensEntregasBO bo = new ItensEntregasBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/entregaListar");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int entregaId = Integer.parseInt(request.getParameter("entregaId"));
            int produtoId = Integer.parseInt(request.getParameter("produtoId"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));

            Entrega entrega = new Entrega();
            entrega.setId(entregaId);

            Produto produto = new Produto();
            produto.setId(produtoId);

            ItensEntregas item = new ItensEntregas();
            item.setEntrega(entrega);
            item.setProduto(produto);
            item.setQuantidade(quantidade);

            bo.adicionarItem(item);

            response.sendRedirect(
                request.getContextPath() + "/entregaDetalhe?id=" + entregaId
            );

        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/entrega/erro.jsp").forward(request, response);
        }
    }
}
