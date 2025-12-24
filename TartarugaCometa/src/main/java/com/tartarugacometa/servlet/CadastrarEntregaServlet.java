package com.tartarugacometa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.BO.EnderecoBO;
import com.tartarugacometa.BO.EntregaBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;
import com.tartarugacometa.model.Entrega;

@WebServlet("/entregaCadastrar")
public class CadastrarEntregaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntregaBO entregaBO = new EntregaBO();
    private ClienteBO clienteBO = new ClienteBO();
    private EnderecoBO enderecoBO = new EnderecoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("clientes", clienteBO.listarClientesBO());
        request.setAttribute("enderecos", enderecoBO.listarEnderecoBO());

        request.getRequestDispatcher("/entrega/cadastrar.jsp")
               .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String status = request.getParameter("status");
            String freteStr = request.getParameter("frete");
            String remetenteIdStr = request.getParameter("remetenteId");
            String destinatarioIdStr = request.getParameter("destinatarioId");
            String enderecoOrigemIdStr = request.getParameter("enderecoOrigemId");
            String enderecoDestinoIdStr = request.getParameter("enderecoDestinoId");

            if (status == null || status.isEmpty()
                || freteStr == null || freteStr.isEmpty()
                || remetenteIdStr == null || remetenteIdStr.isEmpty()
                || destinatarioIdStr == null || destinatarioIdStr.isEmpty()
                || enderecoOrigemIdStr == null || enderecoOrigemIdStr.isEmpty()
                || enderecoDestinoIdStr == null || enderecoDestinoIdStr.isEmpty()) {

                throw new RuntimeException("Todos os campos são obrigatórios.");
            }

            double frete = Double.parseDouble(freteStr);
            int remetenteId = Integer.parseInt(remetenteIdStr);
            int destinatarioId = Integer.parseInt(destinatarioIdStr);
            int enderecoOrigemId = Integer.parseInt(enderecoOrigemIdStr);
            int enderecoDestinoId = Integer.parseInt(enderecoDestinoIdStr);

            Cliente remetente = new Cliente();
            remetente.setId(remetenteId);

            Cliente destinatario = new Cliente();
            destinatario.setId(destinatarioId);

            Endereco enderecoOrigem = new Endereco();
            enderecoOrigem.setId(enderecoOrigemId);

            Endereco enderecoDestino = new Endereco();
            enderecoDestino.setId(enderecoDestinoId);

            Entrega entrega = new Entrega();
            entrega.setStatus(status);
            entrega.setFrete(frete);
            entrega.setRemetente(remetente);
            entrega.setDestinatario(destinatario);
            entrega.setEnderecoOrigem(enderecoOrigem);
            entrega.setEnderecoDestino(enderecoDestino);

            entregaBO.cadastrarEntregaBO(entrega);

            request.setAttribute("entrega", entrega);
            request.getRequestDispatcher("/entrega/sucesso.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/entrega/erro.jsp")
                   .forward(request, response);
        }
    }
}
