package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import PostgreSQL.databaseConnection;

public class DNotificacion {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DNotificacion() {
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

    public String save(String tipo) throws SQLException {
        String query = "INSERT INTO notificacion(tipo) VALUES(?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, tipo);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DNotificacion.java dice: La notificación no se pudo insertar");
            throw new SQLException();
        }
        return "La notificación se insertó con éxito";
    }

    public String update(int id, String tipo) throws SQLException {
        String query = "UPDATE notificacion SET tipo=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, tipo);
        ps.setInt(2, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DNotificacion.java dice: La notificación no se pudo actualizar");
            return "La notificación no se pudo actualizar";
        }
        return "La notificación se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM notificacion WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DNotificacion.java dice: La notificación no se pudo eliminar");
            return "La notificación no se pudo eliminar";
        }
        return "La notificación se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> notificaciones = new ArrayList<>();
        String query = "SELECT * FROM notificacion";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            notificaciones.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("tipo")
            });
        }
        return notificaciones;
    }

    public String[] findOne(int id) throws SQLException {
        String[] notificacion = null;
        String query = "SELECT * FROM notificacion WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            notificacion = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("tipo")
            };
        }
        return notificacion;
    }
}
