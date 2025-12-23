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
	        String sql = "INSERT INTO itens_entregas (entrega_id, produto_id, quantidade) VALUES (?, ?, ?);";

	        try (Connection conn = connection.recuperarConexao();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	        	ps.setInt(1,itensEntregas.getEntrega().getId());
	        	ps.setInt(2, itensEntregas.getProduto().getId());
	        	ps.setInt(3, itensEntregas.getQuantidade());
	        	
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

	        String sql =
	            "SELECT ie.id_item, ie.quantidade, " +
	            "p.id_produto, p.nome, p.peso, p.volume, p.valor " +
	            "FROM itens_entregas ie " +
	            "JOIN produtos p ON ie.produto_id = p.id_produto " +
	            "WHERE ie.entrega_id = ?;";

	        try (Connection conn = connection.recuperarConexao();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, entregaId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {

	                Produto produto = new Produto();
	                produto.setId(rs.getInt("id_produto"));
	                produto.setNomeDoProduto(rs.getString("nome"));
	                produto.setPeso(rs.getDouble("peso"));
	                produto.setVolume(rs.getDouble("volume"));
	                produto.setValor(rs.getDouble("valor"));

	                ItensEntregas item = new ItensEntregas();
	                item.setId(rs.getInt("id_item"));
	                item.setQuantidade(rs.getInt("quantidade"));
	                item.setProduto(produto);

	                itensEntregas.add(item);
	            }

	            return itensEntregas;

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public void removerItemPorId(int idItem) {

	        String sql = "DELETE FROM itens_entregas WHERE id_item = ?";

	        try (Connection conn = connection.recuperarConexao();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, idItem);
	            ps.execute();

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    
}
	    