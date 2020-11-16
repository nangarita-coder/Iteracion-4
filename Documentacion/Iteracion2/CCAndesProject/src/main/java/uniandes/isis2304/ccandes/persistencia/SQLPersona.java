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


public String cambioEstadoVisitante(PersistenceManager pm, long id, String nuevoEstado) 
{
	if(nuevoEstado.equals("positivo"))
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE identificacion = ?");
	    q.setParameters(nuevoEstado , id );
	  
		
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLector() + " WHERE idVisitante = ?");
		q.setResultClass(Lector.class);
		q.setParameters(id);
		long idespacio = (Lector) q.executeUnique().darIdEspacio();
		
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaEspacio () + " SET estado = ? WHERE idesp = ?");
	    q.setParameters("rojo" , idespacio );
	  
		
		Query q = pm.newQuery(SQL, "SELECT idPersona FROM " + falat from);
		List lista = q.executeList();
		for (int i = 0; i < lista.size(); i++) {
			int actual = (int) lista.get(i);
			Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE identificacion = ?");
		    q.setParameters("rojo" , actual );
			
		}
	}
	else{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE identificacion = ?");
	    q.setParameters(nuevoEstado , id );
	}
	
	
	
	
	
	
	
	
	
	return "Cambio exitoso";
}
}
