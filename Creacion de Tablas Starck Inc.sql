-- CREACION TABLA TIPO IDENTIFICACION
CREATE TABLE tipo_identificacion (
    id_tipo       INT PRIMARY KEY NOT NULL,
    nombre_tipo   VARCHAR(30) NOT NULL
);

-- CREACION TABLA IDENTIFICACION

CREATE TABLE identificaciones (
    id_num    INT PRIMARY KEY NOT NULL,
    fk_tipo   INT NOT NULL,
    FOREIGN KEY ( fk_tipo )
        REFERENCES tipo_identificacion ( id_tipo )
);

-- CREACION TABLA GENERO

CREATE TABLE genero (
    id_genero       INT PRIMARY KEY NOT NULL,
    nombre_genero   VARCHAR(1) NOT NULL
);

--CREACION TABLA INFORMACION

CREATE TABLE informacion (
    id_informacion   INT PRIMARY KEY NOT NULL,
    nombre           VARCHAR(40) NOT NULL,
    apellido         VARCHAR(40) NOT NULL,
    telefono         VARCHAR(20) NOT NULL,
    direccion        VARCHAR(40) NOT NULL,
    fk_genero        INT NOT NULL,
    fk_id_num        INT NOT NULL,
    FOREIGN KEY ( fk_genero )
        REFERENCES genero ( id_genero ),
    FOREIGN KEY ( fk_id_num )
        REFERENCES identificaciones ( id_num )
);

-- CREACION TABLA PROVEEDORES

CREATE TABLE proveedores (
    id_proveedor       INT PRIMARY KEY NOT NULL,
    nombre_proveedor   VARCHAR(30) NOT NULL,
    direccion          VARCHAR(50) NOT NULL,
    telefono           VARCHAR(20) NOT NULL
);

-- CREACION TABLA CONTACTO

CREATE TABLE contactos (
    id_contacto         INT PRIMARY KEY NOT NULL,
    fk_id_informacion   INT NOT NULL,
    fk_id_proveedor     INT NOT NULL,
    FOREIGN KEY ( fk_id_informacion )
        REFERENCES informacion ( id_informacion ),
    FOREIGN KEY ( fk_id_proveedor )
        REFERENCES proveedores ( id_proveedor )
);

-- CREACION TABLA DEPARTAMENTOS

CREATE TABLE departamentos (
    id_departamento       INT PRIMARY KEY NOT NULL,
    nombre_departamento   VARCHAR(30)
);

--CREACION TABLA PUESTOS

CREATE TABLE puestos (
    id_puesto            INT PRIMARY KEY NOT NULL,
    nombre_puesto        VARCHAR(30) NOT NULL,
    fk_id_departamento   INT NOT NULL,
    FOREIGN KEY ( fk_id_departamento )
        REFERENCES departamentos ( id_departamento )
);

-- CREACION TABLA ROLES

CREATE TABLE roles (
    id_rol       INT PRIMARY KEY NOT NULL,
    nombre_rol   VARCHAR(20)
);

--CREACION TABLA EMPLEADOS

CREATE TABLE empleados (
    id_empleado         INT PRIMARY KEY NOT NULL,
    fk_id_informacion   INT NOT NULL,
    fk_id_puesto        INT NOT NULL,
    FOREIGN KEY ( fk_id_informacion )
        REFERENCES informacion ( id_informacion ),
    FOREIGN KEY ( fk_id_puesto )
        REFERENCES puestos ( id_puesto )
);

--CREACION TABLA MONTO_PERMITIDO

CREATE TABLE monto_permitido (
    id_monto_permitido   INT PRIMARY KEY NOT NULL,
    monto_max            NUMBER NOT NULL,
    monto_min            NUMBER NOT NULL
);

--CREACION TABLA USUARIOS

CREATE TABLE usuarios (
    usuario          VARCHAR(20) PRIMARY KEY NOT NULL,
    contrasenia      VARCHAR(30) NOT NULL,
    ultima_sesion    DATE,
    fk_id_empleado   INT NOT NULL,
    fk_id_rol        INT NOT NULL,
    fk_id_monto      INT NOT NULL,
    FOREIGN KEY ( fk_id_empleado )
        REFERENCES empleados ( id_empleado ),
    FOREIGN KEY ( fk_id_rol )
        REFERENCES roles ( id_rol ),
    FOREIGN KEY ( fk_id_monto )
        REFERENCES monto_permitido ( id_monto_permitido )
);

--CREACION TABLA MONEDA

CREATE TABLE moneda (
    id_moneda     INT PRIMARY KEY NOT NULL,
    tipo_moneda   VARCHAR(20) NOT NULL
);

-- CREACION TABLA TIPO_CUENTA

CREATE TABLE tipo_cuenta (
    id_tipo   INT PRIMARY KEY NOT NULL,
    tipo      VARCHAR(35)
);

-- CREACION TABLA BANCO

CREATE TABLE banco (
    id_banco       INT PRIMARY KEY NOT NULL,
    nombre_banco   VARCHAR(20)
);

-- CREACION TABLA CUENTA

CREATE TABLE cuenta (
    numero_cuenta   INT PRIMARY KEY NOT NULL,
    nombre          VARCHAR(35) NOT NULL,
    fondos          NUMBER NOT NULL,
    fk_id_banco     INT NOT NULL,
    fk_id_tipo      INT NOT NULL,
    fk_id_moneda    INT NOT NULL,
    FOREIGN KEY ( fk_id_banco )
        REFERENCES banco ( id_banco ),
    FOREIGN KEY ( fk_id_tipo )
        REFERENCES tipo_cuenta ( id_tipo ),
    FOREIGN KEY ( fk_id_moneda )
        REFERENCES moneda ( id_moneda )
);

-- CREACION TABLA CHEQUERA 

CREATE TABLE chequeras (
    id_chequera        INT PRIMARY KEY NOT NULL,
    fk_numero_cuenta   INT NOT NULL,
    stock              INT NOT NULL,
    inicio             INT NOT NULL,
    fin                INT NOT NULL,
    FOREIGN KEY ( fk_numero_cuenta )
        REFERENCES cuenta ( numero_cuenta )
);


-- CREACION TABLA ESTADOS

CREATE TABLE estados (
    id_estado   INT PRIMARY KEY NOT NULL,
    estado      VARCHAR(20) NOT NULL
);

-- CREACION TABLA CHEQUES

CREATE TABLE cheques (
    id_cheque         INT PRIMARY KEY NOT NULL,
    monto             NUMBER(8,2) NOT NULL,
    fecha             DATE NOT NULL,
    lugar             VARCHAR(35) NOT NULL,
    fk_id_proveedor   INT NOT NULL,
    fk_id_estado      INT NOT NULL,
    fk_id_chequera    INT NOT NULL,
    FOREIGN KEY ( fk_id_proveedor )
        REFERENCES proveedores ( id_proveedor ),
    FOREIGN KEY ( fk_id_estado )
        REFERENCES estados ( id_estado ),
    FOREIGN KEY ( fk_id_chequera )
        REFERENCES chequeras ( id_chequera )
);



--CREACION TABLA OPERACIONES

CREATE TABLE operaciones (
    id_operacion   INT PRIMARY KEY NOT NULL,
    fk_usuario     VARCHAR(20) NOT NULL,
    fk_id_cheque   INT NOT NULL,
    fecha          DATE NOT NULL,
    fk_id_estado   INT NOT NULL,
    FOREIGN KEY ( fk_usuario )
        REFERENCES usuarios ( usuario ),
    FOREIGN KEY ( fk_id_cheque )
        REFERENCES cheques ( id_cheque ),
    FOREIGN KEY ( fk_id_estado )
        REFERENCES estados ( id_estado )
);