
public class Bebida extends ItemCardapio {  // ✅ extends, não implements!
    
    // 1. CONSTANTES
    public static final String CATEGORIA_REFRIGERANTE = "Refrigerante";
    public static final String CATEGORIA_SUCO = "Suco Natural";
    public static final String CATEGORIA_ALCOOLICA = "Alcoólica";

    // 2. ATRIBUTOS
    private String categoria;  // "Refrigerante", "Suco", "Alcoólica", "Quente"
    private int volume;        // em ml (300, 500, 750 e 1000)
    private boolean gelada;
    
    // 3. CONSTRUTORES
    public Bebida() {
        super();
    }
    public Bebida(int id, String nome, String descricao, double preco,
                 String categoria, int volume, boolean gelada) {
        super(id, nome, descricao, preco);
        this.categoria = categoria;
        this.volume = volume;
        this.gelada = gelada;
    }
    // GETTERS E SETTERS
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean getGelada() {
        return gelada;
    }

    public void setGelada(boolean gelada) {
        this.gelada = gelada;
    }
    
    // 5. MÉTODOS OBRIGATÓRIOS (abstract do pai)
    @Override
    public double calcularPrecoFinal() {
        double precoFinal = getPreco();
        
        switch (volume) {
            case 300:
                precoFinal *= 0.8;
            case 500:
                precoFinal *= 1.0;
            case 750:
                precoFinal *= 1.2;
            case 1000:
                precoFinal *= 1.3;
            default:
        }

        if (gelada) {
            precoFinal += 1.0;
        }

        if (categoria.equals(CATEGORIA_ALCOOLICA)) {
            precoFinal *= 1.15; 
        }
        return precoFinal;
    }
    
    @Override
    public String getTipo() {
        return "Bebida";
    }
    
    @Override
    public String toString() {
        String temp = gelada ? " (gelada)" : " (natural)";
        return super.toString() + " | " + categoria + " | " + volume + "ml" + temp;
    }

}