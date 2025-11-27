package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Mesa;
import util.ConexaoDB;

/**
 * Classe responsável por fazer operações no banco de dados relacionadas a Mesas
 * DAO = Data Access Object (Objeto de Acesso a Dados)
 */
public class MesaDAO {
    
    /**
     * Cadastra uma nova mesa no banco de dados
     * @param mesa - objeto Mesa com os dados a serem salvos
     */
    public void cadastrar(Mesa mesa) {
        // Comando SQL para inserir uma nova mesa
        String sql = "INSERT INTO mesas (numero, capacidade, localizacao) VALUES (?, ?, ?)";
        
        // Variável para guardar a conexão com o banco (começa vazia)
        Connection conn = null;
        
        try {
            // 1. Abre a conexão com o banco de dados
            conn = ConexaoDB.getConexao();
            
            // 2. Prepara o comando SQL e substitui os "?" pelos valores
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mesa.getNumero());
            stmt.setInt(2, mesa.getCapacidade());
            stmt.setString(3, mesa.getLocalizacao());
            
            // 3. Executa o comando no banco (salva a mesa)
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            // Se der algum erro, mostra na tela
            e.printStackTrace();
            
        } finally {
            // Sempre fecha a conexão, mesmo se der erro
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Busca todas as mesas cadastradas no banco de dados
     * @return Lista com todas as mesas encontradas
     */
    public List<Mesa> consultarTodos() {
        // Lista que vai guardar as mesas do banco
        List<Mesa> mesas = new ArrayList<>();
        
        // Comando SQL para buscar todas as mesas ordenadas por número
        String sql = "SELECT numero, capacidade, localizacao FROM mesas ORDER BY numero";
        
        // Variável para guardar a conexão com o banco
        Connection conn = null;
        
        try {
            // 1. Abre a conexão com o banco
            conn = ConexaoDB.getConexao();
            
            // 2. Cria o comando SQL
            Statement stmt = conn.createStatement();
            
            // 3. Executa o comando e guarda os resultados
            ResultSet rs = stmt.executeQuery(sql);
            
            // 4. Percorre cada linha retornada pelo banco
            while (rs.next()) {
                // Cria um objeto Mesa com os dados de cada linha
                Mesa mesa = new Mesa(
                    rs.getInt("numero"),           // Pega o número da mesa
                    rs.getInt("capacidade"),       // Pega a capacidade
                    rs.getString("localizacao")    // Pega a localização
                );
                // Adiciona a mesa na lista
                mesas.add(mesa);
            }
            
        } catch (SQLException e) {
            // Se der erro, mostra na tela
            e.printStackTrace();
            
        } finally {
            // Sempre fecha a conexão
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Retorna a lista com todas as mesas
        return mesas;
    }
    
    /**
     * Atualiza os dados de uma mesa existente no banco
     * @param mesa - objeto Mesa com os novos dados
     */
    public void atualizar(Mesa mesa) {
        // Comando SQL para atualizar a mesa (busca pelo número)
        String sql = "UPDATE mesas SET capacidade = ?, localizacao = ? WHERE numero = ?";
        
        // Variável para guardar a conexão
        Connection conn = null;
        
        try {
            // 1. Abre a conexão
            conn = ConexaoDB.getConexao();
            
            // 2. Prepara o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mesa.getCapacidade());       // Nova capacidade
            stmt.setString(2, mesa.getLocalizacao());   // Nova localização
            stmt.setInt(3, mesa.getNumero());           // Número da mesa a atualizar
            
            // 3. Executa a atualização
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            // Se der erro, mostra na tela
            e.printStackTrace();
            
        } finally {
            // Sempre fecha a conexão
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Remove uma mesa do banco de dados
     * @param numero - número da mesa a ser deletada
     */
    public void eliminar(int numero) {
        // Comando SQL para deletar a mesa
        String sql = "DELETE FROM mesas WHERE numero = ?";
        
        // Variável para guardar a conexão
        Connection conn = null;
        
        try {
            // 1. Abre a conexão
            conn = ConexaoDB.getConexao();
            
            // 2. Prepara o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, numero);  // Número da mesa a deletar
            
            // 3. Executa a exclusão
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            // Se der erro, mostra na tela
            e.printStackTrace();
            
        } finally {
            // Sempre fecha a conexão
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}