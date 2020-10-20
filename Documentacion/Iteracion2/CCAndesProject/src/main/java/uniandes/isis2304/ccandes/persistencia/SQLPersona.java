package uniandes.isis2304.ccandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.ccandes.negocio.Espacio;
import uniandes.isis2304.ccandes.negocio.Persona;

public class SQLPersona {

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
	public SQLPersona (PersistenciaCCAndes pp)
	{
		this.pp = pp;
	}
	
	
public List<Persona> darEspacios (PersistenceManager pm)
	
	{
		System.out.println(pp.darTablaPersona ());
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersona ());
		q.setResultClass(Persona.class);
		System.out.println(q.executeList());
		return (List<Persona>) q.executeList();
	}
}
