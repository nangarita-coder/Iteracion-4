
SELECT bebedores.nombre, bebedores.ciudad, gustan.id_bebida, bebidas.grado_alcohol
FROM bebedores
JOIN gustan 
    ON bebedores.id = gustan.id_bebedor
JOIN bebidas
    ON bebidas.id = gustan.id_bebida
WHERE (bebidas.grado_alcohol > 10
AND bebedores.presupuesto = 'Alto')
ORDER BY bebedores.nombre ASC
;
