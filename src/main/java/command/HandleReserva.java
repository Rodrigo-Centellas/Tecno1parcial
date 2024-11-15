package command;

import java.util.List;
import business.BReserva;

public class HandleReserva {

    public static String save(String params) {
        String response = "";
        try {
            String[] parts = params.split(", ");
            if (parts.length == 2) {
                String estado = parts[0];
                int usuarioId = Integer.parseInt(parts[1]);
                BReserva reserva = new BReserva();
                response = reserva.save(estado, usuarioId);
            } else {
                response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método save (parámetros incorrectos)";
            }
        } catch (Exception e) {
            response = "HandleReserva.java dice: Error en el método save - " + e.getMessage();
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        try {
            String[] parts = params.split(", ");
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                String estado = parts[1];
                int usuarioId = Integer.parseInt(parts[2]);
                BReserva reserva = new BReserva();
                response = reserva.update(id, estado, usuarioId);
            } else {
                response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
            }
        } catch (Exception e) {
            response = "HandleReserva.java dice: Error en el método update - " + e.getMessage();
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        try {
            if (isOnlyNumbers(params)) {
                int idToDelete = Integer.parseInt(params);
                BReserva reserva = new BReserva();
                response = reserva.delete(idToDelete);
            } else {
                response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
            }
        } catch (Exception e) {
            response = "HandleReserva.java dice: Error en el método delete - " + e.getMessage();
        }
        return response;
    }

    public static String findAll() {
        BReserva reserva = new BReserva();
        List<String[]> reservas = reserva.findAll();
        return reservas != null ? concatenate(reservas) : "Reservas vacías";
    }

    public static String findOne(String params) {
        String response = "";
        try {
            if (isOnlyNumbers(params)) {
                int idToFind = Integer.parseInt(params);
                BReserva reserva = new BReserva();
                String[] reservaFound = reserva.findOne(idToFind);
                response = java.util.Arrays.toString(reservaFound);
            } else {
                response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
            }
        } catch (Exception e) {
            response = "HandleReserva.java dice: Error en el método findOne - " + e.getMessage();
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
