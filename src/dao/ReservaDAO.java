package dao;

import java.util.ArrayList;
import java.util.List;
import model.Reserva;

public class ReservaDAO {
    private final List<Reserva> listaReservas = new ArrayList<>();
    private int proximoId = 1;
    
    public void cadastrar(Reserva reserva) {
        reserva.setId(proximoId++);
        listaReservas.add(reserva);
    }
    
    public List<Reserva> consultarTodos() {
        return new ArrayList<>(listaReservas);
    }
    
    public void atualizar(Reserva reserva) {
        for (int i = 0; i < listaReservas.size(); i++) {
            if (listaReservas.get(i).getId() == reserva.getId()) {
                listaReservas.set(i, reserva);
                break;
            }
        }
    }
    
    public void eliminar(int id) {
        listaReservas.removeIf(r -> r.getId() == id);
    }
}