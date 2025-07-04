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

INSERT INTO Cliente (DniCliente, Ruc, Nombre, Apellido, Telefono) VALUES
('11112222', '20111122221', 'Carlos', 'Aguilar', '901100001'),
('22223333', '20222233332', 'Mónica', 'Reyes', '902200002'),
('33334444', '20333344443', 'Lucía', 'Salas', '903300003'),
('44445555', '20444455554', 'Martín', 'Paredes', '904400004'),
('55556666', '20555566665', 'Sandra', 'Lozano', '905500005'),
('66667777', '20666677776', 'Jorge', 'Ibáñez', '906600006'),
('77778888', '20777788887', 'Rosa', 'Vargas', '907700007'),
('88889999', '20888899998', 'Luis', 'Gallardo', '908800008'),
('99990000', '20999900009', 'Carmen', 'Mora', '909900009'),
('00001111', '20000011110', 'Daniel', 'Guzmán', '900000010'),
('10101010', '20101010101', 'Erika', 'Zapata', '910101010'),
('20202020', '20202020202', 'Alonso', 'Quispe', '920202020'),
('30303030', '20303030303', 'Fátima', 'Núñez', '930303030'),
('40404040', '20404040404', 'Renzo', 'Delgado', '940404040'),
('50505050', '20505050505', 'Pamela', 'Cruz', '950505050'),
('60606060', '20606060606', 'Gerardo', 'Vásquez', '960606060'),
('70707070', '20707070707', 'Tatiana', 'Cáceres', '970707070'),
('80808080', '20808080808', 'Óscar', 'Arce', '980808080'),
('90909090', '20909090909', 'Lidia', 'Montes', '990909090'),
('11223344', '20112233445', 'Raúl', 'Campos', '911223344');

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
('Pendiente instalación'),
('Anulado'),
('Observado'),
('Alta por baja'),
('Sin C.E.'),
('Pagado'),
('Sin disponibilidad'),
('Sin cobertura');

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

INSERT INTO Cronograma (UbicacionInstalacion, RangoInstalacion, FechaRegistro, FechaInstalacion) VALUES
('Av. La Marina 123, San Miguel', 'tarde', '2025-06-10', '2025-06-12'),
('Jr. Los Olivos 456, Surco', 'manana', '2025-06-11', '2025-06-10'),
('Calle Ficticia 789, Miraflores', 'tarde', '2025-06-12', '2025-06-11'),
('Pasaje Central 101, La Molina', 'tarde', '2025-06-12', '2025-06-12'),
('Av. Siempre Viva 742, San Isidro', 'manana', '2025-06-11', '2025-06-11');

INSERT INTO RegistroRUC10 (
    IdUsuarioConsultor, IdUsuarioSupervisor, DniCliente,
    IdContactoPrincipal, IdContactoSecundario, IdPlan, IdPromocion, IdCronograma,
    IdEstado, IdSolicitud, IdInstalacion, IdCarrito, Observacion
) VALUES
(2, 4, '12345678', 1, 1, 1, 1, 1, 1, '1QWERTYUI', '763952718', 'CSE00130678226', 'Cliente confirmó instalación.'),
(5, 8, '23456789', 2, 2, 1, 1, 2, 1, '1ASDFGHJK', '763952719', 'CSE00130678227', 'Cliente pidió instalación urgente.'),
(7, 12, '34567890', 3, 3, 1, 1, 3, 1, '1ZXCVBNMA', '763952720', 'CSE00130678228', 'Dirección confirmada por teléfono.'),
(10, 15, '45678901', 4, 4, 1, 1, 4, 1, '1QAZWSXED', '763952721', 'CSE00130678229', 'Dirección confirmada por Mensaje.'),
(13, 18, '56789012', 5, 5, 1, 1, 5, 1, '1LKJHGFDS', '763952722', 'CSE00130678230', 'Cliente solicitó cambio de fecha.'),
(1, 15, '67890123', 6, 1, 1, 1, 1, 3, '1POIUYTRE', '763952723', 'CSE00130678231', 'Cliente se contactará para confirmar.'),
(3, 19, '78901234', 7, 2, 1, 1, 2, 2, '1MNBVCXZA', '763952724', 'CSE00130678232', 'Cliente con deuda anterior.'),
(6, 12, '89012345', 8, 3, 1, 1, 3, 1, '1PLMOKNJI', '763952725', 'CSE00130678233', 'Sector sin cobertura total.'),
(11, 15, '90123456', 9, 4, 1, 1, 4, 4, '1QWASZXED', '763952726', 'CSE00130678234', 'Instalación reagendada por ausencia.'),
(14, 20, '01234567', 10, 5, 1, 1, 5, 2, '1RFVTGBYH', '763952727', 'CSE00130678235', 'Instalador asignado, pendiente visita.'),
(2, 4, '13579246', 1, 1, 1, 1, 1, 1, '1EDCXSWAQ', '763952728', 'CSE00130678236', 'Cliente requiere atención especial.'),
(5, 8, '24681357', 2, 2, 1, 1, 2, 5, '1TGBNHYUJ', '763952729', 'CSE00130678237', 'Número de contacto no disponible.'),
(7, 12, '35792468', 3, 3, 1, 1, 3, 1, '1IKMJNHTG', '763952730', 'CSE00130678238', 'Cliente en zona observada.'),
(10, 15, '46813579', 4, 4, 1, 1, 4, 3, '1UJMHNBVF', '763952731', 'CSE00130678239', 'Promoción aplicada correctamente.');