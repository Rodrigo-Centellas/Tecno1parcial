package business;

import data.DReportes;
import java.sql.SQLException;
import java.util.List;

public class BReportes {
    private DReportes dReportes;

    public BReportes() {
        this.dReportes = new DReportes();
    }

    // Genera un reporte de contratos activos
    public List<String[]> reporteContratosActivos() {
        try {
            return dReportes.obtenerContratosActivos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Genera un reporte de pagos realizados en el último mes
    public List<String[]> reportePagosRecientes() {
        try {
            return dReportes.obtenerPagosRecientes();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Genera un reporte de vehículos en mantenimiento
    public List<String[]> reporteVehiculosMantenimiento() {
        try {
            return dReportes.obtenerVehiculosEnMantenimiento();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Genera un reporte de estadísticas generales (ejemplo)
    public List<String[]> reporteEstadisticasGenerales() {
        try {
            return dReportes.obtenerEstadisticasGenerales();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
