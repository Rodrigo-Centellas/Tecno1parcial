package command;

import java.util.List;

public class HandleVehiculo {

    public static String save(String params) {
        // Parsear los parámetros y realizar la lógica de guardado
        return "Vehículo guardado con los parámetros: " + params;
    }

    public static String update(String params) {
        // Parsear los parámetros y realizar la lógica de actualización
        return "Vehículo actualizado con los parámetros: " + params;
    }

    public static String delete(String params) {
        // Realizar la eliminación con el ID especificado
        return "Vehículo eliminado con ID: " + params;
    }

    public static List<String[]> findAll() {
        // Devolver una lista de vehículos
        return List.of(new String[] { "Vehículo 1" }, new String[] { "Vehículo 2" });
    }

    public static String[] findOne(String params) {
        // Encontrar un vehículo por ID
        return new String[] { "Detalles de vehículo con ID " + params };
    }
}
