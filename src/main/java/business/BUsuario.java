package business;

import java.sql.SQLException;
import java.util.List;
import data.DUsuario;

public class BUsuario {

    private DUsuario dUsuario;

    public BUsuario() {
        this.dUsuario = new DUsuario();
    }

    public String save(String nombre, String apellido, String ci, String direccionDomicilio, String email, String fechaNacimiento, String password, int rolId, Integer garanteId) {
        try {
            return dUsuario.save(nombre, apellido, ci, direccionDomicilio, email, fechaNacimiento, password, rolId, garanteId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Usuario no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String nombre, String apellido, String ci, String direccionDomicilio, String email, String fechaNacimiento, String password, int rolId, Integer garanteId) {
        try {
            return dUsuario.update(id, nombre, apellido, ci, direccionDomicilio, email, fechaNacimiento, password, rolId, garanteId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Usuario no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dUsuario.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Usuario no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dUsuario.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dUsuario.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
