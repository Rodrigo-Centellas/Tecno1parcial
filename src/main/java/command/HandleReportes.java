package command;

import java.util.List;
import business.BReportes;

public class HandleReportes {

    public static String generate(String params) {
        String response = "";
        String[] parts = params.split(", ");
        if (parts.length >= 1) {
            String tipoReporte = parts[0].toLowerCase();
            BReportes reportes = new BReportes();

            switch (tipoReporte) {
                case "contratos_activos":
                    List<String[]> contratosActivos = reportes.reporteContratosActivos();
                    response = contratosActivos != null ? concatenate(contratosActivos) : "No hay contratos activos";
                    break;

                case "pagos_recientes":
                    List<String[]> pagosRecientes = reportes.reportePagosRecientes();
                    response = pagosRecientes != null ? concatenate(pagosRecientes) : "No hay pagos recientes";
                    break;

                case "vehiculos_mantenimiento":
                    List<String[]> vehiculosMantenimiento = reportes.reporteVehiculosMantenimiento();
                    response = vehiculosMantenimiento != null ? concatenate(vehiculosMantenimiento) : "No hay vehículos en mantenimiento";
                    break;

                case "estadisticas_generales":
                    List<String[]> estadisticasGenerales = reportes.reporteEstadisticasGenerales();
                    response = estadisticasGenerales != null ? concatenate(estadisticasGenerales) : "No hay estadísticas disponibles";
                    break;

                default:
                    response = "Tipo de reporte no reconocido: " + tipoReporte;
            }
        } else {
            response = "HandleReportes.java dice: Ocurrió un error al ejecutar el método generate (formato incorrecto)";
        }
        return response;
    }

    public static String findOne(String params) {
        return "HandleReportes.java dice: Método findOne no implementado para reportes";
    }

    public static String findAll() {
        return "HandleReportes.java dice: Método findAll no implementado para reportes";
    }

    private static String concatenate(List<String[]> items) {
        StringBuilder concatenated = new StringBuilder();
        String delimiter = "\n";
        for (String[] item : items) {
            concatenated.append(String.join(", ", item)).append(delimiter);
        }
        return concatenated.toString();
    }
}
