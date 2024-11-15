package business;

import java.sql.SQLException;
import java.util.List;

import data.DRol;

public class BRol {
    private DRol dRol;

    public BRol() {
        this.dRol = new DRol();
    }

    public String save(String nombre) {
        try {
            return dRol.save(nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El rol no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String nombre) {
        try {
            return dRol.update(id, nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El rol no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dRol.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El rol no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dRol.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dRol.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
