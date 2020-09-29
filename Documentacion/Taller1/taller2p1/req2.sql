ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;
SELECT bebidas.nombre as Nombre_bebida, bebidas.grado_alcohol as Grado_alcohol,
    bares.ciudad, COUNT(sirven.id_bebida) as Servido_En
--SELECT bares.id as bar_id, bares.ciudad as ciudad ,
--    bebedores.id as bebedor_id, gustan.id_bebida as gustan_id, COUNT(gustan.id_bebida)
--SELECT *
FROM sirven
JOIN bares 
    on sirven.id_bar = bares.id
JOIN bebedores
    on bebedores.ciudad = bares.ciudad
JOIN gustan
    on bebedores.id = gustan.id_bebedor and sirven.id_bebida = gustan.id_bebida 
JOIN bebidas
    on gustan.id_bebida = bebidas.id 
GROUP BY bebidas.nombre, bebidas.grado_alcohol ,bares.ciudad
  HAVING COUNT(sirven.id_bebida)>=1
ORDER BY bebidas.nombre ASC, bares.ciudad ASC
;

