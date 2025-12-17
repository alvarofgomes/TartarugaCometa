package com.conexaofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	//lembrar de trocar a porta quando tiver no notebook do trabalho(5433) ou em casa(5432)
    private static final String URL =
        "jdbc:postgresql://localhost:5432/tartaruga_cometa";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public Connection recuperarConexao() {

        try {
            // forçando carregamento do driver
            Class.forName("org.postgresql.Driver");

            System.out.println("Conexão ok.");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do PostgreSQL não encontrado", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco", e);
        }
    }
}