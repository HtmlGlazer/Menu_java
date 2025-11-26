public class Mesa {
    
    // ============================================
    // 1. CONSTANTES
    // ============================================
    public static final String LOCALIZACAO_JANELA = "Janela";
    public static final String LOCALIZACAO_CENTRO = "Centro";
    public static final String LOCALIZACAO_VARANDA = "Varanda";
    
    // ============================================
    // 2. ATRIBUTOS
    // ============================================
    private int numero;
    private int capacidade;
    private String localizacao;
    
    // ============================================
    // 3. CONSTRUTORES
    // ============================================
    public Mesa() {
    }
    
    // Construtor completo
    public Mesa(int numero, int capacidade, String localizacao) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
    }
    
    // ============================================
    // 4. GETTERS E SETTERS
    // ============================================
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    // ============================================
    // 5. MÉTODOS ÚTEIS
    // ============================================
    
    /**
     * Verifica se a mesa comporta a quantidade de pessoas especificada
     * @param quantidadePessoas número de pessoas
     * @return true se comporta, false caso contrário
     */
    public boolean comporta(int quantidadePessoas) {
        return this.capacidade >= quantidadePessoas;
    }
    
    /**
     * Verifica se a mesa está em determinada localização
     * @param local localização a verificar
     * @return true se estiver nesta localização
     */
    public boolean estaEm(String local) {
        return this.localizacao.equalsIgnoreCase(local);
    }
    
    // ============================================
    // 6. toString
    // ============================================
    @Override
    public String toString() {
        return String.format("Mesa %d | Capacidade: %d pessoa%s | Localização: %s",
                           numero,
                           capacidade,
                           capacidade != 1 ? "s" : "",  
                           localizacao);
    }
}