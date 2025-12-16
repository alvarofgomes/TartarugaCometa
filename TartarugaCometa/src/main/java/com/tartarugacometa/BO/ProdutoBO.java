package com.tartarugacometa.BO;

import java.util.List;

import com.tartarugacometa.dao.ProdutoDAO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Produto;

public class ProdutoBO {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	
	public void cadastrarProdutoBO(Produto produto) {
		validarProduto(produto);
		produtoDAO.cadastrarProdutoDAO(produto);
	}
	
	public void atualizarProdutoBO(Produto produto) {
		validarProduto(produto);
		produtoDAO.atualizarProdutoDAO(produto);
	}
	
	public void deletarProdutoBO(int id) {
		produtoDAO.deletarProdutoDAO(id);
	}
	
    public Produto buscarProdutoPorIdBO(int id) {
        return produtoDAO.buscarProdutoPorIdDAO(id);
    }
	
	public List<Produto> listarProdutoBO(){
		return produtoDAO.listarProdutoDAO();
	}
	    
	    public void validarProduto(Produto produto) {
	    	
	        if (produto.getNomeDoProduto() == null || produto.getNomeDoProduto().trim().isEmpty()) {
	            throw new ValidacaoException("O nome do produto não pode estar vazio.");
	        }

	        if (produto.getValor() < 0) {
	            throw new ValidacaoException("O preço do produto não pode ser negativo.");
	        }
	    }
}