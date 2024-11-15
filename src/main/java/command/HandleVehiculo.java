package command;
import java.util.List;
import business.BVehiculo;

public class HandleVehiculo {

    public static String save(String params) {
        String response = "";
        String[] parts = params.split(", ");
        if (parts.length == 3) {
            int anio = Integer.parseInt(parts[0]);
            String placa = parts[1];
            float precioCompra = Float.parseFloat(parts[2]);
            BVehiculo vehiculo = new BVehiculo();
            response = vehiculo.save(anio, placa, precioCompra);
        } else {
            response = "HandleVehiculo.java dice: Ocurrió un error al ejecutar el método save (parámetros incorrectos)";
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            int anio = Integer.parseInt(parts[1]);
            String placa = parts[2];
            float precioCompra = Float.parseFloat(parts[3]);
            BVehiculo vehiculo = new BVehiculo();
            response = vehiculo.update(id, anio, placa, precioCompra);
        } else {
            response = "HandleVehiculo.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToDelete = Integer.parseInt(params);
            BVehiculo vehiculo = new BVehiculo();
            response = vehiculo.delete(idToDelete);
        } else {
            response = "HandleVehiculo.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
        }
        return response;
    }

    public static String findAll() {
        BVehiculo vehiculo = new BVehiculo();
        List<String[]> vehiculos = vehiculo.findAll();
        return vehiculos != null ? concatenate(vehiculos) : "Vehículos vacíos";
    }

    public static String findOne(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToFind = Integer.parseInt(params);
            BVehiculo vehiculo = new BVehiculo();
            String[] vehiculoFound = vehiculo.findOne(idToFind);
            response = java.util.Arrays.toString(vehiculoFound);
        } else {
            response = "HandleVehiculo.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
        }
        return response;
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, \\d{4}, [A-Z0-9]+, \\d+\\.\\d+$";
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
