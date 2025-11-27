package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Prato;
import util.ConexaoDB;

/**
 * Classe responsável por gerenciar as operações de Pratos no banco de dados
 * DAO = Data Access Object (Objeto de Acesso a Dados)
 */
public class PratoDAO {
    
    /**
     * Cadastra um novo prato no banco de dados
     * Insere dados em 2 tabelas: itens_cardapio e pratos
     */
    public void cadastrar(Prato prato) {
        // SQL para inserir na tabela pai (itens_cardapio)
        String sqlItem = "INSERT INTO itens_cardapio (tipo, nome, descricao, preco) VALUES (?, ?, ?, ?)";
        
        // SQL para inserir na tabela filha (pratos)
        String sqlPrato = "INSERT INTO pratos (item_id, categoria, tamanho, tempo_preparo) VALUES (?, ?, ?, ?)";
        
        // Cria variável para guardar a conexão
        Connection conn = null;
        
        try {
            // Abre conexão com o banco de dados
            conn = ConexaoDB.getConexao();
            
            // Desliga o commit automático para evitar erros
            conn.setAutoCommit(false);
            
            // Inserir na tabela itens_cardapio
            
            // Prepara o comando SQL
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem, Statement.RETURN_GENERATED_KEYS);
            
            // Substitui os "?" pelos valores reais
            stmtItem.setString(1, "Prato");
            stmtItem.setString(2, prato.getNome());
            stmtItem.setString(3, prato.getDescricao());
            stmtItem.setDouble(4, prato.getPreco());
            
            // Executa o INSERT na tabela itens_cardapio
            stmtItem.executeUpdate();
            
            // Pegar o ID que foi gerado
            
            ResultSet rs = stmtItem.getGeneratedKeys();
            int itemId = 0;
            
            // Se conseguiu pegar o ID gerado
            if (rs.next()) {
                itemId = rs.getInt(1);        // Pega o ID
                prato.setId(itemId);          // Guarda o ID no objeto prato
            }
            
            // Inserir na tabela pratos
            
            PreparedStatement stmtPrato = conn.prepareStatement(sqlPrato);
            
            // Substitui os "?" pelos valores
            stmtPrato.setInt(1, itemId);
            stmtPrato.setString(2, prato.getCategoria());
            stmtPrato.setString(3, prato.getTamanho());
            stmtPrato.setInt(4, prato.getTempoPreparo());
            
            // Executa o INSERT na tabela pratos
            stmtPrato.executeUpdate();
            
            // confirma tudo no banco (commit)
            conn.commit();
            
        } catch (SQLException e) {
            // Se deu algum erro, desfaz tudo
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
     * Busca todos os pratos cadastrados no banco de dados
     * @return Lista com todos os pratos
     */
    public List<Prato> consultarTodos() {
        // Cria lista vazia para guardar os pratos
        List<Prato> pratos = new ArrayList<>();
        
        // SQL que junta as duas tabelas
        String sql = "SELECT i.id, i.nome, i.descricao, i.preco, p.categoria, p.tamanho, p.tempo_preparo " +
                     "FROM itens_cardapio i " +
                     "INNER JOIN pratos p ON i.id = p.item_id " +
                     "WHERE i.tipo = 'Prato'";
        
        Connection conn = null;
        
        try {
            // Abre conexão
            conn = ConexaoDB.getConexao();
            
            // Cria statement simples 
            Statement stmt = conn.createStatement();
            
            // Executa a consulta e guarda o resultado
            ResultSet rs = stmt.executeQuery(sql);
            
            // Para cada linha retornada do banco
            while (rs.next()) {
                // Cria um objeto Prato com os dados
                Prato prato = new Prato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getString("tamanho"),
                    rs.getInt("tempo_preparo")
                );
                
                // Adiciona o prato na lista
                pratos.add(prato);
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
        
        // Retorna a lista de pratos
        return pratos;
    }
    

    // Atualiza os dados de um prato já cadastrado em 2 tabelas: itens_cardapio e pratos

    public void atualizar(Prato prato) {
        // SQL para atualizar a tabela pai
        String sqlItem = "UPDATE itens_cardapio SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
        
        // SQL para atualizar a tabela filha
        String sqlPrato = "UPDATE pratos SET categoria = ?, tamanho = ?, tempo_preparo = ? WHERE item_id = ?";
        
        Connection conn = null;
        
        try {
            conn = ConexaoDB.getConexao();
            conn.setAutoCommit(false);
            
            // Atualizar itens_cardapio
            
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            stmtItem.setString(1, prato.getNome());
            stmtItem.setString(2, prato.getDescricao());
            stmtItem.setDouble(3, prato.getPreco());
            stmtItem.setInt(4, prato.getId());           // WHERE id = ?
            stmtItem.executeUpdate();
            
            // Atualizar pratos
            
            PreparedStatement stmtPrato = conn.prepareStatement(sqlPrato);
            stmtPrato.setString(1, prato.getCategoria());
            stmtPrato.setString(2, prato.getTamanho());
            stmtPrato.setInt(3, prato.getTempoPreparo());
            stmtPrato.setInt(4, prato.getId());          // WHERE item_id = ?
            stmtPrato.executeUpdate();
            
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
    
    /**
     * Remove um prato do banco de dados
     * O CASCADE no banco apaga automaticamente da tabela pratos também
     */
    public void eliminar(int id) {
        // SQL para deletar 
        String sql = "DELETE FROM itens_cardapio WHERE id = ?";
        
        Connection conn = null;
        
        try {
            conn = ConexaoDB.getConexao();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
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