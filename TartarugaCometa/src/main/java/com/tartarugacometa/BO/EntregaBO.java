package com.tartarugacometa.BO;

import java.util.List;

import com.tartarugacometa.dao.EntregaDAO;
import com.tartarugacometa.dao.ItensEntregasDAO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;
import com.tartarugacometa.model.Entrega;

public class EntregaBO {

    private EntregaDAO entregaDAO = new EntregaDAO();
    private ItensEntregasDAO itensEntregasDAO = new ItensEntregasDAO();

    public void cadastrarEntregaBO(Entrega entrega) {
    	validarEntrega(entrega);
    	entregaDAO.cadastrarEntregaDAO(entrega);
    }

    public void atualizarEntregaBO(Entrega entrega) {
    	validarEntrega(entrega);
    	entregaDAO.atualizarEntregaDAO(entrega);
    }

    public void deletarEntregaBO(int id) {
    	
		itensEntregasDAO.deletarItensEntregaDAO(id);
    	
    	entregaDAO.deletarEntregaDAO(id);
    }

    public List<Entrega> listarEntregasPorClienteBO(int clienteId) {
        return entregaDAO.listarEntregasPorClienteDAO(clienteId);
    }

    public Entrega buscarEntregaPorIdBO(int id) {
        return entregaDAO.buscarEntregaPorIdDAO(id);
    }
 
    public List<Entrega> listarEntregaoBO() {
        return entregaDAO.listarEntregasDAO();
    }
    
    public void validarEntrega(Entrega entrega) {

        if (entrega == null) {
            throw new ValidacaoException("Entrega inválida.");
        }

        if (entrega.getStatus() == null || entrega.getStatus().trim().isEmpty()) {
            throw new ValidacaoException("O status da entrega não pode estar vazio.");
        }

        if (entrega.getFrete() < 0) {
            throw new ValidacaoException("O valor do frete não pode ser negativo.");
        }

        if (entrega.getCliente() == null || entrega.getCliente().getId() <= 0) {
            throw new ValidacaoException("A entrega deve estar associada a um cliente válido.");
        }
    }

}