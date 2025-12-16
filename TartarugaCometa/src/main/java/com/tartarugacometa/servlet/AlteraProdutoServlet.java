package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ProdutoBO;
import com.tartarugacometa.model.Entrega;
import com.tartarugacometa.model.Produto;

@WebServlet("/alteraProduto")
public class AlteraProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ProdutoBO produtoBo = new ProdutoBO();
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String produtoId = request.getParameter("id"); 
    	
        if (produtoId == null || produtoId.isEmpty()) {
            throw new RuntimeException("ID do produto não informado");
        }
    	
        int id = Integer.parseInt(produtoId);
        
        Produto produto = produtoBo.buscarProdutoPorIdBO(id);

        produto.setNomeDoProduto(request.getParameter("nome"));
        produto.setPeso(Double.parseDouble(request.getParameter("peso")));
        produto.setVolume(Double.parseDouble(request.getParameter("volume")));
        produto.setValor(Double.parseDouble(request.getParameter("valorfrete")));
        
        produtoBo.atualizarProdutoBO(produto);

        response.sendRedirect(request.getContextPath() + "/produtoListar");
        
    }
	
}