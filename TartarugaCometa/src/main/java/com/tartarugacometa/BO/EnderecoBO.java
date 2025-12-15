package com.tartarugacometa.BO;

import java.util.List;

import com.tartarugacometa.dao.EnderecoDAO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;

public class EnderecoBO {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public void cadastrarEnderecoBO(Endereco endereco) {
    	validarEndereco(endereco);
        enderecoDAO.cadastrarEnderecoDAO(endereco);
    }

    public void atualizarEnderecoBO(Endereco endereco) {
    	validarEndereco(endereco);
        enderecoDAO.atualizarEnderecoDAO(endereco);
    }

    public void deletarEnderecoBO(int id) {
        enderecoDAO.deletarEnderecoDAO(id);
    }

    public List<Endereco> listarEnderecoBO() {
        return enderecoDAO.listarEnderecosDAO();
    }
    
    public Endereco buscarEnderecoPorIdBO(int id) {
        return enderecoDAO.buscarEnderecoPorIdDAO(id);
    }
    
    public void validarEndereco(Endereco endereco) {
        if (endereco.getRua() == null || endereco.getRua().trim().isEmpty()) {
            throw new ValidacaoException("A rua não pode estar vazia.");
        }

        if (endereco.getCidade() == null || endereco.getCidade().trim().isEmpty()) {
            throw new ValidacaoException("A cidade não pode estar vazia.");
        }
    }
    
}