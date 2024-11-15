package command;
import java.util.List;
import business.BUsuario;

public class HandleUsuario {

    public static String save(String params) {
        String response = "";
        String[] parts = params.split(", ");
        if (parts.length == 9) {
            String apellido = parts[0];
            String ci = parts[1];
            String direccion = parts[2];
            String email = parts[3];
            String fechaNacimiento = parts[4];
            String nombre = parts[5];
            String password = parts[6];
            int rolId = Integer.parseInt(parts[7]);
            Integer garanteId = parts[8].isEmpty() ? null : Integer.parseInt(parts[8]);
            BUsuario usuario = new BUsuario();
            response = usuario.save(apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId, garanteId);
        } else {
            response = "HandleUsuario.java dice: Ocurrió un error al ejecutar el método save (parámetros incorrectos)";
        }
        return response;
    }

    public static String update(String params) {
        String response = "";
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String apellido = parts[1];
            String ci = parts[2];
            String direccion = parts[3];
            String email = parts[4];
            String fechaNacimiento = parts[5];
            String nombre = parts[6];
            String password = parts[7];
            int rolId = Integer.parseInt(parts[8]);
            Integer garanteId = parts[9].isEmpty() ? null : Integer.parseInt(parts[9]);
            BUsuario usuario = new BUsuario();
            response = usuario.update(id, apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId, garanteId);
        } else {
            response = "HandleUsuario.java dice: Ocurrió un error al ejecutar el método update (formato de parámetros incorrecto)";
        }
        return response;
    }

    public static String delete(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToDelete = Integer.parseInt(params);
            BUsuario usuario = new BUsuario();
            response = usuario.delete(idToDelete);
        } else {
            response = "HandleUsuario.java dice: Ocurrió un error al ejecutar el método delete (parámetro no numérico)";
        }
        return response;
    }

    public static String findAll() {
        BUsuario usuario = new BUsuario();
        List<String[]> usuarios = usuario.findAll();
        return usuarios != null ? concatenate(usuarios) : "Usuarios vacíos";
    }

    public static String findOne(String params) {
        String response = "";
        if (isOnlyNumbers(params)) {
            int idToFind = Integer.parseInt(params);
            BUsuario usuario = new BUsuario();
            String[] usuarioFound = usuario.findOne(idToFind);
            response = java.util.Arrays.toString(usuarioFound);
        } else {
            response = "HandleUsuario.java dice: Ocurrió un error al ejecutar el método findOne (parámetro no numérico)";
        }
        return response;
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, [a-zA-Z]+, [0-9]+, [a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}, \\d{4}-\\d{2}-\\d{2}, [a-zA-Z]+, [a-zA-Z0-9]+, \\d+, \\d*$";
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
