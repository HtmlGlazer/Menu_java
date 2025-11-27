package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;
import model.ReservaEspecial;
import util.ConexaoDB;

/**
 * Classe responsável por gerenciar as operações de Reservas no banco de dados
 * DAO = Data Access Object (Objeto de Acesso a Dados)
 */
public class ReservaDAO {
    
    /**
     * Cadastra uma nova reserva no banco de dados
     * Pode ser uma Reserva Normal ou Reserva Especial
     * Insere em 1 ou 2 tabelas: reservas (sempre) e reservas_especiais (se for especial)
     */
    public void cadastrar(Reserva reserva) {
        // SQL para inserir na tabela reservas
        String sqlReserva = "INSERT INTO reservas (data_hora, nome_cliente, quantidade_pessoas, numero_mesa, status, tipo_reserva) VALUES (?, ?, ?, ?, ?, ?)";
        
        // SQL para inserir na tabela reservas_especiais (só se for especial)
        String sqlEspecial = "INSERT INTO reservas_especiais (reserva_id, tipo_evento, decoracao_especial) VALUES (?, ?, ?)";
        
        // Cria variável para guardar a conexão
        Connection conn = null;
        
        try {
            // Abre conexão com o banco de dados
            conn = ConexaoDB.getConexao();
            
            // Desliga o commit automático para evitar erros
            conn.setAutoCommit(false);
            
            // Inserir na tabela reservas
            
            // Prepara o comando SQL
            PreparedStatement stmtReserva = conn.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS);
            
            // Substitui os "?" pelos valores reais
            stmtReserva.setTimestamp(1, Timestamp.valueOf(reserva.getDataHora()));  // data_hora
            stmtReserva.setString(2, reserva.getNomeCliente());                     // nome_cliente
            stmtReserva.setInt(3, reserva.getQuantidadePessoas());                  // quantidade_pessoas
            stmtReserva.setInt(4, reserva.getNumeroMesa());                         // numero_mesa
            stmtReserva.setString(5, reserva.getStatus());                          // status
            
            // Verifica se é reserva especial ou normal
            if (reserva instanceof ReservaEspecial) {
                stmtReserva.setString(6, "especial");  // tipo_reserva = "especial"
            } else {
                stmtReserva.setString(6, "normal");    // tipo_reserva = "normal"
            }
            
            // Executa o INSERT na tabela reservas
            stmtReserva.executeUpdate();
            
            // Pegar o ID que foi gerado
            
            ResultSet rs = stmtReserva.getGeneratedKeys();
            int reservaId = 0;
            
            // Se conseguiu pegar o ID gerado
            if (rs.next()) {
                reservaId = rs.getInt(1);
                reserva.setId(reservaId);
            }
            
            // Se for Reserva Especial, inserir também na tabela reservas_especiais
            
            if (reserva instanceof ReservaEspecial) {
                // Configuração para ReservaEspecial
                ReservaEspecial especial = (ReservaEspecial) reserva;
                
                PreparedStatement stmtEspecial = conn.prepareStatement(sqlEspecial);
                
                // Substitui os "?" pelos valores
                stmtEspecial.setInt(1, reservaId);                          // reserva_id (FK)
                stmtEspecial.setString(2, especial.getTipoEvento());        // tipo_evento
                stmtEspecial.setBoolean(3, especial.isDecoracaoEspecial()); // decoracao_especial
                
                // Executa o INSERT na tabela reservas_especiais
                stmtEspecial.executeUpdate();
            }
            
            // Se chegou aqui sem erros, confirma tudo no banco
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
     * Busca todas as reservas cadastradas no banco de dados
     * @return Lista com todas as reservas (normais e especiais)
     */
    public List<Reserva> consultarTodos() {
        // Cria lista vazia para guardar as reservas
        List<Reserva> reservas = new ArrayList<>();
        
        // SQL que junta as duas tabelas (LEFT JOIN porque nem toda reserva é especial)
        String sql = "SELECT r.id, r.data_hora, r.nome_cliente, r.quantidade_pessoas, r.numero_mesa, r.status, r.tipo_reserva, " +
                     "e.tipo_evento, e.decoracao_especial " +
                     "FROM reservas r " +
                     "LEFT JOIN reservas_especiais e ON r.id = e.reserva_id " +
                     "ORDER BY r.data_hora";
        
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
                // Pega o tipo da reserva para saber qual objeto criar
                String tipoReserva = rs.getString("tipo_reserva");
                
                // Se for reserva especial, cria objeto ReservaEspecial
                if ("especial".equals(tipoReserva)) {
                    ReservaEspecial reservaEspecial = new ReservaEspecial(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),  // Converte Timestamp para LocalDateTime
                        rs.getString("nome_cliente"),
                        rs.getInt("quantidade_pessoas"),
                        rs.getInt("numero_mesa"),
                        rs.getString("status"),
                        rs.getString("tipo_evento"),
                        rs.getBoolean("decoracao_especial")
                    );
                    reservas.add(reservaEspecial);
                    
                } else {
                    // Se for reserva normal, cria objeto Reserva
                    Reserva reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),  // Converte Timestamp para LocalDateTime
                        rs.getString("nome_cliente"),
                        rs.getInt("quantidade_pessoas"),
                        rs.getInt("numero_mesa"),
                        rs.getString("status")
                    );
                    reservas.add(reserva);
                }
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
        
        // Retorna a lista de reservas
        return reservas;
    }
    
    /**
     * Atualiza os dados de uma reserva já cadastrada
     * Atualiza em 1 ou 2 tabelas: reservas (sempre) e reservas_especiais (se for especial)
     */
    public void atualizar(Reserva reserva) {
        // SQL para atualizar a tabela reservas
        String sqlReserva = "UPDATE reservas SET data_hora = ?, nome_cliente = ?, quantidade_pessoas = ?, numero_mesa = ?, status = ? WHERE id = ?";
        
        // SQL para atualizar a tabela reservas_especiais
        String sqlEspecial = "UPDATE reservas_especiais SET tipo_evento = ?, decoracao_especial = ? WHERE reserva_id = ?";
        
        Connection conn = null;
        
        try {
            conn = ConexaoDB.getConexao();
            conn.setAutoCommit(false);
            
            // Atualizar reservas
            
            PreparedStatement stmtReserva = conn.prepareStatement(sqlReserva);
            
            // Substitui os "?" pelos valores
            stmtReserva.setTimestamp(1, Timestamp.valueOf(reserva.getDataHora()));
            stmtReserva.setString(2, reserva.getNomeCliente());
            stmtReserva.setInt(3, reserva.getQuantidadePessoas());
            stmtReserva.setInt(4, reserva.getNumeroMesa());
            stmtReserva.setString(5, reserva.getStatus());
            stmtReserva.setInt(6, reserva.getId());  // WHERE id = ?
            
            // Executa o UPDATE
            stmtReserva.executeUpdate();
            
            // Se for Reserva Especial, atualizar também na tabela reservas_especiais
            
            if (reserva instanceof ReservaEspecial) {
                // Configuração para ReservaEspecial
                ReservaEspecial especial = (ReservaEspecial) reserva;
                
                PreparedStatement stmtEspecial = conn.prepareStatement(sqlEspecial);
                
                // Substitui os "?" pelos valores
                stmtEspecial.setString(1, especial.getTipoEvento());
                stmtEspecial.setBoolean(2, especial.isDecoracaoEspecial());
                stmtEspecial.setInt(3, reserva.getId());  // WHERE reserva_id = ?
                
                // Executa o UPDATE
                stmtEspecial.executeUpdate();
            }
            
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
     * Remove uma reserva do banco de dados
     * O CASCADE no banco apaga automaticamente da tabela reservas_especiais também
     */
    public void eliminar(int id) {
        // SQL para deletar
        String sql = "DELETE FROM reservas WHERE id = ?";
        
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