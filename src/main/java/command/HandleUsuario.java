package command;

import java.util.List;

public class HandleUsuario {

    public static String save(String params) {
        // Parsear los parámetros y realizar la lógica de guardado
        return "Usuario guardado con los parámetros: " + params;
    }

    public static String update(String params) {
        // Parsear los parámetros y realizar la lógica de actualización
        return "Usuario actualizado con los parámetros: " + params;
    }

    public static String delete(String params) {
        // Realizar la eliminación con el ID especificado
        return "Usuario eliminado con ID: " + params;
    }

    public static List<String[]> findAll() {
        // Devolver una lista de usuarios
        return List.of(new String[] { "Usuario 1" }, new String[] { "Usuario 2" });
    }

    public static String[] findOne(String params) {
        // Encontrar un usuario por ID
        return new String[] { "Detalles de usuario con ID " + params };
    }
}
