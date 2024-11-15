-- Tabla Rol
CREATE TABLE Rol (
id INT PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL
);
-- Tabla Usuario
CREATE TABLE Usuario (
id INT PRIMARY KEY,
Apellido VARCHAR(50),
CI VARCHAR(20),
Direccion_Domicilio VARCHAR(100),
email VARCHAR(100),
Fecha_Nacimiento DATE,
Nombre VARCHAR(50),
password VARCHAR(50),
rol_id INT,
 garante_id INT,
FOREIGN KEY (rol_id) REFERENCES Rol(id)
 FOREIGN KEY (garante_id) REFERENCES Usuario(id)
);
-- Tabla Notificacion
CREATE TABLE Notificacion (
id INT PRIMARY KEY,
Tipo VARCHAR(50) NOT NULL
);
-- Tabla Notificacion_Usuario
CREATE TABLE Notificacion_Usuario (
id INT PRIMARY KEY,
Fecha DATE,
Mensaje VARCHAR(255),
usuario_id INT,
notificacion_id INT,
FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
FOREIGN KEY (notificacion_id) REFERENCES Notificacion(id)
);
-- Tabla Reserva
CREATE TABLE Reserva (
id INT PRIMARY KEY,
Estado VARCHAR(50) NOT NULL,
usuario_id INT,
FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);
-- Tabla Contrato
CREATE TABLE Contrato (
id INT PRIMARY KEY,
Estado VARCHAR(50),
Fecha_Inicio DATE,
Fecha_Fin DATE,
Renta FLOAT,
usuario_id INT,
vehiculo_id INT,
FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
FOREIGN KEY (vehiculo_id) REFERENCES Vehiculo(id)
);
-- Tabla Vehiculo
CREATE TABLE Vehiculo (
id INT PRIMARY KEY,
Año INT,
Placa VARCHAR(20),
Precio_Compra FLOAT
);
-- Tabla Mantenimiento
CREATE TABLE Mantenimiento (
id INT PRIMARY KEY,
Costo FLOAT,
Tipo VARCHAR(50) NOT NULL
);
-- Tabla Mantenimiento_Vehiculo
CREATE TABLE Mantenimiento_Vehiculo (
id INT PRIMARY KEY,
Detalle VARCHAR(255),
Fecha_Mantenimiento DATE,
vehiculo_id INT,
FOREIGN KEY (vehiculo_id) REFERENCES Vehiculo(id)
);
-- Tabla Reserva_Vehiculo
CREATE TABLE Reserva_Vehiculo (
id INT PRIMARY KEY,
Fecha_Reserva DATE,
reserva_id INT,
vehiculo_id INT,
FOREIGN KEY (reserva_id) REFERENCES Reserva(id),
FOREIGN KEY (vehiculo_id) REFERENCES Vehiculo(id)
);
-- Tabla Pagos
CREATE TABLE Pagos (
id INT PRIMARY KEY,
Fecha_Pago DATE,
Metodo_Pago VARCHAR(50),
Monto_Pagado FLOAT,
Nro_Semana INT,
Renta INT,
usuario_id INT,
FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);