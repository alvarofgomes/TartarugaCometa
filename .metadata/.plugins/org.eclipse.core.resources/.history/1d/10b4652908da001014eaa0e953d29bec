package com.tartarugacometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexaofactory.ConnectionFactory;
import com.tartarugacometa.model.Entrega;

public class EntregaDAO {
	
	private ConnectionFactory connection = new ConnectionFactory();

    public void cadastrarEntregaDAO(Entrega entrega) {
        String sql = "INSERT INTO entregas (status, frete, clientes_id) VALUES (?, ?, ?);";

        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entrega.getStatus());
            ps.setDouble(2, entrega.getFrete());
            ps.setInt(3, entrega.getCliente().getId());

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