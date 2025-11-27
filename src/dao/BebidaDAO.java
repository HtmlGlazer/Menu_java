package dao;

import java.util.ArrayList;
import java.util.List;
import model.Bebida;

public class BebidaDAO {
    private final List<Bebida> listaBebidas = new ArrayList<>();
    private int proximoId = 1;
    
    public void cadastrar(Bebida bebida) {
        bebida.setId(proximoId++);
        listaBebidas.add(bebida);
    }
    
    public List<Bebida> consultarTodos() {
        return new ArrayList<>(listaBebidas);
    }
    
    public void atualizar(Bebida bebida) {
        for (int i = 0; i < listaBebidas.size(); i++) {
            if (listaBebidas.get(i).getId() == bebida.getId()) {
                listaBebidas.set(i, bebida);
                break;
            }
        }
    }
    
    public void eliminar(int id) {
        listaBebidas.removeIf(b -> b.getId() == id);
    }
}