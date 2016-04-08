DROP TABLE bitacora;
DROP TABLE cargoAutomatico;
DROP TABLE cliente;
DROP TABLE institucion;

CREATE TABLE cliente(
    cedulaCliente VARCHAR(9),
    nombreCliente VARCHAR(20),
    apellidoCliente VARCHAR(20),
    correoCliente VARCHAR(30),
    numTarjetaCliente VARCHAR(16),
    CVVCliente VARCHAR(4),
    saldoCliente int,
    PRIMARY KEY (cedulaCliente)
);

CREATE TABLE institucion(
    cedulaJuridicaInstitucion VARCHAR(9),
    nombreInstitucion VARCHAR(25),
    documentoInstitucion VARCHAR(20),
    PRIMARY KEY (cedulaJuridicaInstitucion)
);

CREATE TABLE cargoAutomatico(
    cedulaClienteCargoAuto VARCHAR(9),
    cedulaJuridicaInstitucionCargoAuto VARCHAR(9),
    descripcionCargoAuto VARCHAR(40),
    montoCargoCargoAuto double,
    FOREIGN KEY (cedulaClienteCargoAuto) REFERENCES cliente(cedulaCliente),
    FOREIGN KEY (cedulaJuridicaInstitucionCargoAuto) REFERENCES institucion(cedulaJuridicaInstitucion),
    PRIMARY KEY (cedulaClienteCargoAuto, cedulaJuridicaInstitucionCargoAuto)    
);

CREATE TABLE bitacora(
    idBitacora int AUTO_INCREMENT,
    cedulaClienteBitacora VARCHAR(9),
    cedulaJuridicaInstitucionBitacora VARCHAR(9),
    fechaBitacora DATETIME,
    montoCargoBitacora double,
    validacionCargoBitacora bool,
    FOREIGN KEY (cedulaClienteBitacora, cedulaJuridicaInstitucionBitacora) REFERENCES cargoAutomatico(cedulaClienteCargoAuto, cedulaJuridicaInstitucionCargoAuto),
    PRIMARY KEY (idBitacora)    
);

INSERT INTO cliente VALUES
 ('1','David', 'Leon', 'asd@gmail.com', '1', '1',100000),
 ('2','Brad', 'Guillen', 'asd@gmail.com', '2', '2',100000),
 ('3','Tatiana', 'Jimenes',	'asd@gmail.com', '3','3',100000),
 ('4','Erick', 'Lopez',	'asd@gmail.com', '4','4',100000),
 ('5','Esteban', 'Guillen',	'asd@gmail.com', '5', '5',100000),	
 ('6','Daniel', 'Lobo',	'asd@gmail.com', '6', '6',100000),
 ('7','Rodrigo', 'Alvarez',	'asd@gmail.com', '7', '7',100000),
 ('8','Sofia', 'Fernandez', 'asd@gmail.com', '8', '8',100000),
 ('9','Maria', 'Martines', 'asd@gmail.com',	'9', '9',100000),
 ('10','Andres', 'Corea', 'asd@gmail.com', '10', '10',100000),
 ('11','Carlos', 'Paniagua', 'asd@gmail.com', '11', '11',100000),
 ('12','Mariano', 'Navarro', 'asd@gmail.com', '12', '12',100000),
 ('13','Marco', 'Cedeño', 'asd@gmail.com', '13', '13',100000),
 ('14','Ano', 'Quiroz', 'asd@gmail.com', '14', '14',100000),
 ('15','Jose', 'Flores', 'asd@gmail.com', '15', '15',100000),
 ('16','Ramon', 'Guillen', 'asd@gmail.com',	'16', '16',100000),
 ('17','Sergio', 'Pacheco', 'asd@gmail.com', '17', '17',100000);

INSERT INTO institucion VALUES(
    '1',
    'ULACIT',
    'nulo'
);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '1'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Gira', 10000000);
	
INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '2'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Matricula', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '3'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Letras de Cambio', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '4'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Buseta', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '5'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Club', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '6'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Gira', 4500);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '7'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Letras de cambio', 42500);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '8'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Letras de cambio', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '9'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Convalidacion', 10000000);
	
INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '10'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Matricula', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '11'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Club', 12200);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '12'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Convalidacion', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '13'), 
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Matricula', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '14'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Letras de cambio', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '15'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Matricula', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '16'), 
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'letra de cambio', 10000000);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '17'), 
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Club', 10000000);

