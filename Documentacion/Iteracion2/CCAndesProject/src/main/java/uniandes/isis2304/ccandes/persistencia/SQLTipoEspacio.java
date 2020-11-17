/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.ccandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.ccandes.negocio.TipoEspacio;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Espacio de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLTipoEspacio 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaCCAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaCCAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLTipoEspacio (PersistenciaCCAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Espacio a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idEspacio - El identificador del Espacio
	 * @param nombre - El nombre del Espacio
	 * @param ciudad - La ciudad del Espacio
	 * @param presupuesto - El presupuesto del Espacio (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Espacio
	 * @return El número de tuplas insertadas
	 */
	public long adicionarTipoEspacio (PersistenceManager pm, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoEspacio () + "(tipo) values (?)");
        q.setParameters(tipo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar EspacioES de la base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreEspacio - El nombre del Espacio
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEspaciosPorNombre (PersistenceManager pm, String nombreEspacio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEspacio () + " WHERE nombre = ?");
        q.setParameters(nombreEspacio);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Espacio de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEspacio - El identificador del Espacio
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEspacioPorId (PersistenceManager pm, long idEspacio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEspacio () + " WHERE id = ?");
        q.setParameters(idEspacio);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Espacio de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEspacio - El identificador del Espacio
	 * @return El objeto Espacio que tiene el identificador dado
	 */
	public TipoEspacio darEspacioPorId (PersistenceManager pm, long idEspacio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEspacio () + " WHERE id = ?");
		q.setResultClass(TipoEspacio.class);
		q.setParameters(idEspacio);
		return (TipoEspacio) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS EspacioES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreEspacio - El nombre de Espacio buscado
	 * @return Una lista de objetos Espacio que tienen el nombre dado
	 */
	public List<TipoEspacio> darEspaciosPorNombre (PersistenceManager pm, String nombreEspacio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEspacio () + " WHERE nombre = ?");
		q.setResultClass(TipoEspacio.class);
		q.setParameters(nombreEspacio);
		return (List<TipoEspacio>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS EspacioES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Espacio
	 */
	public List<TipoEspacio> darTipoEspacio (PersistenceManager pm)
	
	{
		System.out.println(pp.darTablaTipoEspacio ());
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoEspacio ());
		q.setResultClass(TipoEspacio.class);
		System.out.println(q.executeList());
		return (List<TipoEspacio>) q.executeList();
	}
 
	
}
