/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogot�	- Colombia)
 * Departamento	de	Ingenier�a	de	Sistemas	y	Computaci�n
 * Licenciado	bajo	el	esquema	Academic Free License versi�n 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germ�n Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jim�nez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.ccandes.negocio.Visita;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto VISITAN de Parranderos
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 * 
 * @author Germ�n Bravo
 */
class SQLVisita 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaCCAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaCCAndes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLVisita (PersistenciaCCAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITAN a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param idBar - El identificador del bar
	 * @param fecha - La fecha en que se realiz� la visita
	 * @param horario - EL horario en que se realiz� la visita (DIURNO, NOCTURNO, TODOS)
	 * @return EL n�mero de tuplas insertadas
	 */
	public long adicionarVisitan (PersistenceManager pm, long idBebedor, long idBar, Timestamp fecha, String horario) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitan () + "(idbebedor, idbar, fechavisita, horario) values (?, ?, ?, ?)");
        q.setParameters(idBebedor, idBar, fecha, horario);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TODAS LAS VISITAS de la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarVisitan (PersistenceManager pm) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitan ());
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITAN de la base de datos de Parranderos, por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param idBar - El identificador del bar
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarVisitan (PersistenceManager pm, long idBebedor, long idBar) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitan () + " WHERE idbebedor = ? AND idbar = ?");
        q.setParameters(idBebedor, idBar);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para ELIMINAR TODAS LAS VISITAS DE UN BEBEDOR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarVisitanPorIdBebedor (PersistenceManager pm, long idBebedor) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitan () + " WHERE idbebedor = ?");
        q.setParameters(idBebedor);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para ELIMINAR TODAS LAS VISITAS HECHAS A UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return EL n�mero de tuplas eliminadas
	 */
	public long eliminarVisitanPorIdBar (PersistenceManager pm, long idBar) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitan () + " WHERE idBar = ?");
        q.setParameters(idBar);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de los VISITAN de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VISITAN
	 */
	public List<Visita> darVisitanPorEspacio (PersistenceManager pm,long espid)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisita ()+ " WHERE espacioid  = ?");
		q.setResultClass(Visita.class);
		return (List<Visita>) q.execute();
	}

	
}
