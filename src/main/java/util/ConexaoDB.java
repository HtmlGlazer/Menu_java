package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    
    // Configurações do banco
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante_db";
    private static final String USUARIO = "root";
    private static final String SENHA = " ";
    
    // Método para obter conexão
    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println(" Erro ao conectar com o banco de dados!");
            System.err.println("Motivo: " + e.getMessage());
            return null;
        }
    }
    
    // Método para testar conexão
    public static void testarConexao() {
        Connection conn = getConexao();
        if (conn != null) {
            System.out.println("Conexão com banco de dados OK!");
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Falha na conexão com banco de dados!");
        }
    }
}