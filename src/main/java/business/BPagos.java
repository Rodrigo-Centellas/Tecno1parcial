package business;

import java.sql.SQLException;
import java.util.List;
import data.DPagos;

public class BPagos {
    private DPagos dPagos;

    public BPagos() {
        this.dPagos = new DPagos();
    }

    public String save(String fechaPago, String metodoPago, float montoPagado, int nroSemana, int renta) {
        try {
            return dPagos.save(fechaPago, metodoPago, montoPagado, nroSemana, renta);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pago no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String fechaPago, String metodoPago, float montoPagado, int nroSemana, int renta) {
        try {
            return dPagos.update(id, fechaPago, metodoPago, montoPagado, nroSemana, renta);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pago no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dPagos.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pago no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dPagos.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dPagos.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
