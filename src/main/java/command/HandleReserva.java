package command;
import java.util.List;
import business.BReserva;

public class HandleReserva {

    public static String save(String params) {
        String response = "";
        if (params instanceof String) {
            BReserva reserva = new BReserva();
            response = reserva.save(params);
        } else {
            response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método save (parámetro incorrecto)";
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String estado = parts[1];
            BReserva reserva = new BReserva();
            response = reserva.update(id, estado);
        } else {
            response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToDelete = Integer.parseInt(params);
            BReserva reserva = new BReserva();
            response = reserva.delete(idToDelete);
        } else {
            response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
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
        if (isOnlyNumbers(params)) {
            int idToFind = Integer.parseInt(params);
            BReserva reserva = new BReserva();
            String[] reservaFound = reserva.findOne(idToFind);
            response = java.util.Arrays.toString(reservaFound);
        } else {
            response = "HandleReserva.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
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
