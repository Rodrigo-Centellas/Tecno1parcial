package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DMantenimiento {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DMantenimiento() {
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

    public String save(float costo, String tipo) throws SQLException {
        String query = "INSERT INTO mantenimiento(costo, tipo) VALUES(?, ?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setFloat(1, costo);
        ps.setString(2, tipo);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DMantenimiento.java dice: El mantenimiento no se pudo insertar");
            throw new SQLException();
        }
        return "El mantenimiento se inserto con exito";
    }

    public String update(int id, float costo, String tipo) throws SQLException {
        String query = "UPDATE mantenimiento SET costo=?, tipo=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setFloat(1, costo);
        ps.setString(2, tipo);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DMantenimiento.java dice: El mantenimiento no se pudo actualizar");
            return "El mantenimiento no se pudo actualizar";
        }
        return "El mantenimiento se actualizo con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM mantenimiento WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DMantenimiento.java dice: El mantenimiento no se pudo eliminar");
            return "El mantenimiento no se pudo eliminar";
        }
        return "El mantenimiento se elimino con exito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> mantenimientos = new ArrayList<>();
        String query = "SELECT * FROM mantenimiento";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            mantenimientos.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getFloat("costo")),
                    set.getString("tipo")
            });
        }
        return mantenimientos;
    }

    public String[] findOne(int id) throws SQLException {
        String[] mantenimiento = null;
        String query = "SELECT * FROM mantenimiento WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            mantenimiento = new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getFloat("costo")),
                    set.getString("tipo")
            };
        }
        return mantenimiento;
    }
}
