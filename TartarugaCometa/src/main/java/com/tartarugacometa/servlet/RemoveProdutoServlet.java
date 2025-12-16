package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ProdutoBO;

@WebServlet("/removeProduto")
public class RemoveProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ProdutoBO produtoBo = new ProdutoBO();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String idProduto = request.getParameter("id");
    	
        if (idProduto == null || idProduto.isEmpty()) {
            throw new RuntimeException("ID do produto não informado");
        }
    	
        int id = Integer.parseInt(idProduto);
        
        produtoBo.deletarProdutoBO(id);
        response.sendRedirect(request.getContextPath() + "/produtoListar");
    }
	
}