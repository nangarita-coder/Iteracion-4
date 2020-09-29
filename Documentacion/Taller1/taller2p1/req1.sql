ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;
SELECT bebedores.nombre as Nombre_bebedor,bebedores.presupuesto as Presupuesto_bebedor,
    bebedores.ciudad as Ciudad_bebedor, bares.nombre as Nombre_bar, bares.ciudad as Ciudad_bar,
    sirven.horario as Horario
    
FROM bebedores
JOIN bares
    ON bebedores.presupuesto = bares.presupuesto
JOIN gustan
    ON bebedores.id = gustan.id_bebedor
JOIN bebidas
    ON bebedores.id = gustan.id_bebedor
JOIN sirven
    ON gustan.id_bebida = sirven.id_bebida
    AND sirven.id_bar = bares.id
  
WHERE NOT bebedores.ciudad = bares.ciudad
GROUP BY bebedores.nombre,bebedores.presupuesto,
    bebedores.ciudad, bares.nombre, bares.ciudad,sirven.horario

ORDER BY bebedores.nombre ASC

;
