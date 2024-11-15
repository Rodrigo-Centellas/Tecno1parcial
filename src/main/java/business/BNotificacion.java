package business;

import java.sql.SQLException;
import java.util.List;
import data.DNotificacion;

public class BNotificacion {
    private DNotificacion dNotificacion;

    public BNotificacion() {
        this.dNotificacion = new DNotificacion();
    }

    public String save(String tipo) {
        try {
            return dNotificacion.save(tipo);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La notificación no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String tipo) {
        try {
            return dNotificacion.update(id, tipo);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La notificación no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dNotificacion.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La notificación no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dNotificacion.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dNotificacion.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
