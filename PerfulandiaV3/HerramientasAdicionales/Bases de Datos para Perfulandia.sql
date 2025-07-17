
-- Base de Datos de Usuarios Para Perfulandia
CREATE USER 'perfulandia_admin2'@'%' identified BY 'Duoc.2025';
CREATE DATABASE perfulandia_spa;
GRANT ALL PRIVILEGES ON perfulandia_spa.* TO 'perfulandia_admin2'@'%';

-- Base de Datos de Inventario para Perfulandia
CREATE USER 'perfulandia_admin3'@'%' identified BY 'Duoc.2025';
CREATE DATABASE perfulandia_inventario;
GRANT ALL PRIVILEGES ON perfulandia_inventario.* TO 'perfulandia_admin3'@'%';

-- Base de Datos de Envios para Perfulandia
CREATE USER 'perfulandia_admin4'@'%' identified BY 'Duoc.2025';
CREATE DATABASE perfulandia_envios;
GRANT ALL PRIVILEGES ON perfulandia_envios.* TO 'perfulandia_admin4'@'%';

-- Base de Datos de Ventas para Perfulandia
CREATE USER 'perfulandia_admin1'@'%' identified BY 'Duoc.2025';
CREATE DATABASE perfulandia_ventas;
GRANT ALL PRIVILEGES ON perfulandia_ventas.* TO 'perfulandia_admin1'@'%';

Select * from envio;







