package dao;

import java.util.ArrayList;
import java.util.List;
import model.Mesa;

public class MesaDAO {
    private final List<Mesa> listaMesas = new ArrayList<>();
    
    public void cadastrar(Mesa mesa) {
        listaMesas.add(mesa);
    }
    
    public List<Mesa> consultarTodos() {
        return new ArrayList<>(listaMesas);
    }
    
    public void atualizar(Mesa mesa) {
        for (int i = 0; i < listaMesas.size(); i++) {
            if (listaMesas.get(i).getNumero() == mesa.getNumero()) {
                listaMesas.set(i, mesa);
                break;
            }
        }
    }
    
    public void eliminar(int numero) {
        listaMesas.removeIf(m -> m.getNumero() == numero);
    }
}