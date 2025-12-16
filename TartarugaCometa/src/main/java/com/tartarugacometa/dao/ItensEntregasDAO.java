package com.tartarugacometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexaofactory.ConnectionFactory;
import com.tartarugacometa.model.Entrega;
import com.tartarugacometa.model.ItensEntregas;
import com.tartarugacometa.model.Produto;

public class ItensEntregasDAO {
	
	  private ConnectionFactory connection = new ConnectionFactory();

	   public void cadastrarItemDAO(ItensEntregas itensEntregas) {
	        String sql = "INSERT INTO itens_entregas (entrega_id, produto_id) VALUES (?, ?);";

	        try (Connection conn = connection.recuperarConexao();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	        	ps.setInt(1,itensEntregas.getEntrega().getId());
	        	ps.setInt(2, itensEntregas.getProduto().getId());
	        	
	            ps.execute();

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	    public void deletarItensEntregaDAO(int entregaId) {
	        String sql = "DELETE FROM itens_entregas WHERE entrega_id = ?;";

	        try (Connection conn = connection.recuperarConexao();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, entregaId);
	            ps.execute();

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    //evitar erro quando apaga com o metodo acima 
	    public void deletarItensPorProdutoDAO(int produtoId) {
	        String sql = "DELETE FROM itens_entregas WHERE produto_id = ?;";

	        try (Connection conn = connection.recuperarConexao();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, produtoId);
	            ps.execute();

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	   
	    public List<ItensEntregas> listarPorEntregaDAO(int entregaId) {
	    	
	        List<ItensEntregas> itensEntregas = new ArrayList<>();

	        String sql = "SELECT * FROM itens_entregas WHERE entrega_id = ?;";

	        try (Connection conn = connection.recuperarConexao();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	        	//enquanto não testar ver se funciona
	            ps.setInt(1, entregaId);
	            //caso de erro no sql testar passando os 2 id de entrega e produto
	            //ps.setInt(2, produtoId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	            	ItensEntregas itens = new ItensEntregas();
	            	itens.setId(rs.getInt("id_item"));
	                
	                Entrega entrega = new Entrega();
	                entrega.setId(rs.getInt("entrega_id"));
	                itens.setEntrega(entrega);
	                
	                Produto produto = new Produto();
	                produto.setId(rs.getInt("produto_id"));
	                itens.setProduto(produto);

	                itensEntregas.add(itens);
	            }

	            return itensEntregas;

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
	    