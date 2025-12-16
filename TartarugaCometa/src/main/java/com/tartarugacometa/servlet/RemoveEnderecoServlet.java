package com.tartarugacometa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EnderecoBO;

@WebServlet("/removeEndereco")
public class RemoveEnderecoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EnderecoBO enderecoBo = new EnderecoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            enderecoBo.deletarEnderecoBO(id);

            response.sendRedirect(request.getContextPath() + "/enderecoListar");

        } catch (Exception e) {
            throw new ServletException("Erro ao remover endereço", e);
        }
    }
}
