package command;

import java.util.HashMap;
import java.util.Map;

public class CommandInterpreter {

    private static final Map<String, String[]> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("contrato", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("mantenimiento", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("reserva", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("usuario", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("vehiculo", new String[] { "save", "update", "findOne", "findAll", "delete" });
    }

    public static String interpret(String subject) {
        subject = subject.replaceAll("[^a-zA-Z0-9\\s\\(\\),./@]", "");
        subject = subject.replaceAll("\\s+", " ").trim();

        System.out.println("Subject luego de formatear: " + subject);
        if (subject.equals("help")) {
            return getHelpMessage();
        }

        String pattern = "(\\w+)\\s+(\\w+)\\s*\\((.*)\\)";
        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = regex.matcher(subject);

        if (!matcher.matches()) {
            return "Comando no reconocido. Por favor, asegúrate de seguir la estructura: {caso_de_uso} comando (parametros)";
        }

        String useCase = matcher.group(1).trim();
        String command = matcher.group(2).trim();
        String params = matcher.group(3).trim();

        if (!COMMANDS.containsKey(useCase)) {
            return "Caso de uso '" + useCase + "' no reconocido. Usa 'HELP' para ver la lista de casos de uso y comandos.";
        }

        boolean commandExists = false;
        for (String validCommand : COMMANDS.get(useCase)) {
            if (validCommand.equals(command)) {
                commandExists = true;
                break;
            }
        }

        if (!commandExists) {
            return "Comando '" + command + "' no reconocido para el caso de uso '" + useCase + "'. Usa 'HELP' para ver la lista de comandos.";
        }

        switch (useCase.toLowerCase()) {
            case "contrato":
                return executeContrato(command, params);
            case "mantenimiento":
                return executeMantenimiento(command, params);
            case "reserva":
                return executeReserva(command, params);
            case "usuario":
                return executeUsuario(command, params);
            case "vehiculo":
                return executeVehiculo(command, params);
            default:
                return "No se reconoce " + useCase + " como un caso de uso";
        }
    }

    private static String executeContrato(String command, String params) {
        switch (command) {
            case "save":
                return HandleContrato.save(params);
            case "update":
                return HandleContrato.update(params);
            case "findOne":
                return HandleContrato.findOne(params);
            case "findAll":
                return HandleContrato.findAll();
            case "delete":
                return HandleContrato.delete(params);
            default:
                return "Comando no válido para contrato";
        }
    }

    private static String executeMantenimiento(String command, String params) {
        switch (command) {
            case "save":
                return HandleMantenimiento.save(params);
            case "update":
                return HandleMantenimiento.update(params);
            case "findOne":
                return HandleMantenimiento.findOne(params);
            case "findAll":
                return HandleMantenimiento.findAll();
            case "delete":
                return HandleMantenimiento.delete(params);
            default:
                return "Comando no válido para mantenimiento";
        }
    }

    private static String executeReserva(String command, String params) {
        switch (command) {
            case "save":
                return HandleReserva.save(params);
            case "update":
                return HandleReserva.update(params);
            case "findOne":
                return HandleReserva.findOne(params);
            case "findAll":
                return HandleReserva.findAll();
            case "delete":
                return HandleReserva.delete(params);
            default:
                return "Comando no válido para reserva";
        }
    }

    private static String executeUsuario(String command, String params) {
        switch (command) {
            case "save":
                return HandleUsuario.save(params);
            case "update":
                return HandleUsuario.update(params);
            case "findOne":
                return HandleUsuario.findOne(params);
            case "findAll":
                return HandleUsuario.findAll();
            case "delete":
                return HandleUsuario.delete(params);
            default:
                return "Comando no válido para usuario";
        }
    }

    private static String executeVehiculo(String command, String params) {
        switch (command) {
            case "save":
                return HandleVehiculo.save(params);
            case "update":
                return HandleVehiculo.update(params);
            case "findOne":
                return HandleVehiculo.findOne(params);
            case "findAll":
                return HandleVehiculo.findAll();
            case "delete":
                return HandleVehiculo.delete(params);
            default:
                return "Comando no válido para vehiculo";
        }
    }

    private static String getHelpMessage() {
        return "**************** SISTEMA DE GESTIÓN VEHICULAR **************** \n\n"
                + "Comandos disponibles para los casos de uso: \n"
                + "CU: contrato\n"
                + " - save (estado, fechaInicio, fechaFin, renta, usuarioId, vehiculoId)\n"
                + " - update (id, estado, fechaInicio, fechaFin, renta)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: mantenimiento\n"
                + " - save (costo, tipo)\n"
                + " - update (id, costo, tipo)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: reserva\n"
                + " - save (estado)\n"
                + " - update (id, estado)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: usuario\n"
                + " - save (apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId)\n"
                + " - update (id, apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: vehiculo\n"
                + " - save (anio, placa, precioCompra)\n"
                + " - update (id, anio, placa, precioCompra)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n";
    }
}
