create table if not exists admins(
                	id int auto_increment not null primary key,
                	nombre varchar(255) not null,
                	password varchar(255) not null,
                	cargo varchar(255) not null,
                	estado varchar(255) not null default 'ACTIVE'
                );
create table if not exists clientes(
                    id int auto_increment not null primary key,
                    nombre varchar(255) not null,
                    apellido varchar(255) not null,
                    telefono varchar(50) not null,
                    genero varchar(50) not null,
                    direccion varchar(255) not null,
                    estado varchar(255) not null default 'ACTIVE',
                    id_admin_referido int,
                    constraint fk_admin_referido foreign key (id_admin_referido) references admins(id)
                );
create table if not exists pagos(
                    id int auto_increment not null primary key,
                    tipo_suscripcion varchar(255) not null,
                    precio double default 0.0,
                    duracion varchar(255),
                    estado varchar(255) default 'PAGO',
                    id_cliente int,
                    constraint fk_cliente foreign key (id_cliente) references clientes(id)
                );
INSERT INTO admins (nombre, password, cargo)
                SELECT 'Superadmin', '12345', 'Superadmin'
                WHERE NOT EXISTS (
                    SELECT 1 FROM admins WHERE nombre = 'Superadmin'
                );