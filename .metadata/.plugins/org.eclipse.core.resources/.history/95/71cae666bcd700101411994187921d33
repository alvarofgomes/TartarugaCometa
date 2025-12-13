package com.tartarugacometa.BO;

import java.util.List;

import com.tartarugacometa.dao.EntregaDAO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Entrega;

public class EntregaBO {

    private EntregaDAO entregaDAO = new EntregaDAO();

    public void cadastrarEntregaBO(Entrega entrega) {
    	validarEntrega(entrega);
    	entregaDAO.cadastrarEntregaDAO(entrega);
    }

    public void atualizarEntregaBO(Entrega entrega) {
    	validarEntrega(entrega);
    	entregaDAO.atualizarEntregaDAO(entrega);
    }

    public void deletarEntregaBO(int id) {
    	entregaDAO.deletarEntregaDAO(id);
    }

    public List<Entrega> listarEntregasPorClienteBO(int clienteId) {
        return entregaDAO.listarEntregasPorClienteDAO(clienteId);
    }

    public void validarEntrega(Entrega entrega) {
        if (entrega.getStatus() == null || entrega.getStatus().trim().isEmpty()) {
            throw new ValidacaoException("O status da entrega não pode estar vazio.");
        }

        if (entrega.getCliente() == null) {
            throw new ValidacaoException("A entrega deve ter um cliente associado.");
        }
    }
    
}