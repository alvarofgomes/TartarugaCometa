package com.tartarugacometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexaofactory.ConnectionFactory;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;
import com.tartarugacometa.model.Entrega;
import com.tartarugacometa.model.ItensEntregas;
import com.tartarugacometa.model.Produto;

public class EntregaDAO {
	
	private ConnectionFactory connection = new ConnectionFactory();

	public void cadastrarEntregaDAO(Entrega entrega) {

	    String sql = " INSERT INTO entregas (status, frete, remetente_id, destinatario_id, endereco_origem_id, endereco_destino_id)"+
	        "VALUES (?, ?, ?, ?, ?, ?);";

	    try (Connection conn = connection.recuperarConexao();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, entrega.getStatus());
	        ps.setDouble(2, entrega.getFrete());
	        ps.setInt(3, entrega.getRemetente().getId());
	        ps.setInt(4, entrega.getDestinatario().getId());
	        ps.setInt(5, entrega.getEnderecoOrigem().getId());
	        ps.setInt(6, entrega.getEnderecoDestino().getId());

	        ps.execute();

	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

    public void atualizarEntregaDAO(Entrega entrega) {
        String sql = "UPDATE entregas SET status=?, frete=? WHERE id_entrega=?;";

        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entrega.getStatus());
            ps.setDouble(2, entrega.getFrete());
            //verificar depois se e necessario atualizar o id do cliente(ao meu ver não faz sentindo porque o id do cliente não muda então não tem necessidade de atualizar o id. )
            ps.setInt(3, entrega.getId());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deletarEntregaDAO(int idEntrega) {
        String sql = "DELETE FROM entregas WHERE id_entrega = ?;";

        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
    public List<ItensEntregas> listarPorEntregaDAO(int entregaId) {

        List<ItensEntregas> itensEntregas = new ArrayList<>();

        String sql =
            "SELECT ie.id_item, ie.quantidade, " +
            "p.id_produto, p.nome " +
            "FROM itens_entregas ie " +
            "JOIN produtos p ON ie.produto_id = p.id_produto " +
            "WHERE ie.entrega_id = ?";

        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entregaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ItensEntregas item = new ItensEntregas();
                item.setId(rs.getInt("id_item"));
                item.setQuantidade(rs.getInt("quantidade"));

                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNomeDoProduto(rs.getString("nome"));

                item.setProduto(produto);

                itensEntregas.add(item);
            }

            return itensEntregas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Entrega> listarEntregasDAO() {

        List<Entrega> entregas = new ArrayList<>();

        String sql =
        	    "SELECT " +
        	    "e.id_entrega, e.status, e.frete, " +

        	    "r.id_cliente AS remetente_id, r.nome AS remetente_nome, r.cpfcnpj AS remetente_cpf, " +
        	    "d.id_cliente AS destinatario_id, d.nome AS destinatario_nome, d.cpfcnpj AS destinatario_cpf, " +

        	    "eo.rua AS origem_rua, eo.numero AS origem_numero, eo.bairro AS origem_bairro, eo.cidade AS origem_cidade, " +
        	    "ed.rua AS destino_rua, ed.numero AS destino_numero, ed.bairro AS destino_bairro, ed.cidade AS destino_cidade " +

        	    "FROM entregas e " +
        	    "JOIN clientes r ON e.remetente_id = r.id_cliente " +
        	    "JOIN clientes d ON e.destinatario_id = d.id_cliente " +
        	    "JOIN enderecos eo ON e.endereco_origem_id = eo.id_endereco " +
        	    "JOIN enderecos ed ON e.endereco_destino_id = ed.id_endereco";

        
        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Entrega entrega = new Entrega();
                entrega.setId(rs.getInt("id_entrega"));
                entrega.setStatus(rs.getString("status"));
                entrega.setFrete(rs.getDouble("frete"));

                Cliente remetente = new Cliente();
                remetente.setId(rs.getInt("remetente_id"));
                remetente.setNome(rs.getString("remetente_nome"));
                remetente.setCpfCnpj(rs.getString("remetente_cpf"));

                Cliente destinatario = new Cliente();
                destinatario.setId(rs.getInt("destinatario_id"));
                destinatario.setNome(rs.getString("destinatario_nome"));
                destinatario.setCpfCnpj(rs.getString("destinatario_cpf"));

                Endereco origem = new Endereco();
                origem.setRua(rs.getString("origem_rua"));
                origem.setNumero(rs.getString("origem_numero"));
                origem.setBairro(rs.getString("origem_bairro"));
                origem.setCidade(rs.getString("origem_cidade"));

                Endereco destino = new Endereco();
                destino.setRua(rs.getString("destino_rua"));
                destino.setNumero(rs.getString("destino_numero"));
                destino.setBairro(rs.getString("destino_bairro"));
                destino.setCidade(rs.getString("destino_cidade"));

                entrega.setRemetente(remetente);
                entrega.setDestinatario(destinatario);
                entrega.setEnderecoOrigem(origem);
                entrega.setEnderecoDestino(destino);

                entregas.add(entrega);
            }

            return entregas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Entrega buscarEntregaPorIdDAO(int id) {

        String sql =
            "SELECT e.id_entrega, e.status, e.frete, " +
            "r.id_cliente AS remetente_id, r.nome AS remetente_nome, " +
            "d.id_cliente AS destinatario_id, d.nome AS destinatario_nome, " +
            "eo.id_endereco AS origem_id, eo.rua AS origem_rua, eo.numero AS origem_numero, eo.bairro AS origem_bairro, eo.cidade AS origem_cidade, " +
            "ed.id_endereco AS destino_id, ed.rua AS destino_rua, ed.numero AS destino_numero, ed.bairro AS destino_bairro, ed.cidade AS destino_cidade " +
            "FROM entregas e " +
            "JOIN clientes r ON e.remetente_id = r.id_cliente " +
            "JOIN clientes d ON e.destinatario_id = d.id_cliente " +
            "JOIN enderecos eo ON e.endereco_origem_id = eo.id_endereco " +
            "JOIN enderecos ed ON e.endereco_destino_id = ed.id_endereco " +
            "WHERE e.id_entrega = ?";

        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Entrega entrega = new Entrega();
                entrega.setId(rs.getInt("id_entrega"));
                entrega.setStatus(rs.getString("status"));
                entrega.setFrete(rs.getDouble("frete"));

                Cliente remetente = new Cliente();
                remetente.setId(rs.getInt("remetente_id"));
                remetente.setNome(rs.getString("remetente_nome"));

                Cliente destinatario = new Cliente();
                destinatario.setId(rs.getInt("destinatario_id"));
                destinatario.setNome(rs.getString("destinatario_nome"));

                Endereco origem = new Endereco();
                origem.setId(rs.getInt("origem_id"));
                origem.setRua(rs.getString("origem_rua"));
                origem.setNumero(rs.getString("origem_numero"));
                origem.setBairro(rs.getString("origem_bairro"));
                origem.setCidade(rs.getString("origem_cidade"));

                Endereco destino = new Endereco();
                destino.setId(rs.getInt("destino_id"));
                destino.setRua(rs.getString("destino_rua"));
                destino.setNumero(rs.getString("destino_numero"));
                destino.setBairro(rs.getString("destino_bairro"));
                destino.setCidade(rs.getString("destino_cidade"));

                entrega.setRemetente(remetente);
                entrega.setDestinatario(destinatario);
                entrega.setEnderecoOrigem(origem);
                entrega.setEnderecoDestino(destino);

                return entrega;
            }

            throw new RuntimeException("Entrega não encontrada");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Entrega> listarEntregasPorClienteDAO(int clienteId) {
        List<Entrega> entregas = new ArrayList<>();
        String sql = "SELECT * FROM entregas WHERE clientes_id = ?;";

        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(rs.getInt("id_entrega"));
                entrega.setStatus(rs.getString("status"));
                entrega.setFrete(rs.getDouble("frete"));
                entregas.add(entrega);
            }

            return entregas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}