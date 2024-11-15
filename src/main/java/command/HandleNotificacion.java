package command;

import business.BNotificacion;
import java.util.List;

public class HandleNotificacion {

    public static String save(String params) {
        String response = "";
        if (params != null && !params.isEmpty()) {
            BNotificacion notificacion = new BNotificacion();
            response = notificacion.save(params);
        } else {
            response = "HandleNotificacion.java dice: Ocurrió un error al ejecutar el método save (parámetros incorrectos)";
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String tipo = parts[1];
            BNotificacion notificacion = new BNotificacion();
            response = notificacion.update(id, tipo);
        } else {
            response = "HandleNotificacion.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToDelete = Integer.parseInt(params);
            BNotificacion notificacion = new BNotificacion();
            response = notificacion.delete(idToDelete);
        } else {
            response = "HandleNotificacion.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
        }
        return response;
    }

    public static String findAll() {
        BNotificacion notificacion = new BNotificacion();
        List<String[]> notificaciones = notificacion.findAll();
        return notificaciones != null ? concatenate(notificaciones) : "Notificaciones vacías";
    }

    public static String findOne(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToFind = Integer.parseInt(params);
            BNotificacion notificacion = new BNotificacion();
            String[] notificacionFound = notificacion.findOne(idToFind);
            response = java.util.Arrays.toString(notificacionFound);
        } else {
            response = "HandleNotificacion.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
        }
        return response;
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, [a-zA-Z]+$";
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
