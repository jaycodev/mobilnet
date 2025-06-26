USE registro_gpon_db;

INSERT INTO Rol(Descripcion) VALUES
('BackOffice'),
('Consultor'),
('Supervisor'),
('Administrador');

INSERT INTO Usuario (IdRol, Nombre, Apellido, Correo, Contrasena) VALUES
(1, 'Juan', 'Pérez', 'juan.perez@example.com', 'clave123'),
(2, 'María', 'Gómez', 'maria.gomez@example.com', 'mariag2023'),
(1, 'Carlos', 'Ramírez', 'carlos.ramirez@example.com', 'passw0rd'),
(3, 'Ana', 'Torres', 'ana.torres@example.com', 'torres456'),
(2, 'Luis', 'Martínez', 'luis.martinez@example.com', 'lmartinez!'),
(1, 'Lucía', 'Fernández', 'lucia.fernandez@example.com', 'lucia12'),
(2, 'Pedro', 'Sánchez', 'pedro.sanchez@example.com', 'pedro123'),
(3, 'Laura', 'Morales', 'laura.morales@example.com', 'lauram!23'),
(4, 'Jorge', 'Cruz', 'jorge.cruz@example.com', 'jorge2023'),
(2, 'Elena', 'Vega', 'elena.vega@example.com', 'elena456'),
(1, 'Diego', 'Castillo', 'diego.castillo@example.com', 'castilloD'),
(3, 'Sofía', 'Herrera', 'sofia.herrera@example.com', 'sofiaH23'),
(2, 'Mateo', 'Flores', 'mateo.flores@example.com', 'flores@12'),
(1, 'Valentina', 'Rojas', 'valentina.rojas@example.com', 'valenR2025'),
(3, 'Andrés', 'Navarro', 'andres.navarro@example.com', 'navarro12'),
(4, 'Camila', 'Silva', 'camila.silva@example.com', 'camilaS'),
(1, 'Sebastián', 'Ibarra', 'sebastian.ibarra@example.com', 'sibarra!'),
(2, 'Daniela', 'Paredes', 'daniela.paredes@example.com', 'danielaP'),
(3, 'Gonzalo', 'Castañeda', 'gonzalo.castaneda@example.com', 'gcast2024'),
(4, 'Natalia', 'Mendoza', 'natalia.mendoza@example.com', 'nataliaM');

INSERT INTO Cliente (DniCliente, Ruc, Nombre, Apellido, Telefono) VALUES
('12345678', '20123456789', 'Juan', 'Pérez', '987654321'),
('23456789', '20456789123', 'María', 'Gómez', '912345678'),
('34567890', '20567891234', 'Carlos', 'Ramírez', '923456789'),
('45678901', '20678912345', 'Ana', 'Torres', '934567890'),
('56789012', '20789123456', 'Luis', 'Martínez', '945678901'),
('67890123', '20891234567', 'Lucía', 'Fernández', '956789012'),
('78901234', '20912345678', 'Pedro', 'Sánchez', '967890123'),
('89012345', '20123456780', 'Laura', 'Morales', '978901234'),
('90123456', '20234567891', 'Jorge', 'Cruz', '989012345'),
('01234567', '20345678912', 'Elena', 'Vega', '900123456'),
('13579246', '20456789124', 'Diego', 'Castillo', '911234567'),
('24681357', '20567891235', 'Sofía', 'Herrera', '922345678'),
('35792468', '20678912346', 'Mateo', 'Flores', '933456789'),
('46813579', '20789123457', 'Valentina', 'Rojas', '944567890'),
('57924680', '20891234568', 'Andrés', 'Navarro', '955678901'),
('68035791', '20912345679', 'Camila', 'Silva', '966789012'),
('79146802', '20123456781', 'Sebastián', 'Ibarra', '977890123'),
('80257913', '20234567892', 'Daniela', 'Paredes', '988901234'),
('91368024', '20345678913', 'Gonzalo', 'Castañeda', '999012345'),
('02479135', '20456789125', 'Natalia', 'Mendoza', '901123456');

INSERT INTO Plan(Descripcion) VALUES ('Internet Fibra Optica 300Mbps');

INSERT INTO Promocion(Descripcion) VALUES ('Descuento cargo fijo 30% x 3meses');

INSERT INTO Distrito (NombreDistrito) VALUES
('Ancón'), ('Ate'), ('Barranco'), ('Breña'), ('Carabayllo'),
('Chaclacayo'), ('Chorrillos'), ('Cieneguilla'), ('Comas'), ('El Agustino'),
('Independencia'), ('Jesús María'), ('La Molina'), ('La Victoria'), ('Lima'),
('Lince'), ('Los Olivos'), ('Lurigancho'), ('Lurín'), ('Magdalena del Mar'),
('Miraflores'), ('Pachacámac'), ('Pucusana'), ('Pueblo Libre'), ('Puente Piedra'),
('Punta Hermosa'), ('Punta Negra'), ('Rímac'), ('San Bartolo'), ('San Borja'),
('San Isidro'), ('San Juan de Lurigancho'), ('San Juan de Miraflores'), ('San Luis'),
('San Martín de Porres'), ('San Miguel'), ('Santa Anita'), ('Santa María del Mar'),
('Santa Rosa'), ('Santiago de Surco'), ('Surquillo'), ('Villa El Salvador'),
('Villa María del Triunfo');

INSERT INTO Sector (NombreSector) VALUES
('Cercado de Lima'), ('La Molina'), ('Miraflores'), ('San Borja'), ('San Isidro'),
('San Miguel'), ('Santiago de Surco'), ('Modern San Isidro'), ('Barrio de Surco'),
('Centro Histórico (Rímac)'), ('Puente Piedra'), ('Villa El Salvador'),
('San Juan de Lurigancho'), ('Comas'), ('Lince'), ('La Victoria'), ('Ate'),
('Chorrillos'), ('Los Olivos'), ('San Martín de Porres'), ('El Agustino'),
('Pueblo Libre'), ('Magdalena del Mar'), ('San Juan de Miraflores'), ('Barranco');

INSERT INTO EstadoRegistro(Descripcion) VALUES
('Pendiente instalacion'),
('Anulado'),
('Observado'),
('Alta por Baja'),
('sin C.E.'),
('Pegado'),
('Sin disponibilidad'),
('Sin cobertura'),
('Deuda');


INSERT INTO ContactoPrincipal (NombreContacto, Dni, Telefono, Correo) VALUES
('Juan Pérez', '12345678', '987654321', 'juan.perez@example.com'),
('María Gómez', '23456789', '912345678', 'maria.gomez@example.com'),
('Carlos Ramírez', '34567890', '923456789', 'carlos.ramirez@example.com'),
('Ana Torres', '45678901', '934567890', 'ana.torres@example.com'),
('Luis Martínez', '56789012', '945678901', 'luis.martinez@example.com'),
('Lucía Fernández', '67890123', '956789012', 'lucia.fernandez@example.com'),
('Pedro Sánchez', '78901234', '967890123', 'pedro.sanchez@example.com'),
('Laura Morales', '89012345', '978901234', 'laura.morales@example.com'),
('Jorge Cruz', '90123456', '989012345', 'jorge.cruz@example.com'),
('Elena Vega', '01234567', '900123456', 'elena.vega@example.com');

INSERT INTO ContactoSecundario (NombreContacto, Dni, Telefono, Correo) VALUES
('Rosa Ruiz', '12312312', '911223344', 'rosa.ruiz@example.com'),
('Mario Silva', '23423423', '922334455', 'mario.silva@example.com'),
('Diana López', '34534534', '933445566', 'diana.lopez@example.com'),
('Ricardo Torres', '45645645', '944556677', 'ricardo.torres@example.com'),
('Carmen Soto', '56756756', '955667788', 'carmen.soto@example.com');

INSERT INTO Cronograma (UbicacionInstalacion, RangoInstalacion, FechaProgramada) VALUES
('Av. La Marina 123, San Miguel', '8:00 AM - 10:00 AM', '2025-07-01'),
('Jr. Los Olivos 456, Surco', '10:00 AM - 12:00 PM', '2025-07-02'),
('Calle Ficticia 789, Miraflores', '1:00 PM - 3:00 PM', '2025-07-03'),
('Pasaje Central 101, La Molina', '3:00 PM - 5:00 PM', '2025-07-04'),
('Av. Siempre Viva 742, San Isidro', '8:00 AM - 10:00 AM', '2025-07-05');

INSERT INTO RegistroRUC10 (
    IdUsuarioConsulto, IdUsuarioSupervisor, DniCliente, FechaRegistro, Ruc,
    IdContactoPrincipal, IdContactoSecundario, IdPlan, IdPromocion, IdCronograma,
    IdDistrito, IdSector, IdEstado, IdSolicitud, IdInstalacion, IdCarrito,
    Observacion
) VALUES
-- Registro 1
(2, 4, '12345678', '2025-06-19 09:15:00', '20123456789',
 1, 1, 1, 1, 1,
 1, 1, 1, '1QWERTYUI', '763952718', 'CSE00130678226',
 'Cliente confirmó instalación.'),

-- Registro 2
(5, 8, '23456789', '2025-06-19 09:20:00', '20456789123',
 2, 2, 1, 1, 2,
 2, 2, 1, '1ASDFGHJK', '763952719', 'CSE00130678227',
 'Cliente pidió instalación urgente.'),

-- Registro 3
(7, 12, '34567890', '2025-06-19 09:25:00', '20567891234',
 3, 3, 1, 1, 3,
 3, 3, 1, '1ZXCVBNMA', '763952720', 'CSE00130678228',
 'Dirección confirmada por teléfono.'),

-- Registro 4
(10, 15, '45678901', '2025-06-19 09:30:00', '20678912345',
 4, 4, 1, 1, 4,
 4, 4, 1, '1QAZWSXED', '763952721', 'CSE00130678229',
 '')
;