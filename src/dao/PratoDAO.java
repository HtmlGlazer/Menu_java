package dao;

import java.util.ArrayList;
import java.util.List;
import model.Prato;

public class PratoDAO {
    private final List<Prato> listaPratos = new ArrayList<>();
    private int proximoId = 1;
    
    public void cadastrar(Prato prato) {
        prato.setId(proximoId++);
        listaPratos.add(prato);
    }
    
    public List<Prato> consultarTodos() {
        return new ArrayList<>(listaPratos);
    }
    
    public void atualizar(Prato prato) {
        for (int i = 0; i < listaPratos.size(); i++) {
            if (listaPratos.get(i).getId() == prato.getId()) {
                listaPratos.set(i, prato);
                break;
            }
        }
    }
    
    public void eliminar(int id) {
        listaPratos.removeIf(p -> p.getId() == id);
    }
}