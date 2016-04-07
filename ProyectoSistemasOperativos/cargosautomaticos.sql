DROP TABLE cargoAutomatico;
DROP TABLE cliente;
DROP TABLE institucion;
DROP TABLE bitacora;

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

INSERT INTO cliente VALUES (
    '207220487',
    'Brad',
    'Guillen',
    'bradguillen15@gmail.com',
    '1111111111111111',
    '111',
    100000
);

INSERT INTO cargoAutomatico VALUES(
    (SELECT cedulaCliente FROM cliente WHERE cedulaCliente = '207220487'),
    (SELECT cedulaJuridicaInstitucion FROM institucion WHERE cedulaJuridicaInstitucion = '1'),
    'Matricula',
    4500
);

SELECT  FROM bitacora