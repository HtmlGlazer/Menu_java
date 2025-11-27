package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bebida;
import util.ConexaoDB;

    // Classe responsável por gerenciar as operações de Bebidas no banco de dados

public class BebidaDAO {
    

    // Cadastra uma nova bebida no banco de dados em 2 tabelas: itens_cardapio e bebidas
    public void cadastrar(Bebida bebida) {
        // SQL para inserir na tabela pai (itens_cardapio)
        String sqlItem = "INSERT INTO itens_cardapio (tipo, nome, descricao, preco) VALUES (?, ?, ?, ?)";
        
        // SQL para inserir na tabela filha (bebidas)
        String sqlBebida = "INSERT INTO bebidas (item_id, categoria, volume, gelada) VALUES (?, ?, ?, ?)";
        
        // Cria variável para guardar a conexão (começa vazia)
        Connection conn = null;
        
        try {
            // Abre conexão com o banco de dados
            conn = ConexaoDB.getConexao();
            
            // Desliga o commit automático (para fazer tudo de uma vez)
            conn.setAutoCommit(false);
            
            // Inserir na tabela itens_cardapio ===
            
            // Prepara o comando SQL (RETURN_GENERATED_KEYS pega o ID gerado)
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem, Statement.RETURN_GENERATED_KEYS);
            
            // Substitui os "?" pelos valores reais
            stmtItem.setString(1, "Bebida");              // tipo
            stmtItem.setString(2, bebida.getNome());      // nome
            stmtItem.setString(3, bebida.getDescricao()); // descricao
            stmtItem.setDouble(4, bebida.getPreco());     // preco
            
            // Executa o INSERT na tabela itens_cardapio
            stmtItem.executeUpdate();
            
            // Pegar o ID que foi gerado ===
            
            ResultSet rs = stmtItem.getGeneratedKeys();
            int itemId = 0;
            
            // Se conseguiu pegar o ID gerado
            if (rs.next()) {
                itemId = rs.getInt(1);           // Pega o ID
                bebida.setId(itemId);            // Guarda o ID no objeto bebida
            }
            
            // Inserir na tabela bebidas ===
            
            PreparedStatement stmtBebida = conn.prepareStatement(sqlBebida);
            
            // Substitui os "?" pelos valores
            stmtBebida.setInt(1, itemId);                    // item_id (FK)
            stmtBebida.setString(2, bebida.getCategoria());  // categoria
            stmtBebida.setInt(3, bebida.getVolume());        // volume
            stmtBebida.setBoolean(4, bebida.isGelada());     // gelada
            
            // Executa o INSERT na tabela bebidas
            stmtBebida.executeUpdate();
            
            // Se chegou aqui sem erros, confirma tudo no banco (commit)
            conn.commit();
            
        } catch (SQLException e) {
            // Se deu algum erro, desfaz tudo (rollback)
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            
        } finally {
            // Sempre fecha a conexão no final
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
     * Busca todas as bebidas cadastradas no banco de dados
     * @return Lista com todas as bebidas
     */
    public List<Bebida> consultarTodos() {
        // Cria lista vazia para guardar as bebidas
        List<Bebida> bebidas = new ArrayList<>();
        
        // SQL que junta Item cardapio e Bebidas
        String sql = "SELECT i.id, i.nome, i.descricao, i.preco, b.categoria, b.volume, b.gelada " +
                     "FROM itens_cardapio i " +
                     "INNER JOIN bebidas b ON i.id = b.item_id " +
                     "WHERE i.tipo = 'Bebida'";
        
        Connection conn = null;
        
        try {
            // Abre conexão
            conn = ConexaoDB.getConexao();
            
            // Cria statement simples (sem parâmetros)
            Statement stmt = conn.createStatement();
            
            // Executa a consulta e guarda o resultado
            ResultSet rs = stmt.executeQuery(sql);
            
            // Para cada linha retornada do banco
            while (rs.next()) {
                // Cria um objeto Bebida com os dados
                Bebida bebida = new Bebida(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getInt("volume"),
                    rs.getBoolean("gelada")
                );
                
                // Adiciona a bebida na lista
                bebidas.add(bebida);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            // Fecha a conexão
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Retorna a lista de bebidas
        return bebidas;
    }
    

    // Atualiza os dados de uma bebida já cadastrada em 2 tabelas: itens_cardapio e bebidas

    public void atualizar(Bebida bebida) {
        // SQL para atualizar a tabela pai
        String sqlItem = "UPDATE itens_cardapio SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
        
        // SQL para atualizar a tabela filha
        String sqlBebida = "UPDATE bebidas SET categoria = ?, volume = ?, gelada = ? WHERE item_id = ?";
        
        Connection conn = null;
        
        try {
            conn = ConexaoDB.getConexao();
            conn.setAutoCommit(false);
            
            // Atualizar itens_cardapio 
            
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            stmtItem.setString(1, bebida.getNome());
            stmtItem.setString(2, bebida.getDescricao());
            stmtItem.setDouble(3, bebida.getPreco());
            stmtItem.setInt(4, bebida.getId());
            stmtItem.executeUpdate();
            
            // Atualizar bebidas
            
            PreparedStatement stmtBebida = conn.prepareStatement(sqlBebida);
            stmtBebida.setString(1, bebida.getCategoria());
            stmtBebida.setInt(2, bebida.getVolume());
            stmtBebida.setBoolean(3, bebida.isGelada());
            stmtBebida.setInt(4, bebida.getId());
            stmtBebida.executeUpdate();
            
            // Confirma as alterações
            conn.commit();
            
        } catch (SQLException e) {
            // Se der erro, desfaz tudo
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            
        } finally {
            // Fecha conexão
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    //Remove uma bebida do banco de dados

    public void eliminar(int id) {
        // SQL para deletar (só precisa deletar da tabela pai)
        String sql = "DELETE FROM itens_cardapio WHERE id = ?";
        
        Connection conn = null;
        
        try {
            conn = ConexaoDB.getConexao();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);  // Substitui o ? pelo ID
            
            // Executa o DELETE
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            // Fecha conexão
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