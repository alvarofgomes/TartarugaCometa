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

@WebServlet("/produtoCadastrar")
public class CadastrarProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ProdutoBO produtoBo = new ProdutoBO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/produto/cadastrar.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String nomeDoProduto = request.getParameter("nomeDoProduto");
			Double peso = Double.valueOf(request.getParameter("peso"));
			Double volume = Double.valueOf(request.getParameter("volume"));
			Double valor = Double.valueOf(request.getParameter("valorfrete"));
			
			Produto produto = new Produto();
			produto.setNomeDoProduto(nomeDoProduto);
			produto.setPeso(peso);
			produto.setVolume(volume);
			produto.setValor(valor);

			produtoBo.cadastrarProdutoBO(produto);
			
            request.setAttribute("produto", produto.getNomeDoProduto());
            RequestDispatcher rd = request.getRequestDispatcher("/produto/sucesso.jsp");
            rd.forward(request, response);
			
		} catch (ValidacaoException e) {
			request.setAttribute("erro", e.getMessage());

			RequestDispatcher rd = request.getRequestDispatcher("/produto/erro.jsp");
			rd.forward(request, response);
		}
		
	}
	
}