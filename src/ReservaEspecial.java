import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservaEspecial extends Reserva{
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
        return this.tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public boolean isDecoracaoEspecial() {
        return this.decoracaoEspecial;
    }

    public boolean getDecoracaoEspecial() {
        return this.decoracaoEspecial;
    }

    public void setDecoracaoEspecial(boolean decoracaoEspecial) {
        this.decoracaoEspecial = decoracaoEspecial;
    }

    // Métodos
    //Formata data e hora para exibição (dd/MM/yyyy HH:mm)
    @Override
    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }
}
