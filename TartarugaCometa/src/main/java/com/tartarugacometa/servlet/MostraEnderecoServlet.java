package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EnderecoBO;
import com.tartarugacometa.model.Endereco;

@WebServlet("/mostraEndereco")
public class MostraEnderecoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EnderecoBO enderecoBo = new EnderecoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idEndereco = request.getParameter("id");

        if (idEndereco != null && !idEndereco.isEmpty()) {
            Integer id = Integer.valueOf(idEndereco);

            Endereco endereco = enderecoBo.buscarEnderecoPorIdBO(id);

            request.setAttribute("endereco", endereco);

            RequestDispatcher rd =
                    request.getRequestDispatcher("/endereco/formAlteraEndereco.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("Erro: ID do endereço não informado.");
        }
    }
}
