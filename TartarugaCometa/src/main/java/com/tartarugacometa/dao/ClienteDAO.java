package com.tartarugacometa.dao;

import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;
import com.tartarugacometa.model.Entrega;
import com.conexaofactory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public void cadastrarClienteDAO(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpfcnpj) VALUES (?, ?)";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpfCnpj());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
        }
    }

    public void atualizarClienteDAO(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, cpfcnpj = ? WHERE id_cliente = ?";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpfCnpj());
            ps.setInt(3, cliente.getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }


    public void deletarClienteDAO(int id) {
        Connection conn = null;
        try {
            conn = connectionFactory.recuperarConexao();
            conn.setAutoCommit(false); 
            
            
            try {
               
                String sqlBuscarEntregas = 
                    "SELECT id_entrega FROM entregas WHERE remetente_id = ? OR destinatario_id = ?";
                
                List<Integer> idsEntregas = new ArrayList<>();
                
                try (PreparedStatement ps = conn.prepareStatement(sqlBuscarEntregas)) {
                    ps.setInt(1, id);
                    ps.setInt(2, id);
                    
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        idsEntregas.add(rs.getInt("id_entrega"));
                    }
                }

                if (!idsEntregas.isEmpty()) {

                    StringBuilder idsStr = new StringBuilder();
                    for (int i = 0; i < idsEntregas.size(); i++) {
                        if (i > 0) idsStr.append(",");
                        idsStr.append(idsEntregas.get(i));
                    }
                    
                    String sqlDeletarItens = 
                        "DELETE FROM itens_entregas WHERE entrega_id IN (" + idsStr.toString() + ")";
                    
                    try (Statement stmt = conn.createStatement()) {
                        stmt.executeUpdate(sqlDeletarItens);
                    }
                    
                    String sqlDeletarEntregas = 
                        "DELETE FROM entregas WHERE id_entrega IN (" + idsStr.toString() + ")";
                    
                    try (Statement stmt = conn.createStatement()) {
                        stmt.executeUpdate(sqlDeletarEntregas);
                    }
                }
                
            } catch (SQLException e) {

                System.err.println("Aviso ao processar entregas: " + e.getMessage());
            }
            
            String sqlEnderecos = "DELETE FROM enderecos WHERE clientes_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlEnderecos)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            
            String sqlCliente = "DELETE FROM clientes WHERE id_cliente = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlCliente)) {
                ps.setInt(1, id);
                int rowsAffected = ps.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SQLException("Cliente com ID " + id + " não encontrado.");
                }
            }
            
            conn.commit(); 
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); 
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao desfazer transação: " + ex.getMessage(), ex);
            }
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletarClienteSimplesDAO(int id) {
        Connection conn = null;
        try {
            conn = connectionFactory.recuperarConexao();
            conn.setAutoCommit(false);
            
            String sqlEnderecos = "DELETE FROM enderecos WHERE clientes_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlEnderecos)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            
            String sqlCliente = "DELETE FROM clientes WHERE id_cliente = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlCliente)) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                
                if (rows == 0) {
                    throw new SQLException("Cliente não encontrado com ID: " + id);
                }
            }
            
            conn.commit();
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {}
            
            if (e.getMessage().contains("violates foreign key constraint")) {
                throw new RuntimeException(
                    "Não é possível remover o cliente porque existem entregas associadas a ele. " +
                    "Remova as entregas primeiro ou configure ON DELETE CASCADE.", e);
            }
            
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {}
        }
    }

    public List<Cliente> listarClientesDAO() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY nome";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpfcnpj")
                );
                cliente.setId(rs.getInt("id_cliente"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }

        return clientes;
    }
    
    public Cliente buscarClientePorIdDAO(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpfCnpj(rs.getString("cpfcnpj"));
                return cliente;
            } else {
                throw new RuntimeException("Cliente com ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        }
    }
    
    public List<Cliente> listarClientesComEnderecosDAO() {

        String sql =
            "SELECT c.id_cliente, c.nome, c.cpfcnpj, " +
            "e.id_endereco, e.rua, e.numero, e.bairro, e.cidade, e.estado, e.cep " +
            "FROM clientes c " +
            "LEFT JOIN enderecos e ON e.clientes_id = c.id_cliente " +
            "ORDER BY c.nome";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Cliente clienteAtual = null;
            int clienteIdAtual = -1;

            while (rs.next()) {

                int clienteId = rs.getInt("id_cliente");

                if (clienteAtual == null || clienteId != clienteIdAtual) {
                    clienteAtual = new Cliente();
                    clienteAtual.setId(clienteId);
                    clienteAtual.setNome(rs.getString("nome"));
                    clienteAtual.setCpfCnpj(rs.getString("cpfcnpj"));
                    clienteAtual.setEnderecos(new ArrayList<>());

                    clientes.add(clienteAtual);
                    clienteIdAtual = clienteId;
                }

                if (rs.getObject("id_endereco") != null) {
                    Endereco e = new Endereco();
                    e.setId(rs.getInt("id_endereco"));
                    e.setRua(rs.getString("rua"));
                    e.setNumero(rs.getString("numero"));
                    e.setBairro(rs.getString("bairro"));
                    e.setCidade(rs.getString("cidade"));
                    e.setEstado(rs.getString("estado"));
                    e.setCep(rs.getString("cep"));

                    clienteAtual.getEnderecos().add(e);
                }
            }

            return clientes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes com endereços", e);
        }
    }

    
}