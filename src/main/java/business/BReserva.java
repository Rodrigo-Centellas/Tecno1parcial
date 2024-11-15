package business;

import java.sql.SQLException;
import java.util.List;

import data.DReserva;

public class BReserva {
    private DReserva dReserva;

    public BReserva() {
        this.dReserva = new DReserva();
    }

    public String save(String estado, int usuarioId) {
        try {
            return dReserva.save(estado, usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La reserva no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String estado, int usuarioId) {
        try {
            return dReserva.update(id, estado, usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La reserva no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dReserva.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La reserva no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dReserva.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dReserva.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
