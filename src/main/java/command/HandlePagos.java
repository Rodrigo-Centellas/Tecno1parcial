package command;

import business.BPagos;
import java.util.List;

public class HandlePagos {

    public static String save(String params) {
        String response = "";
        String[] parts = params.split(", ");
        if (parts.length == 5) {
            String fechaPago = parts[0];
            String metodoPago = parts[1];
            float montoPagado = Float.parseFloat(parts[2]);
            int nroSemana = Integer.parseInt(parts[3]);
            int renta = Integer.parseInt(parts[4]);
            BPagos pagos = new BPagos();
            response = pagos.save(fechaPago, metodoPago, montoPagado, nroSemana, renta);
        } else {
            response = "HandlePagos.java dice: Ocurrió un error al ejecutar el método save (parámetros incorrectos)";
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String fechaPago = parts[1];
            String metodoPago = parts[2];
            float montoPagado = Float.parseFloat(parts[3]);
            int nroSemana = Integer.parseInt(parts[4]);
            int renta = Integer.parseInt(parts[5]);
            BPagos pagos = new BPagos();
            response = pagos.update(id, fechaPago, metodoPago, montoPagado, nroSemana, renta);
        } else {
            response = "HandlePagos.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToDelete = Integer.parseInt(params);
            BPagos pagos = new BPagos();
            response = pagos.delete(idToDelete);
        } else {
            response = "HandlePagos.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
        }
        return response;
    }

    public static String findAll() {
        BPagos pagos = new BPagos();
        List<String[]> pagosList = pagos.findAll();
        return pagosList != null ? concatenate(pagosList) : "Pagos vacíos";
    }

    public static String findOne(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToFind = Integer.parseInt(params);
            BPagos pagos = new BPagos();
            String[] pagoFound = pagos.findOne(idToFind);
            response = java.util.Arrays.toString(pagoFound);
        } else {
            response = "HandlePagos.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
        }
        return response;
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, \\d{4}-\\d{2}-\\d{2}, [a-zA-Z]+, \\d+\\.\\d+, \\d+, \\d+$";
        return input.matches(regex);
    }

    public static boolean isOnlyNumbers(String cadena) {
        return cadena.matches("^[0-9]+$");
    }

    private static String concatenate(List<String[]> items) {
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
