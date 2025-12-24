package com.tartarugacometa.BO;

import java.util.List;

import com.tartarugacometa.dao.EnderecoDAO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Endereco;

public class EnderecoBO {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public void cadastrarEnderecoBO(Endereco endereco) {
        validarEnderecoCadastro(endereco);
        enderecoDAO.cadastrarEnderecoDAO(endereco);
    }

    public void atualizarEnderecoBO(Endereco endereco) {
        validarEnderecoAtualizacao(endereco);
        enderecoDAO.atualizarEnderecoDAO(endereco);
    }


    public void deletarEnderecoBO(int id) {
        if (id <= 0) {
            throw new ValidacaoException("ID do endereço inválido para exclusão.");
        }
        enderecoDAO.deletarEnderecoDAO(id);
    }

    public List<Endereco> listarEnderecoBO() {
        return enderecoDAO.listarEnderecoBO();
    }

    public Endereco buscarEnderecoPorIdBO(int id) {
        if (id <= 0) {
            throw new ValidacaoException("ID do endereço inválido.");
        }
        return enderecoDAO.buscarEnderecoPorIdDAO(id);
    }

    private void validarEnderecoCadastro(Endereco endereco) {
        validarCamposBasicos(endereco);

        if (endereco.getCliente() == null || endereco.getCliente().getId() <= 0) {
            throw new ValidacaoException("O endereço deve estar associado a um cliente válido.");
        }
    }

    private void validarEnderecoAtualizacao(Endereco endereco) {
        validarCamposBasicos(endereco);

        if (endereco.getId() <= 0) {
            throw new ValidacaoException("ID do endereço inválido.");
        }
    }

    private void validarCamposBasicos(Endereco endereco) {
        if (endereco == null) {
            throw new ValidacaoException("Endereço inválido.");
        }
        if (endereco.getRua() == null || endereco.getRua().trim().isEmpty()) {
            throw new ValidacaoException("A rua não pode estar vazia.");
        }
        if (endereco.getNumero() == null || endereco.getNumero().trim().isEmpty()) {
            throw new ValidacaoException("O número não pode estar vazio.");
        }
        if (endereco.getBairro() == null || endereco.getBairro().trim().isEmpty()) {
            throw new ValidacaoException("O bairro não pode estar vazio.");
        }
        if (endereco.getCidade() == null || endereco.getCidade().trim().isEmpty()) {
            throw new ValidacaoException("A cidade não pode estar vazia.");
        }
        if (endereco.getEstado() == null || endereco.getEstado().trim().isEmpty()) {
            throw new ValidacaoException("O estado não pode estar vazio.");
        }
        if (endereco.getCep() == null || !endereco.getCep().matches("\\d{8}")) {
            throw new ValidacaoException("O CEP deve conter exatamente 8 números.");
        }
    }
}