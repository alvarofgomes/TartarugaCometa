package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ProdutoBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Produto;

@WebServlet("/alteraProduto")
public class AlteraProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ProdutoBO produtoBo = new ProdutoBO();
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	try {
            String idProduto = request.getParameter("id");

            if (idProduto == null || idProduto.trim().isEmpty()) {
                throw new ValidacaoException("ID do produto não informado.");
            }

            int id;
            try {
                id = Integer.parseInt(idProduto);
            } catch (NumberFormatException e) {
                throw new ValidacaoException("ID do produto inválido.");
            }
            
            String nome = request.getParameter("nomeDoProduto");
            nome = nome.replaceAll("[0-9]", "");

            Produto produto = produtoBo.buscarProdutoPorIdBO(id);
            produto.setNomeDoProduto(nome);
            produto.setPeso(Double.parseDouble(request.getParameter("peso")));
            produto.setVolume(Double.parseDouble(request.getParameter("volume")));
            produto.setValor(Double.parseDouble(request.getParameter("valor")));

            produtoBo.atualizarProdutoBO(produto);

            response.sendRedirect(request.getContextPath() + "/produtoListar");

        } catch (ValidacaoException e) {
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/produto/formAlteraProduto.jsp");
            rd.forward(request, response);
        }
    }
	
}