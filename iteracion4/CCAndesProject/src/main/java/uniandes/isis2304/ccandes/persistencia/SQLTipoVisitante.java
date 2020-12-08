package uniandes.isis2304.ccandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.ccandes.negocio.TipoVisitante;


public class SQLTipoVisitante {

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
	
	
	public SQLTipoVisitante (PersistenciaCCAndes pp)
	{
		this.pp = pp;
	}

	
	
public List<TipoVisitante> darEspacios (PersistenceManager pm)
	
	{
//		System.out.println(pp.darTablaEspacio ());
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEspacio ());
		q.setResultClass(TipoVisitante.class);
//		System.out.println(q.execute());
		return (List<TipoVisitante>) q.execute();
	}
}
