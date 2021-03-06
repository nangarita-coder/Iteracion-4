
DROP TABLE ESPACIO CASCADE CONSTRAINTS;
DROP TABLE HORARIO CASCADE CONSTRAINTS;
DROP TABLE PERSONA CASCADE CONSTRAINTS;
DROP TABLE tipo_visitante CASCADE CONSTRAINTS;
DROP TABLE VISITA CASCADE CONSTRAINTS;




CREATE TABLE horario(
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    hora_apertura NUMBER NOT NULL,
    hora_cierre NUMBER NOT NULL,
    UNIQUE ( hora_apertura, hora_cierre),
    PRIMARY KEY(id)
);

CREATE TABLE tipo_visitante(
    tipo VARCHAR2(255 BYTE) NOT NULL,
    horario_id NUMBER NOT NULL,
    FOREIGN KEY(horario_id) REFERENCES horario(id),
    PRIMARY KEY(tipo)
);


CREATE TABLE persona(
    nombre VARCHAR2(255 BYTE) NOT NULL,
    identificacion NUMBER NOT NULL,
    email VARCHAR2(255 BYTE),
    telefono NUMBER UNIQUE NOT NULL,
    emer_telefono NUMBER NOT NULL,
    emer_nombre VARCHAR2(255 BYTE) NOT NULL,
    establecimiento_donde_trabaja NUMBER,
    compania_domicilios VARCHAR2(255 BYTE),
    estado VARCHAR2(255 BYTE) NOT NULL,
    tipo_visitante VARCHAR2(255 BYTE),
    FOREIGN KEY(tipo_visitante) REFERENCES tipo_visitante(tipo),
    /*
    FOREIGN KEY(establecimiento_donde_trabaja) REFERENCES espacio(id),
    */
    CHECK(estado IN ('POSITIVO','ROJO','NARANJA','VERDE')),
    PRIMARY KEY (email)
);

CREATE TABLE espacio(
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    nombre VARCHAR2(255 BYTE) NOT NULL,
    aforo NUMBER NOT NULL,
    tipo_espacio VARCHAR2(255 BYTE) NOT NULL,
    personas_adentro NUMBER NOT NULL,
    capacidad_normal NUMBER NOT NULL,
    estado NUMBER NOT NULL,
    tipo_comercio VARCHAR2(255 BYTE),
    area NUMBER NOT NULL,
    horario_id NUMBER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(horario_id) REFERENCES horario(id)
);


CREATE TABLE visita(
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    entrada TIMESTAMP NOT NULL,
    salida TIMESTAMP,
    temperatura NUMBER NOT NULL,
    persona_email VARCHAR2(255 BYTE) NOT NULL, 
    espacio_id NUMBER NOT NULL, 
    PRIMARY KEY (id),
    FOREIGN KEY(persona_email) REFERENCES persona(email),
    FOREIGN KEY(espacio_id) REFERENCES espacio(id)

);




ALTER TABLE persona
ADD CONSTRAINT fk_supplier
  FOREIGN KEY (establecimiento_donde_trabaja)
  REFERENCES espacio(id);
  

/*
RF1
*/


INSERT INTO horario (
    hora_apertura,
    hora_cierre)
    VALUES (
    7,
    22
);

    
INSERT INTO espacio(
    nombre,
    aforo,
    tipo_espacio,
    personas_adentro,
    capacidad_normal,
    estado,
    area,
    horario_id )
    VALUES (
        'Baño principal',
        50,
        'BAÑO',
        10,
        100,
        1,
        60,
        1
    );
  

 
/*
RF2
*/


INSERT INTO horario (
    hora_apertura,
    hora_cierre)
    VALUES (
    7,
    15
);

    
INSERT INTO espacio(
    nombre,
    aforo,
    tipo_espacio,
    personas_adentro,
    capacidad_normal,
    estado,
    tipo_comercio,
    area,
    horario_id )
    VALUES (
        'Exito',
        50,
        'ESTABLECIMIENTO',
        10,
        100,
        1,
        'TIENDA',
        100,
        1
    );
  
/*
RF3
*/

INSERT INTO horario (
    hora_apertura,
    hora_cierre)
    VALUES (
    7,
    23
);

INSERT INTO tipo_visitante(
    tipo,
    horario_id)
    VALUES(
    'DOMICILIARIO',
    3
    );
    
/*
RF4
*/

INSERT INTO persona(
    nombre,
    identificacion,
    email,
    telefono,
    emer_telefono,
    emer_nombre,
    compania_domicilios,
    estado,
    tipo_visitante
    )
    VALUES(
    'Danilo',
    33482,
    'danilo@correo.com',
    34123293,
    34233222,
    'Laura',
    'Rappi',
    'VERDE',
    'DOMICILIARIO'
    );
    
/*
RF5
*/

INSERT INTO tipo_visitante(
    tipo,
    horario_id)
    VALUES(
    'LECTOR',
    3
    );
    
INSERT INTO persona(
    nombre,
    identificacion,
    email,
    telefono,
    emer_telefono,
    emer_nombre,
    estado,
    tipo_visitante,
    establecimiento_donde_trabaja
    )
    VALUES(
    'Julian',
    33482234,
    'julian@correo.com',
    341233293,
    34233222,
    'Laura',
    'VERDE',
    'LECTOR',
    3
    );
    

/*
RFC8
*/

Select nombre, email, id_espacio
From (Select nombre , email
From Persona) INNER JOIN (Select email_persona , 
COUNT(email_persona), entrada, salida, espacio_id FROM visita WHERE (rango de fechas que quieres))
WHERE COUNT(email_persona)>2

/*
RFC9
*/

SELECT nombre, email
FROM (SELECT email_personas
 WHERE entrada BETWEEN  DATEADD(Day, -10 , fecha dada 1)  
    AND  DATEADD(Day, -10 , fecha dada2)
    AND salida  BETWEEN  DATEADD(Day, -10 , fecha dada 1)  
    AND  DATEADD(Day, -10 , fecha dada2)  ) INNER JOIN Persona