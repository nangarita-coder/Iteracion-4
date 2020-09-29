SELECT *
FROM (
SELECT  
bebedores.ciudad, tipo_bebida.nombre, COUNT(gustan.id_bebida) as favs
--SELECT bebedores.ciudad, gustan.id_bebida, COUNT(gustan.id_bebida)
FROM gustan 
JOIN bebedores
    on gustan.id_bebedor = bebedores.id
JOIN tipo_bebida
    on gustan.id_bebida = tipo_bebida.id
GROUP BY tipo_bebida.nombre,bebedores.ciudad  
HAVING COUNT(id_bebida)>1
ORDER BY bebedores.ciudad asc, COUNT(id_bebida) DESC
)
WHERE   rownum <= 3
;
