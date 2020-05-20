CREATE SEQUENCE seqId_informacion
start with 1
increment by 1
maxvalue 9999
nocycle;

CREATE SEQUENCE SEQID_EMPLEADO
start with 1
increment by 1
maxvalue 9999
nocycle;


CREATE SEQUENCE seqId_empleado_contacto
start with 1
increment by 1
maxvalue 9999
nocycle;


CREATE SEQUENCE idTipo_identificacion
start with 1
increment by 1
maxvalue 20
nocycle;


CREATE SEQUENCE seqId_genero
start with 1
increment by 1
maxvalue 5
nocycle;

CREATE SEQUENCE SEQID_PROVEEDOR
start with 1
increment by 1
maxvalue 9999
nocycle;

CREATE SEQUENCE SEQID_MONTO_PERMITIDO
start with 1
increment by 1
maxvalue 2
nocycle;

CREATE SEQUENCE SEQID_ROLES
start with 1
increment by 1
maxvalue 999
nocycle;

CREATE SEQUENCE SEQID_DEPARTAMENTO
start with 1
increment by 1
maxvalue 30
nocycle;

CREATE SEQUENCE SEQID_PUESTOS
start with 1
increment by 1
maxvalue 999
nocycle;

CREATE SEQUENCE SEQID_OPERACION
start with 1
increment by 1
maxvalue 99999
nocycle;

CREATE SEQUENCE SEQID_ESTADO
start with 1
increment by 1
maxvalue 5
nocycle;

CREATE SEQUENCE SEQID_MONEDA
start with 1
increment by 1
maxvalue 5
nocycle;

CREATE SEQUENCE SEQID_TIPOCUENTA
start with 1
increment by 1
maxvalue 20
nocycle;


CREATE SEQUENCE SEQID_BANCO
start with 1
increment by 1
maxvalue 10
nocycle;


INSERT INTO tipo_identificacion VALUES (idTipo_identificacion.nextval, 'PASAPORTE');


INSERT INTO IDENTIFICACIONES VALUES(3244849251703, 1);
INSERT INTO informacion VALUES (seqid_informacion.nextval, 'Miguel Angel', 'Gongora Garcia', '4013 7516', 'San Benito', 1, 3244849251703);
INSERT INTO DEPARTAMENTOS VALUES(SEQID_DEPARTAMENTO.NEXTVAL, 'INFORMATICA');
INSERT INTO PUESTOS VALUES(SEQID_ROLES.NEXTVAL, 'PROGRAMADOR WEB', 1);
INSERT INTO empleados VALUES(SEQID_EMPLEADO.NEXTVAL, 1, 2);

INSERT INTO ROLES VALUES (seqid_roles.nextval, 'Administrador');
INSERT INTO monto_permitido VALUES(SEQID_MONTO_PERMITIDO.NEXTVAL, 10000, 2000);

select * from usuarios;
SELECT * FROM EMPLEADOS;
SELECT * FROM PUESTOS;
SELECT * FROM ROLES;
SELECT * FROM monto_permitido;
INSERT INTO USUARIOS VALUES('mgongora', 'admin', sysdate, 2, 1, 1);

/*

SELECT u.usuario, U.contrasenia, i.nombre, i.apellido, p.nombre_puesto, d.nombre_departamento 
FROM USUARIOS U
INNER JOIN EMPLEADOS E
ON U.fk_id_empleado = e.id_empleado
INNER JOIN PUESTOS P
ON e.fk_id_puesto = p.id_puesto
INNER JOIN departamentos D
ON d.id_departamento = p.fk_id_departamento
INNER JOIN INFORMACION I
ON i.id_informacion = e.fk_id_informacion
WHERE u.usuario = 'mgongora';

*/

--PROCEDIMIENTO PARA VALIDAR LOGIN

CREATE OR REPLACE PROCEDURE validateLogin
(
    encontradoOUT OUT number,
    usernameIN IN  VARCHAR2, 
    passwordIN IN  VARCHAR2
)
AS

BEGIN
    SELECT COUNT(usuario) INTO encontradoOUT 
    FROM USUARIOS
    WHERE usuario = usernameIN AND 
    contrasenia = passwordIN;
END;


--Bloque PL/SQL
DECLARE
    cantidad INT := 0;
BEGIN
    validateLogin(cantidad, 'mgongora', 'admin');
    
    if(cantidad > 0) THEN
        DBMS_OUTPUT.PUT_LINE('ACCESO CONCEDIDO');
    
    else
        DBMS_OUTPUT.PUT_LINE('ACCESO DENEGADO');
        
    END IF;
END;















