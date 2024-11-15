package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DVehiculo {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DVehiculo() {
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

    public String save(int año, String placa, float precioCompra) throws SQLException {
        String query = "INSERT INTO vehiculo(año, placa, precio_compra) VALUES(?, ?, ?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, año);
        ps.setString(2, placa);
        ps.setFloat(3, precioCompra);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DVehiculo.java dice: El vehiculo no se pudo insertar");
            throw new SQLException();
        }
        return "El vehiculo se inserto con exito";
    }

    public String update(int id, int año, String placa, float precioCompra) throws SQLException {
        String query = "UPDATE vehiculo SET año=?, placa=?, precio_compra=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, año);
        ps.setString(2, placa);
        ps.setFloat(3, precioCompra);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DVehiculo.java dice: El vehiculo no se pudo actualizar");
            return "El vehiculo no se pudo actualizar";
        }
        return "El vehiculo se actualizo con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM vehiculo WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DVehiculo.java dice: El vehiculo no se pudo eliminar");
            return "El vehiculo no se pudo eliminar";
        }
        return "El vehiculo se elimino con exito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> vehiculos = new ArrayList<>();
        String query = "SELECT * FROM vehiculo";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            vehiculos.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getInt("año")),
                    set.getString("placa"),
                    String.valueOf(set.getFloat("precio_compra"))
            });
        }
        return vehiculos;
    }

    public String[] findOne(int id) throws SQLException {
        String[] vehiculo = null;
        String query = "SELECT * FROM vehiculo WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            vehiculo = new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getInt("año")),
                    set.getString("placa"),
                    String.valueOf(set.getFloat("precio_compra"))
            };
        }
        return vehiculo;
    }
}
