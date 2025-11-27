package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    
    // Constantes
    public static final String STATUS_ATIVA = "Ativa";
    public static final String STATUS_CANCELADA = "Cancelada";
    public static final String STATUS_FINALIZADA = "Finalizada";
    
    // Atributos
    private int id;
    private LocalDateTime dataHora;
    private String nomeCliente;
    private int quantidadePessoas;
    private int numeroMesa;
    private String status;
    
    // Construtores
    public Reserva() {
    }
    
    public Reserva(int id, LocalDateTime dataHora, String nomeCliente, 
                   int quantidadePessoas, int numeroMesa, String status) {
        this.id = id;
        this.dataHora = dataHora;
        this.nomeCliente = nomeCliente;
        this.quantidadePessoas = quantidadePessoas;
        this.numeroMesa = numeroMesa;
        this.status = status;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }
    
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }
    
    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
    
    public int getNumeroMesa() {
        return numeroMesa;
    }
    
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Métodos
    
    
    //Formata data e hora para exibição (dd/MM/yyyy HH:mm)
    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }
    
    //Cancela a reserva
    public void cancelar() {
        this.status = STATUS_CANCELADA;
    }

     // Finaliza a reserva
    public void finalizar() {
        this.status = STATUS_FINALIZADA;
    }
    
    //Verifica se a reserva está ativa
    public boolean isAtiva() {
        return status.equals(STATUS_ATIVA);
    }

    //Verifica se a reserva foi cancelada
    public boolean isCancelada() {
        return status.equals(STATUS_CANCELADA);
    }
    
    // toString
    @Override
    public String toString() {
        return String.format("Reserva #%d | %s | Cliente: %s | %d pessoa%s | Mesa %d | Status: %s",
                           id,
                           getDataHoraFormatada(),
                           nomeCliente,
                           quantidadePessoas,
                           quantidadePessoas != 1 ? "s" : "",
                           numeroMesa,
                           status);
    }
}