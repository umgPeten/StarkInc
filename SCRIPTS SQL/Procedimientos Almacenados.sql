CREATE OR REPLACE PROCEDURE insertar_chequera (
    num_chequera   IN   INT,
    num_cuenta     IN   INT,
    inventario     IN   INT
) AS
    ultimo_valor_cheque   INT;
    inicio                INT;
    fin                   INT;
BEGIN
    SELECT
        MAX(id_cheque)
    INTO ultimo_valor_cheque
    FROM
        cheques;

    inicio := ultimo_valor_cheque + 1;
    fin := ultimo_valor_cheque + inventario;
    INSERT INTO chequeras VALUES (
        num_chequera,
        num_cuenta,
        inventario,
        inicio,
        fin
    );

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error al ingresar chequera ' || sqlerrm);
        dbms_output.put_line('Deshaciendo cambios');
        ROLLBACK;
END;

SET SERVEROUT ON;

EXECUTE insertar_chequera(2, 12345, 25);

CREATE OR REPLACE PROCEDURE get_depa_puesto_rol (
    resultado   OUT   SYS_REFCURSOR,
    tipo        IN    INT
) AS
    peticion INT := 0;
BEGIN
    peticion := tipo;
    IF ( peticion = 1 ) THEN
        OPEN resultado FOR SELECT
                               *
                           FROM
                               departamentos
                           ORDER BY
                               id_departamento;

    ELSIF ( peticion = 2 ) THEN
        OPEN resultado FOR SELECT
                               *
                           FROM
                               puestos
                           ORDER BY
                               id_puesto;

    ELSE
        OPEN resultado FOR SELECT
                               *
                           FROM
                               roles
                           ORDER BY
                               id_rol;

    END IF;

END;

VARIABLE cursor_output refcursor;

EXECUTE get_depa_puesto_rol(:cursor_output, 2);

SET AUTOPRINT ON;

CREATE OR REPLACE PROCEDURE insertarusuario (
    inserttypein       IN    INT,
    identificacionin   IN    INT,
    tipo_idin          IN    INT,
    nombrein           IN    VARCHAR2,
    apellidoin         IN    VARCHAR2,
    telefonoin         IN    VARCHAR2,
    direccionin        IN    VARCHAR2,
    id_generoin        IN    VARCHAR2,
    id_puestoin        IN    INT,
    fecha_nacin        IN    VARCHAR2,
    id_errorout        OUT   INT,
    id_megerrorout     OUT   VARCHAR2
) AS
    dupval_on_index EXCEPTION;
    PRAGMA exception_init ( dupval_on_index, -00001 );
    tipoinsert INT := 0;
BEGIN
    id_errorout := 0;
    id_megerrorout := 'SIN ERRORES';
    tipoinsert := inserttypein;
    dbms_output.put_line('EMPEZANDO');
    IF ( tipoinsert = 1 ) THEN
        INSERT INTO identificaciones VALUES (
            identificacionin,
            tipo_idin
        );

        dbms_output.put_line('se inserto identificacion');
        INSERT INTO informacion VALUES (
            seqid_informacion.NEXTVAL,
            nombrein,
            apellidoin,
            to_date(fecha_nacin, 'dd/mm/yyyy'),
            telefonoin,
            direccionin,
            id_generoin,
            identificacionin
        );

        dbms_output.put_line('se inserto informacion');
        INSERT INTO empleados VALUES (
            seqid_empleado_contacto.NEXTVAL,
            seqid_informacion.CURRVAL,
            id_puestoin
        );

        dbms_output.put_line('se inserto empleado');
    END IF;

    COMMIT;
    dbms_output.put_line('INSERTADO CORRECTAMENTE');
EXCEPTION
    WHEN dupval_on_index THEN
        dbms_output.put_line('ERROR, LLAVE DUPLICADA');
        id_errorout := sqlcode;
        id_megerrorout := sqlerrm;
        ROLLBACK;
        dbms_output.put_line('SE HIZO ROLLBACK');
    WHEN OTHERS THEN
        id_errorout := sqlcode;
        id_megerrorout := sqlerrm;
        dbms_output.put_line(id_errorout);
        dbms_output.put_line(id_megerrorout);
        dbms_output.put_line('SE HIZO ROLLBACK');
        ROLLBACK;
END;

CREATE OR REPLACE PROCEDURE obtenerempleados (
    empleados OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN empleados FOR SELECT
                           e.id_empleado,
                           i.nombre,
                           i.apellido,
                           i.direccion,
                           i.telefono,
                           p.nombre_puesto,
                           d.nombre_departamento
                       FROM
                           empleados       e
                           INNER JOIN puestos         p ON e.fk_id_puesto = p.id_puesto
                           INNER JOIN departamentos   d ON d.id_departamento = p.fk_id_departamento
                           INNER JOIN informacion     i ON i.id_informacion = e.fk_id_informacion
                       ORDER BY
                           e.id_empleado;

END obtenerempleados;