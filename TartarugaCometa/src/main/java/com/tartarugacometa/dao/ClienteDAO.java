package com.tartarugacometa.dao;

import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Entrega;
import com.conexaofactory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactory();
    //lembrar de tratar as exception pra dar um erro de cada metodo e não um erro de runtime que e outro erro aleatorio sem base no codigo 
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
            throw new RuntimeException(e);
        }
    }

    
    /*testando formas de exceptions apenas teste, isso n vai se manter nessa parte do codigo
    public static void clienteErroVazio(Cliente cliente) {
    	
    	if(cliente.getCpfCnpj().isEmpty() || cliente.getCpfCnpj().equals(" ")) {
    		throw new IllegalArgumentException("CPF está vazio. ");
    	}
    	
    }
    
    public static void clienteLetraCpfCnpj(Cliente cliente) {
    	
    	boolean l = cliente.getCpfCnpj().matches("[a-zA-Z]+"); 
    	
    	if(cliente.getCpfCnpj().equals(l)) {
    		throw new IllegalArgumentException("CPF não pode conter letras. ");
    	}
    	
    }*/

    public void atualizarClienteDAO(Cliente cliente) {
    	
        String sql = "UPDATE clientes SET nome = ?, cpfcnpj = ? WHERE id_cliente = ?;";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpfCnpj());
            ps.setInt(3, cliente.getId());
            
            ps.execute();
			ps.close();
			conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void deletarClienteDAO(int id) {
    	
        EntregaDAO entregaDAO = new EntregaDAO();
        ItensEntregasDAO itensDAO = new ItensEntregasDAO();
        
        List<Entrega> entregas = entregaDAO.listarEntregasPorClienteDAO(id);
    	
        //deletar o de itens
        for (Entrega entrega : entregas) {
            itensDAO.deletarItensEntregaDAO(entrega.getId());
        }
        
        for (Entrega entrega : entregas) {
            entregaDAO.deletarEntregaDAO(entrega.getId());
        }
        
        String sql = "DELETE FROM clientes WHERE id_cliente = ?;";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            
            ps.execute();
			ps.close();
			conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public List<Cliente> listarClientesDAO() {
    	
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes;";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
        		
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	
                Cliente cliente = new Cliente(
                rs.getString("nome"),
                rs.getString("cpfcnpj"));
                
                cliente.setId(rs.getInt("id_cliente"));
                clientes.add(cliente);
                
            }
            
            ps.execute();
			ps.close();
			conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;
        
    }
    
    public Cliente buscarClientePorIdDAO(int id) {
    	
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?;";

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
                throw new RuntimeException("Cliente com ID " + id + " n�o encontrado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
}
