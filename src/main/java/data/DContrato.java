package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DContrato {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DContrato() {
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

    public String save(String estado, String fechaInicio, String fechaFin, float renta, int usuarioId, int vehiculoId) throws SQLException {
        String query = "INSERT INTO contrato(estado, fecha_inicio, fecha_fin, renta, usuario_id, vehiculo_id) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, estado);
        ps.setString(2, fechaInicio);
        ps.setString(3, fechaFin);
        ps.setFloat(4, renta);
        ps.setInt(5, usuarioId);
        ps.setInt(6, vehiculoId);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DContrato.java dice: El contrato no se pudo insertar");
            throw new SQLException();
        }
        return "El contrato se inserto con exito";
    }

    public String update(int id, String estado, String fechaInicio, String fechaFin, float renta) throws SQLException {
        String query = "UPDATE contrato SET estado=?, fecha_inicio=?, fecha_fin=?, renta=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, estado);
        ps.setString(2, fechaInicio);
        ps.setString(3, fechaFin);
        ps.setFloat(4, renta);
        ps.setInt(5, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DContrato.java dice: El contrato no se pudo actualizar");
            return "El contrato no se pudo actualizar";
        }
        return "El contrato se actualizo con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM contrato WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DContrato.java dice: El contrato no se pudo eliminar");
            return "El contrato no se pudo eliminar";
        }
        return "El contrato se elimino con exito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> contratos = new ArrayList<>();
        String query = "SELECT * FROM contrato";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            contratos.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("estado"),
                    set.getString("fecha_inicio"),
                    set.getString("fecha_fin"),
                    String.valueOf(set.getFloat("renta")),
                    String.valueOf(set.getInt("usuario_id")),
                    String.valueOf(set.getInt("vehiculo_id"))
            });
        }
        return contratos;
    }

    public String[] findOne(int id) throws SQLException {
        String[] contrato = null;
        String query = "SELECT * FROM contrato WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            contrato = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("estado"),
                    set.getString("fecha_inicio"),
                    set.getString("fecha_fin"),
                    String.valueOf(set.getFloat("renta")),
                    String.valueOf(set.getInt("usuario_id")),
                    String.valueOf(set.getInt("vehiculo_id"))
            };
        }
        return contrato;
    }
}
