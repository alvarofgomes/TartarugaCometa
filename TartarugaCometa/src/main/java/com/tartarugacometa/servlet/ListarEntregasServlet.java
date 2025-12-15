package com.tartarugacometa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tartarugacometa.BO.EntregaBO;
import com.tartarugacometa.model.Entrega;

@WebServlet("/entregaListar")
public class ListarEntregasServlet extends HttpServlet {

    private EntregaBO entregaBo = new EntregaBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Entrega> entregas = entregaBo.listarEntregaoBO();

        request.setAttribute("entregas", entregas);
        RequestDispatcher rd = request.getRequestDispatcher("/entrega/listar.jsp");
        rd.forward(request, response);
    }
}