CREATE OR REPLACE TRIGGER creacion_cheques_trigger AFTER
    INSERT ON chequeras
DECLARE
    valor_inicio   INT;
    valor_fin      INT;
    max_value_id   INT;
    num_chequera   INT;
BEGIN
    SELECT
        MAX(id_chequera)
    INTO max_value_id
    FROM
        chequeras;

    SELECT
        inicio,
        fin,
        id_chequera
    INTO
        valor_inicio,
        valor_fin,
        num_chequera
    FROM
        chequeras
    WHERE
        id_chequera = max_value_id;

    FOR i IN valor_inicio..valor_fin LOOP INSERT INTO cheques VALUES (
        i,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        num_chequera
    );

    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('Error en la transaccion: ' || sqlerrm);
        dbms_output.put_line('Se deshacen las modificaciones');
        ROLLBACK;
END;