package command;

import java.util.List;

public class HandleReserva {

    public static String save(String params) {
        // Parsear los parámetros y realizar la lógica de guardado
        return "Reserva guardada con los parámetros: " + params;
    }

    public static String update(String params) {
        // Parsear los parámetros y realizar la lógica de actualización
        return "Reserva actualizada con los parámetros: " + params;
    }

    public static String delete(String params) {
        // Realizar la eliminación con el ID especificado
        return "Reserva eliminada con ID: " + params;
    }

    public static List<String[]> findAll() {
        // Devolver una lista de reservas
        return List.of(new String[] { "Reserva 1" }, new String[] { "Reserva 2" });
    }

    public static String[] findOne(String params) {
        // Encontrar una reserva por ID
        return new String[] { "Detalles de reserva con ID " + params };
    }
}
