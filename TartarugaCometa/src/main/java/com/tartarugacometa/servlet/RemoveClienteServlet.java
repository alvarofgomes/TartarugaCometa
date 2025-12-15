package com.tartarugacometa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.ClienteBO;

@WebServlet("/removeCliente")
public class RemoveClienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteBO clienteBo = new ClienteBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paramId = request.getParameter("id");

            try {
                Integer id = Integer.valueOf(paramId);

                clienteBo.deletarClienteBO(id);

                response.sendRedirect("/TartarugaCometa/clienteListar");

            } catch (Exception e) {
            	response.sendRedirect("Erro: " + e.getMessage());
         }
      
    }
}