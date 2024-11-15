package command;
import java.util.List;
import business.BContrato;

public class HandleContrato {

    public static String save(String params) {
        String response = "";
        String[] parts = params.split(", ");
        if (parts.length == 6) {
            String estado = parts[0];
            String fechaInicio = parts[1];
            String fechaFin = parts[2];
            float renta = Float.parseFloat(parts[3]);
            int usuarioId = Integer.parseInt(parts[4]);
            int vehiculoId = Integer.parseInt(parts[5]);
            BContrato contrato = new BContrato();
            response = contrato.save(estado, fechaInicio, fechaFin, renta, usuarioId, vehiculoId);
        } else {
            response = "HandleContrato.java dice: Ocurrió un error al ejecutar el método save (parámetros incorrectos)";
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String estado = parts[1];
            String fechaInicio = parts[2];
            String fechaFin = parts[3];
            float renta = Float.parseFloat(parts[4]);
            BContrato contrato = new BContrato();
            response = contrato.update(id, estado, fechaInicio, fechaFin, renta);
        } else {
            response = "HandleContrato.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToDelete = Integer.parseInt(params);
            BContrato contrato = new BContrato();
            response = contrato.delete(idToDelete);
        } else {
            response = "HandleContrato.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
        }
        return response;
    }

    public static String findAll() {
        BContrato contrato = new BContrato();
        List<String[]> contratos = contrato.findAll();
        return contratos != null ? concatenate(contratos) : "Contratos vacíos";
    }

    public static String findOne(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToFind = Integer.parseInt(params);
            BContrato contrato = new BContrato();
            String[] contratoFound = contrato.findOne(idToFind);
            response = java.util.Arrays.toString(contratoFound);
        } else {
            response = "HandleContrato.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
        }
        return response;
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, [a-zA-Z]+, \\d{4}-\\d{2}-\\d{2}, \\d{4}-\\d{2}-\\d{2}, \\d+\\.\\d+$";
        return input.matches(regex);
    }

    public static boolean isOnlyNumbers(String cadena) {
        return cadena.matches("^[0-9]+$");
    }

    public static String concatenate(List<String[]> items) {
        StringBuilder concatenated = new StringBuilder();
        String delimiter = ", ";
        for (String[] itemArray : items) {
            for (String item : itemArray) {
                if (concatenated.length() > 0) {
                    concatenated.append(delimiter);
                }
                concatenated.append(item);
            }
        }
        return concatenated.toString();
    }
}
