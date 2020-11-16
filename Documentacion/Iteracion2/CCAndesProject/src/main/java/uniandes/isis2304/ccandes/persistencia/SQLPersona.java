package uniandes.isis2304.ccandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.ccandes.negocio.Espacio;
import uniandes.isis2304.ccandes.negocio.Persona;
import uniandes.isis2304.ccandes.negocio.Visita;



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



	public String cambioEstadoVisitante(PersistenceManager pm, long email, String nuevoEstado) 
	{
		if(nuevoEstado.equals("positivo"))
		{
			Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE email = ?");
			q.setParameters(nuevoEstado , email );


			Query q2 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisita() + " WHERE emailpersona = ?");
			q.setResultClass(Visita.class);
			q.setParameters(email);
			Visita vi = (Visita) q.executeUnique();
			long idespacio =vi.getEspacioid();

			Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaEspacio () + " SET estado = ? WHERE idesp = ?");
			q.setParameters("rojo" , idespacio );


			List<Visita> v =pp.darVisitanPorEspacio(idespacio);

			for (int i = 0; i < v.size(); i++) 
			{
				Visita actual = v.get(i);
				if (vi.getEntrada().after(actual.getEntrada())&&vi.getSalida().before(actual.getEntrada())) {
					Query q4 = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE email = ?");
					q.setParameters("rojo" , actual.getEmailpersona() );
				}
				
			}

		}
	
	else{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE email = ?");
		q.setParameters(nuevoEstado , email );
	}









	return "Cambio exitoso";
}
}
