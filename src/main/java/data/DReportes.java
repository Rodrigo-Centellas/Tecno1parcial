package data;

import PostgreSQL.databaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DReportes {
    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DReportes() {
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

    // Obtiene un reporte de contratos activos
    public List<String[]> obtenerContratosActivos() throws SQLException {
        List<String[]> reporte = new ArrayList<>();
        String query = "SELECT * FROM contrato WHERE estado = 'Activo'";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            reporte.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("estado"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_fin"),
                    String.valueOf(rs.getFloat("renta")),
                    String.valueOf(rs.getInt("usuario_id")),
                    String.valueOf(rs.getInt("vehiculo_id"))
            });
        }
        return reporte;
    }

    // Obtiene un reporte de pagos realizados en el último mes
    public List<String[]> obtenerPagosRecientes() throws SQLException {
        List<String[]> reporte = new ArrayList<>();
        String query = "SELECT * FROM pagos WHERE fecha_pago >= CURRENT_DATE - INTERVAL '1 month'";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            reporte.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("fecha_pago"),
                    rs.getString("metodo_pago"),
                    String.valueOf(rs.getFloat("monto_pagado")),
                    String.valueOf(rs.getInt("nro_semana")),
                    String.valueOf(rs.getInt("renta"))
            });
        }
        return reporte;
    }

    // Obtiene un reporte de vehículos actualmente en mantenimiento
    public List<String[]> obtenerVehiculosEnMantenimiento() throws SQLException {
        List<String[]> reporte = new ArrayList<>();
        String query = "SELECT v.id, v.placa, m.tipo, m.costo, mv.fecha_mantenimiento " +
                "FROM vehiculo v " +
                "JOIN mantenimiento_vehiculo mv ON v.id = mv.vehiculo_id " +
                "JOIN mantenimiento m ON mv.mantenimiento_id = m.id " +
                "WHERE mv.fecha_mantenimiento >= CURRENT_DATE - INTERVAL '1 month'";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            reporte.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("placa"),
                    rs.getString("tipo"),
                    String.valueOf(rs.getFloat("costo")),
                    rs.getString("fecha_mantenimiento")
            });
        }
        return reporte;
    }

    // Obtiene un reporte de estadísticas generales (por ejemplo, conteo de usuarios, vehículos, etc.)
    public List<String[]> obtenerEstadisticasGenerales() throws SQLException {
        List<String[]> estadisticas = new ArrayList<>();
        String query = "SELECT 'Usuarios' AS tipo, COUNT(*) AS cantidad FROM usuario " +
                "UNION " +
                "SELECT 'Vehículos', COUNT(*) FROM vehiculo " +
                "UNION " +
                "SELECT 'Contratos', COUNT(*) FROM contrato " +
                "UNION " +
                "SELECT 'Pagos', COUNT(*) FROM pagos";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            estadisticas.add(new String[] {
                    rs.getString("tipo"),
                    String.valueOf(rs.getInt("cantidad"))
            });
        }
        return estadisticas;
    }
}
