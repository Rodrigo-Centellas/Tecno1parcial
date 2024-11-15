package business;

import java.sql.SQLException;
import java.util.List;

import data.DVehiculo;

public class BVehiculo {
    private DVehiculo dVehiculo;

    public BVehiculo() {
        this.dVehiculo = new DVehiculo();
    }

    public String save(int anio, String placa, float precioCompra) {
        try {
            return dVehiculo.save(anio, placa, precioCompra);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El vehículo no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, int anio, String placa, float precioCompra) {
        try {
            return dVehiculo.update(id, anio, placa, precioCompra);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El vehículo no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dVehiculo.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El vehículo no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dVehiculo.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dVehiculo.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
