package command;

import java.util.List;

public class HandleContrato {

    public static String save(String params) {
        // Parsear los parámetros y realizar la lógica de guardado
        return "Contrato guardado con los parámetros: " + params;
    }

    public static String update(String params) {
        // Parsear los parámetros y realizar la lógica de actualización
        return "Contrato actualizado con los parámetros: " + params;
    }

    public static String delete(String params) {
        // Realizar la eliminación con el ID especificado
        return "Contrato eliminado con ID: " + params;
    }

    public static List<String[]> findAll() {
        // Devolver una lista de contratos
        return List.of(new String[] { "Contrato 1" }, new String[] { "Contrato 2" });
    }

    public static String[] findOne(String params) {
        // Encontrar un contrato por ID
        return new String[] { "Detalles de contrato con ID " + params };
    }
}
