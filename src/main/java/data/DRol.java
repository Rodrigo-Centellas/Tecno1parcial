package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DRol {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DRol() {
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

    public String save(String nombre) throws SQLException {
        String query = "INSERT INTO rol(nombre) VALUES(?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, nombre);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DRol.java dice: El rol no se pudo insertar");
            throw new SQLException();
        }
        return "El rol se inserto con exito";
    }

    public String update(int id, String nombre) throws SQLException {
        String query = "UPDATE rol SET nombre=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DRol.java dice: El rol no se pudo actualizar");
            return "El rol no se pudo actualizar";
        }
        return "El rol se actualizo con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM rol WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DRol.java dice: El rol no se pudo eliminar");
            return "El rol no se pudo eliminar";
        }
        return "El rol se elimino con exito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> roles = new ArrayList<>();
        String query = "SELECT * FROM rol";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            roles.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("nombre")
            });
        }
        return roles;
    }

    public String[] findOne(int id) throws SQLException {
        String[] rol = null;
        String query = "SELECT * FROM rol WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            rol = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("nombre")
            };
        }
        return rol;
    }
}
