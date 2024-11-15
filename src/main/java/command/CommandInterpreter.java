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
        COMMANDS.put("rol", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("pagos", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("notificacion", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("reportes", new String[] { "generate" });
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
                return handleContrato(command, params);
            case "mantenimiento":
                return handleMantenimiento(command, params);
            case "reserva":
                return handleReserva(command, params);
            case "usuario":
                return handleUsuario(command, params);
            case "vehiculo":
                return handleVehiculo(command, params);
            case "rol":
                return handleRol(command, params);
            case "pagos":
                return handlePagos(command, params);
            case "notificacion":
                return handleNotificacion(command, params);
            case "reportes":
                return handleReportes(command, params);
            default:
                return "No se reconoce " + useCase + " como un caso de uso";
        }
    }

    private static String handleContrato(String command, String params) {
        switch (command) {
            case "save": return HandleContrato.save(params);
            case "update": return HandleContrato.update(params);
            case "delete": return HandleContrato.delete(params);
            case "findAll": return HandleContrato.findAll();
            case "findOne": return HandleContrato.findOne(params);
            default: return "Comando no válido para contrato";
        }
    }

    private static String handleMantenimiento(String command, String params) {
        switch (command) {
            case "save": return HandleMantenimiento.save(params);
            case "update": return HandleMantenimiento.update(params);
            case "delete": return HandleMantenimiento.delete(params);
            case "findAll": return HandleMantenimiento.findAll();
            case "findOne": return HandleMantenimiento.findOne(params);
            default: return "Comando no válido para mantenimiento";
        }
    }

    private static String handleReserva(String command, String params) {
        switch (command) {
            case "save": return HandleReserva.save(params);
            case "update": return HandleReserva.update(params);
            case "delete": return HandleReserva.delete(params);
            case "findAll": return HandleReserva.findAll();
            case "findOne": return HandleReserva.findOne(params);
            default: return "Comando no válido para reserva";
        }
    }

    private static String handleUsuario(String command, String params) {
        switch (command) {
            case "save": return HandleUsuario.save(params);
            case "update": return HandleUsuario.update(params);
            case "delete": return HandleUsuario.delete(params);
            case "findAll": return HandleUsuario.findAll();
            case "findOne": return HandleUsuario.findOne(params);
            default: return "Comando no válido para usuario";
        }
    }

    private static String handleVehiculo(String command, String params) {
        switch (command) {
            case "save": return HandleVehiculo.save(params);
            case "update": return HandleVehiculo.update(params);
            case "delete": return HandleVehiculo.delete(params);
            case "findAll": return HandleVehiculo.findAll();
            case "findOne": return HandleVehiculo.findOne(params);
            default: return "Comando no válido para vehículo";
        }
    }

    private static String handleRol(String command, String params) {
        switch (command) {
            case "save": return HandleRol.save(params);
            case "update": return HandleRol.update(params);
            case "delete": return HandleRol.delete(params);
            case "findAll": return HandleRol.findAll();
            case "findOne": return HandleRol.findOne(params);
            default: return "Comando no válido para rol";
        }
    }

    private static String handlePagos(String command, String params) {
        switch (command) {
            case "save": return HandlePagos.save(params);
            case "update": return HandlePagos.update(params);
            case "delete": return HandlePagos.delete(params);
            case "findAll": return HandlePagos.findAll();
            case "findOne": return HandlePagos.findOne(params);
            default: return "Comando no válido para pagos";
        }
    }

    private static String handleNotificacion(String command, String params) {
        switch (command) {
            case "save": return HandleNotificacion.save(params);
            case "update": return HandleNotificacion.update(params);
            case "delete": return HandleNotificacion.delete(params);
            case "findAll": return HandleNotificacion.findAll();
            case "findOne": return HandleNotificacion.findOne(params);
            default: return "Comando no válido para notificaciones";
        }
    }

    private static String handleReportes(String command, String params) {
        switch (command) {
            case "generate": return HandleReportes.generate(params);
            default: return "Comando no válido para reportes";
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
                + " - save (apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId, garanteId)\n"
                + " - update (id, apellido, ci, direccion, email, fechaNacimiento, nombre, password, rolId, garanteId)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: vehiculo\n"
                + " - save (anio, placa, precioCompra)\n"
                + " - update (id, anio, placa, precioCompra)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: pagos\n"
                + " - save (monto, fechaPago, usuarioId)\n"
                + " - update (id, monto, fechaPago)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: notificacion\n"
                + " - save (mensaje, fecha, usuarioId)\n"
                + " - update (id, mensaje, fecha)\n"
                + " - findOne (id)\n"
                + " - findAll (all)\n"
                + " - delete (id)\n"
                + "\n"
                + "CU: reportes\n"
                + " - generate (tipoReporte, parametros)\n";
    }
}
