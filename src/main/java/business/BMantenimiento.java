package business;

import java.sql.SQLException;
import java.util.List;

import data.DMantenimiento;

public class BMantenimiento {
    private DMantenimiento dMantenimiento;

    public BMantenimiento() {
        this.dMantenimiento = new DMantenimiento();
    }

    public String save(float costo, String tipo) {
        try {
            return dMantenimiento.save(costo, tipo);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El mantenimiento no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, float costo, String tipo) {
        try {
            return dMantenimiento.update(id, costo, tipo);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El mantenimiento no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dMantenimiento.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El mantenimiento no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dMantenimiento.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dMantenimiento.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
