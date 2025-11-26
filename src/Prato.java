public class Prato extends ItemCardapio {  // ✅ extends, não implements!
    
    // CONSTANTES
    public static final String CATEGORIA_ENTRADA = "Entrada";
    public static final String CATEGORIA_PRINCIPAL = "Prato Principal";
    public static final String CATEGORIA_ACOMPANHAMENTO = "Acompanhamento";

    public static final String TAMANHO_P = "P";
    public static final String TAMANHO_M = "M";
    public static final String TAMANHO_G = "G";
    // ATRIBUTOS
    private String categoria;
    private String tamanho;
    private int tempoPreparo;  
    // CONSTRUTORES
    public Prato() {
        super();
    } 
    public Prato(int id, String nome, String descricao, double preco, 
                 String categoria, String tamanho, int tempoPreparo) {
        super(id, nome, descricao, preco);  
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.tempoPreparo = tempoPreparo;
    }   
    // GETTERS E SETTERS
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }
    
    // MÉTODOS
    @Override
    public double calcularPrecoFinal() {
        double precoFinal = getPreco(); 
        
        switch (tamanho) {
            case TAMANHO_P:
                precoFinal *= 0.8;  
            case TAMANHO_M:
                precoFinal *= 1.0;
            case TAMANHO_G:
                precoFinal *= 1.3;  
            default:
        }        
        return precoFinal;  
    }
    
    @Override
    public String getTipo() {
        return "Prato";
    }
    
    @Override
    public String toString() {
        return super.toString() + " | " + categoria + " | Tamanho: " + tamanho;
    }


}