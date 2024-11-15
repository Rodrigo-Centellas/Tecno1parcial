package business;

import java.sql.SQLException;
import java.util.List;

import data.DContrato;

public class BContrato {
    private DContrato dContrato;

    public BContrato(){
        this.dContrato = new DContrato();
    }

    public String save(String estado, String fechaInicio, String fechaFin, float renta, int usuarioId, int vehiculoId) {
        try {
            return dContrato.save(estado, fechaInicio, fechaFin, renta, usuarioId, vehiculoId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Contrato no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String estado, String fechaInicio, String fechaFin, float renta) {
        try {
            return dContrato.update(id, estado, fechaInicio, fechaFin, renta);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Contrato no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dContrato.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Contrato no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dContrato.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dContrato.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
