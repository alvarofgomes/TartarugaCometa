package com.tartarugacometa.BO;

import com.tartarugacometa.dao.ClienteDAO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;

import java.util.List;

public class ClienteBO {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarClienteBO(Cliente cliente) {
        validarCliente(cliente);
        clienteDAO.cadastrarClienteDAO(cliente);
    }

    public void atualizarClienteBO(Cliente cliente) {
        if (cliente.getId() <= 0) {
            throw new ValidacaoException("ID do cliente inválido para atualização.");
        }
        validarCliente(cliente);
        clienteDAO.atualizarClienteDAO(cliente);
    }

    public void deletarClienteBO(int id) {
        try {

            clienteDAO.deletarClienteSimplesDAO(id);
            
        } catch (Exception e) {

            if (e.getMessage().contains("violates foreign key constraint") || 
                e.getMessage().contains("entregas associadas")) {
                
                try {
                    clienteDAO.deletarClienteDAO(id); 
                } catch (Exception e2) {
                    throw new RuntimeException(
                        "Não foi possível remover o cliente. " +
                        "Existem entregas associadas que impedem a remoção. " +
                        "Remova as entregas primeiro ou contate o administrador. " +
                        "Erro: " + e2.getMessage());
                }
            } else {

                throw new RuntimeException("Erro ao remover cliente: " + e.getMessage());
            }
        }
    }
    
    public List<Cliente> listarClientesBO() {
        return clienteDAO.listarClientesDAO();
    }

    public Cliente buscarClientePorIdBO(int id) {
        if (id <= 0) {
            throw new ValidacaoException("ID do cliente inválido.");
        }
        return clienteDAO.buscarClientePorIdDAO(id);
    }

    private void validarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new ValidacaoException("Cliente não pode ser nulo.");
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O nome do cliente não pode estar vazio.");
        }

        if (cliente.getCpfCnpj() == null || cliente.getCpfCnpj().trim().isEmpty()) {
            throw new ValidacaoException("CPF/CNPJ não pode estar vazio.");
        }

        if (!cliente.getCpfCnpj().matches("\\d+")) {
            throw new ValidacaoException("CPF/CNPJ deve conter apenas números.");
        }

        if (cliente.getCpfCnpj().length() != 11 && cliente.getCpfCnpj().length() != 14) {
            throw new ValidacaoException("CPF deve ter 11 dígitos ou CNPJ deve ter 14 dígitos.");
        }
    }
}
