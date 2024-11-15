package command;
import java.util.List;
import business.BMantenimiento;

public class HandleMantenimiento {

    public static String save(String params) {
        String response = "";
        String[] parts = params.split(", ");
        if (parts.length == 2) {
            float costo = Float.parseFloat(parts[0]);
            String tipo = parts[1];
            BMantenimiento mantenimiento = new BMantenimiento();
            response = mantenimiento.save(costo, tipo);
        } else {
            response = "HandleMantenimiento.java dice: Ocurrió un error al ejecutar el método save (parámetros incorrectos)";
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            float costo = Float.parseFloat(parts[1]);
            String tipo = parts[2];
            BMantenimiento mantenimiento = new BMantenimiento();
            response = mantenimiento.update(id, costo, tipo);
        } else {
            response = "HandleMantenimiento.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToDelete = Integer.parseInt(params);
            BMantenimiento mantenimiento = new BMantenimiento();
            response = mantenimiento.delete(idToDelete);
        } else {
            response = "HandleMantenimiento.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
        }
        return response;
    }

    public static String findAll() {
        BMantenimiento mantenimiento = new BMantenimiento();
        List<String[]> mantenimientos = mantenimiento.findAll();
        return mantenimientos != null ? concatenate(mantenimientos) : "Mantenimientos vacíos";
    }

    public static String findOne(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToFind = Integer.parseInt(params);
            BMantenimiento mantenimiento = new BMantenimiento();
            String[] mantenimientoFound = mantenimiento.findOne(idToFind);
            response = java.util.Arrays.toString(mantenimientoFound);
        } else {
            response = "HandleMantenimiento.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
        }
        return response;
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, \\d+\\.\\d+, [a-zA-Z]+$";
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
