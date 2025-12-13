package com.tartarugacometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexaofactory.ConnectionFactory;
import com.tartarugacometa.model.Produto;

public class ProdutoDAO {

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	 //lembrar de tratar as exception pra dar um erro de cada metodo e não um erro de runtime que e outro erro aleatorio sem base no codigo 
	public void cadastrarProdutoDAO(Produto produto) {
		
		String sql = "INSERT INTO produtos (nome,peso,volume,valorfrete) VALUES (?,?,?,?);";
		
		try(Connection conn = connectionFactory.recuperarConexao();
	        PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, produto.getNomeDoProduto());
			ps.setDouble(2, produto.getPeso());
			ps.setDouble(3,produto.getVolume());
			ps.setDouble(4, produto.getValor());
			
            ps.execute();
			ps.close();
			conn.close();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void atualizarProdutoDAO(Produto produto) {
		
		String sql = "UPDATE produtos SET nome = ?, peso = ?, volume = ?, valorfrete = ? WHERE id_produto = ?;";
		
		try(Connection conn = connectionFactory.recuperarConexao();
		    PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, produto.getNomeDoProduto());
			ps.setDouble(2, produto.getPeso());
			ps.setDouble(3,produto.getVolume());
			ps.setDouble(4, produto.getValor());
			ps.setInt(5, produto.getId());
			
			ps.execute();
			ps.close();
			conn.close();

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void deletarProdutoDAO(int id) {
		
		String sql = "DELETE FROM produtos WHERE id_produto = ?;";
		
		try(Connection conn = connectionFactory.recuperarConexao();
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
            ps.execute();
			ps.close();
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException();
		}
		
	}
	
	public List<Produto> listarProdutoDAO(){
		
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT * FROM produtos;";
		
		try(Connection conn = connectionFactory.recuperarConexao();
			PreparedStatement ps = conn.prepareStatement(sql);
				
			ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					
					Produto produto = new Produto(
							
							rs.getString("nome"),
							rs.getDouble("peso"),
							rs.getDouble("volume"),
							rs.getDouble("valorfrete")
							
					);
					
					produto.setId(rs.getInt("id_produto"));
					produtos.add(produto);
					
				}

				return produtos;
				
			}catch(SQLException e) {
				throw new RuntimeException();
			}
		
	}
	
}