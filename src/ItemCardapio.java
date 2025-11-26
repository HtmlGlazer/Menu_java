public abstract class ItemCardapio {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    
    // Construtor para Integração com BD
    public ItemCardapio() {
    }

    // Construtor com parâmetros
    public ItemCardapio(int id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    // Método abstrato para calcular preço final
    public abstract double calcularPrecoFinal();
    
    // Método abstrato para obter tipo do item
    public abstract String getTipo();
    
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}


