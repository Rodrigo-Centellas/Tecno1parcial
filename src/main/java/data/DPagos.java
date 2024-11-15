package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import PostgreSQL.databaseConnection;

public class DPagos {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DPagos() {
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

    public String save(String fechaPago, String metodoPago, float montoPagado, int nroSemana, int renta) throws SQLException {
        String query = "INSERT INTO pagos(fecha_pago, metodo_pago, monto_pagado, nro_semana, renta) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, fechaPago);
        ps.setString(2, metodoPago);
        ps.setFloat(3, montoPagado);
        ps.setInt(4, nroSemana);
        ps.setInt(5, renta);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DPagos.java dice: El pago no se pudo insertar");
            throw new SQLException();
        }
        return "El pago se insertó con éxito";
    }

    public String update(int id, String fechaPago, String metodoPago, float montoPagado, int nroSemana, int renta) throws SQLException {
        String query = "UPDATE pagos SET fecha_pago=?, metodo_pago=?, monto_pagado=?, nro_semana=?, renta=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, fechaPago);
        ps.setString(2, metodoPago);
        ps.setFloat(3, montoPagado);
        ps.setInt(4, nroSemana);
        ps.setInt(5, renta);
        ps.setInt(6, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DPagos.java dice: El pago no se pudo actualizar");
            return "El pago no se pudo actualizar";
        }
        return "El pago se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM pagos WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DPagos.java dice: El pago no se pudo eliminar");
            return "El pago no se pudo eliminar";
        }
        return "El pago se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> pagos = new ArrayList<>();
        String query = "SELECT * FROM pagos";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            pagos.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("fecha_pago"),
                    set.getString("metodo_pago"),
                    String.valueOf(set.getFloat("monto_pagado")),
                    String.valueOf(set.getInt("nro_semana")),
                    String.valueOf(set.getInt("renta"))
            });
        }
        return pagos;
    }

    public String[] findOne(int id) throws SQLException {
        String[] pago = null;
        String query = "SELECT * FROM pagos WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            pago = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("fecha_pago"),
                    set.getString("metodo_pago"),
                    String.valueOf(set.getFloat("monto_pagado")),
                    String.valueOf(set.getInt("nro_semana")),
                    String.valueOf(set.getInt("renta"))
            };
        }
        return pago;
    }
}
