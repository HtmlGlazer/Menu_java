import java.time.LocalDateTime;

public class ReservaEspecial extends Reserva {
    
    // Constantes
    public static final String EVENTO_ANIVERSARIO = "Aniversário";
    public static final String EVENTO_CASAMENTO = "Casamento";
    public static final String EVENTO_CORPORATIVO = "Corporativo";
    public static final String EVENTO_FORMATURA = "Formatura";

    // Atributos
    private String tipoEvento;
    private boolean decoracaoEspecial;

    // Construtores
    public ReservaEspecial() {
        super();
    }

    public ReservaEspecial(int id, LocalDateTime dataHora, String nomeCliente,
                           int quantidadePessoas, int numeroMesa, String status,
                           String tipoEvento, boolean decoracaoEspecial) {
        super(id, dataHora, nomeCliente, quantidadePessoas, numeroMesa, status);
        this.tipoEvento = tipoEvento;
        this.decoracaoEspecial = decoracaoEspecial;
    }

    // Getters e Setters
    public String getTipoEvento() {
        return tipoEvento;  
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public boolean isDecoracaoEspecial() {
        return decoracaoEspecial;
    }

    public void setDecoracaoEspecial(boolean decoracaoEspecial) {
        this.decoracaoEspecial = decoracaoEspecial;
    }

    // Métodos
    @Override
    public String toString() {
        return String.format("Reserva #%d | %s | Cliente: %s | %d pessoa%s | Mesa %d | Status: %s | Evento: %s | Decoração: %s",
                           getId(),
                           getDataHoraFormatada(),
                           getNomeCliente(),
                           getQuantidadePessoas(),
                           getQuantidadePessoas() != 1 ? "s" : "",
                           getNumeroMesa(),
                           getStatus(),
                           tipoEvento,
                           decoracaoEspecial ? "Sim" : "Não");
    }
}