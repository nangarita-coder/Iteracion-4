/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: CCAndes Uniandes
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

import uniandes.isis2304.ccandes.negocio.Espacio;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Espacio de CCAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Santiago Forero
 */
class SQLEspacio 
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
	public SQLEspacio (PersistenciaCCAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Espacio a la base de datos de CCAndes
	 * @param pm
	 * @param idesp
	 * @param nombre
	 * @param aforo
	 * @param tipoespacio
	 * @param personasadentro
	 * @param estado
	 * @param tipocomercio
	 * @param tipoEstablecimiento
	 * @return
	 */
	public long adicionarEspacio (PersistenceManager pm, long idesp, String nombre, int aforo, String tipoespacio, int personasadentro, int estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEspacio () + "(idesp, nombre, aforo, tipoespacio, personasadentro,estado, tipocomercio ) values (?, ?, ?, ?, ?,?,?)");
        q.setParameters(idesp, nombre, aforo, tipoespacio, personasadentro, estado, null);
        return (long) q.executeUnique();					
	}

	
	public long adicionarEstablecimiento (PersistenceManager pm, long idesp, String nombre, int aforo, int personasadentro, int estado, String tipocomercio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEspacio () + "(idesp, nombre, aforo,tipoespacio, personasadentro,estado, tipocomercio ) values (?, ?, ?, ?, ?,?,?)");
        q.setParameters(idesp, nombre, aforo, "ESTABLECIMIENTO" , personasadentro, estado, tipocomercio);
        return (long) q.executeUnique();					
	}


	/**
	 * TODO SIN HACER
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Espacio de la 
	 * base de datos de CCAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEspacio - El identificador del Espacio
	 * @return El objeto Espacio que tiene el identificador dado
	 */
	public Espacio darEspacioPorId (PersistenceManager pm, long idesp) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEspacio () + " WHERE idesp = ?");
		q.setResultClass(Espacio.class);
		q.setParameters(idesp);
		return (Espacio) q.executeUnique();
	}

	/**
	 * TODO NO FUNCIONA
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Espacios de la 
	 * base de datos de CCAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Espacios
	 */
	public List<Espacio> darEspacios (PersistenceManager pm)
	
	{
		System.out.println(pp.darTablaEspacio ());
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEspacio ());
		q.setResultClass(Espacio.class);
		System.out.println(q.execute());
		return (List<Espacio>) q.executeList();
	}
	
	public String cambioEstadoEspacio(PersistenceManager pm, long id, String nuevoEstado) 
	{		
			Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaEspacio () + " SET estado = ? WHERE idesp = ?");
		    q.setParameters(nuevoEstado , id );
		  
		return "Cambio exitoso";
	}
	
}
