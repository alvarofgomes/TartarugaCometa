package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EnderecoBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Endereco;

@WebServlet("/alteraEndereco")
public class AlteraEnderecoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EnderecoBO enderecoBo = new EnderecoBO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

        	String idEndereco = request.getParameter("id");

        	if (idEndereco == null || idEndereco.isEmpty()) {
        	    throw new ValidacaoException("Falha ao identificar o endereço.");
        	}

        	int id;
        	try {
        	    id = Integer.parseInt(idEndereco);
        	} catch (NumberFormatException e) {
        	    throw new ValidacaoException("ID do endereço inválido.");
        	}

             
            String rua = request.getParameter("rua").trim();
            String numero = request.getParameter("numero").trim();
            String bairro = request.getParameter("bairro").trim();
            String cidade = request.getParameter("cidade").trim();
            String estado = request.getParameter("estado").trim();
            String cep = request.getParameter("cep").trim().replaceAll("\\s+", "");
            cep = cep.replaceAll("\\D", "");
            
            Endereco endereco = new Endereco();
            endereco.setId(id);
            endereco.setRua(rua);
            endereco.setNumero(numero);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setCep(cep);

            enderecoBo.atualizarEnderecoBO(endereco);

            response.sendRedirect(request.getContextPath() + "/clienteListar");

        } catch (ValidacaoException e) {
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/endereco/formAlteraEndereco.jsp");
            rd.forward(request, response);

        }

    }
}
