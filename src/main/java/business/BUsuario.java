package business;

import java.sql.SQLException;
import java.util.List;

public class BUsuario {
    private DUsuario dUsuario;

    public BUsuario(){
        this.dUsuario = new DUsuario();
    }

    public String save(String apellido, String ci, String direccion, String email, String fechaNacimiento, String nombre, String password, int rolId) {
        try {
            return dUsuario.save(apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Usuario no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String apellido, String ci, String direccion, String email, String fechaNacimiento, String nombre, String password, int rolId) {
        try {
            return dUsuario.update(id, apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId);
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
