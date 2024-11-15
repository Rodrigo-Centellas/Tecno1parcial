package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DReserva {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DReserva() {
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

    public String save(String estado) throws SQLException {
        String query = "INSERT INTO reserva(estado) VALUES(?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, estado);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DReserva.java dice: La reserva no se pudo insertar");
            throw new SQLException();
        }
        return "La reserva se inserto con exito";
    }

    public String update(int id, String estado) throws SQLException {
        String query = "UPDATE reserva SET estado=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, estado);
        ps.setInt(2, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DReserva.java dice: La reserva no se pudo actualizar");
            return "La reserva no se pudo actualizar";
        }
        return "La reserva se actualizo con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM reserva WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DReserva.java dice: La reserva no se pudo eliminar");
            return "La reserva no se pudo eliminar";
        }
        return "La reserva se elimino con exito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> reservas = new ArrayList<>();
        String query = "SELECT * FROM reserva";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            reservas.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("estado")
            });
        }
        return reservas;
    }

    public String[] findOne(int id) throws SQLException {
        String[] reserva = null;
        String query = "SELECT * FROM reserva WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            reserva = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("estado")
            };
        }
        return reserva;
    }
}
