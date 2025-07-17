-- INSERTS para la tabla ENVIO
INSERT INTO envio (direccion, estado, fecha_entrega, fecha_envio, id_venta) VALUES
( 'Av. Providencia 1234, Santiago', 'Entregado', '2025-05-10', '2025-05-08', 111),
('Calle Los Leones 234, Ñuñoa', 'En camino', '2025-05-11', '2025-05-18', 112),
('Pasaje Sur 321, Maipú', 'Pendiente', '2025-05-15', '2025-05-13', 103),
('Camino El Alba 789, Las Condes', 'Entregado', '2025-05-15', '2025-05-13', 104),
('Av. La Florida 4567, La Florida', 'Cancelado', '2025-05-15', '2025-05-13', 105),
('Camino a Melipilla 9000, Cerrillos', 'En camino', '2025-05-11', '2025-05-17', 106),
('Calle 5 de Abril 321, Estación Central', 'Entregado', '2025-05-14', '2025-05-12', 107),
('Av. Macul 7890, Macul', 'Pendiente', '2025-05-15', '2025-05-13', 108),
('Calle Lira 876, Santiago Centro', 'En camino', '2025-05-11', '2025-05-19', 109),
('Av. Recoleta 543, Recoleta', 'Entregado', '2025-05-11', '2025-05-09', 110);

-- INSERTS para la tabla venta
INSERT INTO venta (id, codigo, fecha, pago_confirmado, total) VALUES
( 'VNT-1001', '2025-05-10', TRUE, 45990),
( 'VNT-1002', '2025-05-11', FALSE, 12990),
( 'VNT-1003', '2025-05-12', TRUE, 77990),
( 'VNT-1004', '2025-05-12', TRUE, 19990),
( 'VNT-1005', '2025-05-13', FALSE, 35990),
( 'VNT-1006', '2025-05-13', TRUE, 9990),
( 'VNT-1007', '2025-05-14', TRUE, 64990),
( 'VNT-1008', '2025-05-15', FALSE, 24990),
( 'VNT-1009', '2025-05-15', TRUE, 29990),
( 'VNT-1010', '2025-05-16', TRUE, 89990);

-- INSERTS para la tabla USUARIO
INSERT INTO usuario (apellido, correo, fecha_nacimiento, nombre, run) VALUES
( 'González', 'mgonzalez@gmail.com', '1990-03-15', 'María', '18.234.567-4'),
( 'Pérez', 'jperez@yahoo.com', '1985-07-22', 'Juan', '17.987.654-1'),
( 'Rojas', 'carolina.rojas@hotmail.com', '1992-11-10', 'Carolina', '19.345.123-6'),
( 'Muñoz', 'fmunoz@outlook.com', '1988-05-08', 'Fernando', '16.456.789-0'),
( 'Silva', 'paula.silva@gmail.com', '1995-02-28', 'Paula', '20.123.456-5'),
( 'Contreras', 'lcontreras@gmail.com', '1991-09-30', 'Luis', '17.234.876-2'),
( 'Castillo', 'dcastillo@hotmail.com', '1987-12-01', 'Daniela', '15.345.678-3'),
( 'Fuentes', 'rfuentes@gmail.com', '1993-06-18', 'Ricardo', '18.765.432-9'),
( 'Navarro', 'anavarro@yahoo.com', '1990-10-25', 'Andrea', '19.876.543-7'),
( 'Vega', 'cvega@gmail.com', '1989-04-12', 'Cristian', '16.789.012-8');

-- INSERTS para la tabla PRODUCTO
INSERT INTO producto (precio_compra, precio_venta, codigo, categoria, descripcion, nombre) VALUES
(12000, 19990, 'PRF-001', 'Perfume', 'Perfume floral con toques de jazmín y vainilla. Ideal para uso diario.', 'Essenza Bella'),
(15000, 24990, 'PRF-002', 'Perfume', 'Aroma amaderado intenso y elegante para ocasiones especiales.', 'Noir Élégant'),
(9500, 16990, 'PRF-003', 'Perfume', 'Fragancia fresca y cítrica, perfecta para días calurosos.', 'Citrus Fresh'),
(18000, 29990, 'PRF-004', 'Perfume', 'Perfume dulce con notas de frutos rojos y almizcle.', 'Dulce Pasión'),
(13000, 21990, 'PRF-005', 'Perfume', 'Aroma clásico con lavanda y cuero. Duración prolongada.', 'Tradición'),
(11000, 18990, 'PRF-006', 'Perfume', 'Perfume unisex con mezcla de especias y madera de sándalo.', 'Aura Libre'),
(14000, 23990, 'PRF-007', 'Perfume', 'Fragancia oriental con canela, incienso y vainilla.', 'Misterio Oriental'),
(10000, 17990, 'PRF-008', 'Perfume', 'Aroma fresco de mar con notas verdes y acuáticas.', 'Brisa Marina'),
(16000, 26990, 'PRF-009', 'Perfume', 'Perfume sensual con notas de rosa, pachulí y ámbar.', 'Seducción'),
(12500, 20990, 'PRF-010', 'Perfume', 'Perfume liviano con esencia de flor de loto y té blanco.', 'Pureté');

-- INSERTS para la tabla INVENTARIO

INSERT INTO inventario (id_producto, stock_disponible, stock_minimo, nombre_sucursal) VALUES
(1, 25, 5, 'Sucursal Santiago Centro'),
(2, 10, 3, 'Sucursal Viña del Mar'),
(3, 30, 8, 'Sucursal Concepción'),
(4, 15, 4, 'Sucursal Santiago Centro'),
(5, 8, 2, 'Sucursal Viña del Mar'),
(6, 20, 5, 'Sucursal Concepción'),
(7, 12, 3, 'Sucursal Santiago Centro'),
(8, 18, 4, 'Sucursal Viña del Mar'),
(9, 5, 2, 'Sucursal Concepción'),
(10, 22, 6, 'Sucursal Santiago Centro');

