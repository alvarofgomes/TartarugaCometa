package com.tartarugacometa.BO;

import java.util.List;

import com.tartarugacometa.dao.ItensEntregasDAO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Entrega;
import com.tartarugacometa.model.ItensEntregas;

public class ItensEntregasBO {

    private ItensEntregasDAO dao = new ItensEntregasDAO();

    public void adicionarItem(ItensEntregas item) {

        if (item == null) {
            throw new ValidacaoException("Item inválido.");
        }

        if (item.getEntrega() == null || item.getEntrega().getId() <= 0) {
            throw new ValidacaoException("Entrega inválida.");
        }

        if (item.getProduto() == null || item.getProduto().getId() <= 0) {
            throw new ValidacaoException("Produto inválido.");
        }

        if (item.getQuantidade() <= 0) {
            throw new ValidacaoException("Quantidade deve ser maior que zero.");
        }

        dao.cadastrarItemDAO(item);
    }

    public void removerItem(int idItem, Entrega entrega) {

        if (idItem <= 0) {
            throw new ValidacaoException("Item inválido.");
        }
        
		if ("entregue".equalsIgnoreCase(entrega.getStatus())) {
            throw new RuntimeException("Não é possível alterar itens de uma entrega já concluída.");
        }

        dao.removerItemPorId(idItem);
    }

    
    public List<ItensEntregas> listarPorEntrega(int entregaId) {
        return dao.listarPorEntregaDAO(entregaId);
    }

    public void removerPorEntrega(int entregaId) {
        dao.deletarItensEntregaDAO(entregaId);
    }
}
