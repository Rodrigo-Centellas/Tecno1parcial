package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DUsuario {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DUsuario() {
        this.connection = new databaseConnection(
                ConfigDb.getUser(),
                ConfigDb.getPassword(),
                ConfigDb.getHost(),
                ConfigDb.getPort(),
                ConfigDb.getDbName());
    }

    public void disconnect() {
        if (connection != null)
            connection.closeConnection();
    }

    // Guardar un usuario
    public String save(String nombre, String apellido, String ci, String direccionDomicilio, String email, String fechaNacimiento, String password, int rolId, Integer garanteId) throws SQLException {
        String query = "INSERT INTO usuario(nombre, apellido, ci, direccion_domicilio, email, fecha_nacimiento, password, rol_id, garante_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, ci);
        ps.setString(4, direccionDomicilio);
        ps.setString(5, email);
        ps.setString(6, fechaNacimiento);
        ps.setString(7, password);
        ps.setInt(8, rolId);

        if (garanteId != null) {
            ps.setInt(9, garanteId);
        } else {
            ps.setNull(9, java.sql.Types.INTEGER);
        }

        if (ps.executeUpdate() == 0) {
            System.err.println("class DUsuario.java dice: El usuario no se pudo insertar");
            throw new SQLException();
        }

        return "El usuario se insertó con éxito";
    }

    // Actualizar un usuario
    public String update(int id, String nombre, String apellido, String ci, String direccionDomicilio, String email, String fechaNacimiento, String password, int rolId, Integer garanteId) throws SQLException {
        String query = "UPDATE usuario SET nombre=?, apellido=?, ci=?, direccion_domicilio=?, email=?, fecha_nacimiento=?, password=?, rol_id=?, garante_id=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, ci);
        ps.setString(4, direccionDomicilio);
        ps.setString(5, email);
        ps.setString(6, fechaNacimiento);
        ps.setString(7, password);
        ps.setInt(8, rolId);

        if (garanteId != null) {
            ps.setInt(9, garanteId);
        } else {
            ps.setNull(9, java.sql.Types.INTEGER);
        }

        ps.setInt(10, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DUsuario.java dice: El usuario no se pudo actualizar");
            return "El usuario no se pudo actualizar";
        }
        return "El usuario se actualizó con éxito";
    }

    // Eliminar un usuario
    public String delete(int id) throws SQLException {
        String query = "DELETE FROM usuario WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DUsuario.java dice: El usuario no se pudo eliminar");
            return "El usuario no se pudo eliminar";
        }
        return "El usuario se eliminó con éxito";
    }

    // Obtener todos los usuarios
    public List<String[]> findAll() throws SQLException {
        List<String[]> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();

        while (set.next()) {
            usuarios.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("nombre"),
                    set.getString("apellido"),
                    set.getString("ci"),
                    set.getString("direccion_domicilio"),
                    set.getString("email"),
                    set.getString("fecha_nacimiento"),
                    set.getString("password"),
                    String.valueOf(set.getInt("rol_id")),
                    set.getString("garante_id") != null ? String.valueOf(set.getInt("garante_id")) : null
            });
        }
        return usuarios;
    }

    // Obtener un usuario por ID
    public String[] findOne(int id) throws SQLException {
        String[] usuario = null;
        String query = "SELECT * FROM usuario WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            usuario = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("nombre"),
                    set.getString("apellido"),
                    set.getString("ci"),
                    set.getString("direccion_domicilio"),
                    set.getString("email"),
                    set.getString("fecha_nacimiento"),
                    set.getString("password"),
                    String.valueOf(set.getInt("rol_id")),
                    set.getString("garante_id") != null ? String.valueOf(set.getInt("garante_id")) : null
            };
        }
        return usuario;
    }
}
