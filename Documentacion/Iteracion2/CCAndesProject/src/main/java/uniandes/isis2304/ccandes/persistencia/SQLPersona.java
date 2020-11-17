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



	public String cambioEstadoVisitante(PersistenceManager pm, String email, String nuevoEstado) 
	{ String respuesta;
		if(nuevoEstado.equals("positivo"))
		{
			 respuesta = "Se cambio el estado de la persona con email "+email+" a rojo ";      
			Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE email = ?");
			q.setParameters(nuevoEstado , email );


			Query q2 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisita() + " WHERE emailpersona = ?");
			q2.setResultClass(Visita.class);
			q2.setParameters(email);
			List<Visita> visi = (List<Visita>) q.executeList();
			for (int j = 0; j < visi.size(); j++)
			{ 
				Visita lugar = visi.get(j);
				Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaEspacio () + " SET estado = ? WHERE idesp = ?");
				q3.setParameters("rojo" , lugar.getEspacioid() );

				respuesta+= "se cambio el estado del espacio con id "+lugar.getEspacioid()+" a rojo ";
				List<Visita> v =pp.darVisitanPorEspacio(lugar.getEspacioid());

				for (int i = 0; i < v.size(); i++) 
				{
					Visita actual = v.get(i);
					if (actual.getEntrada().after(lugar.getEntrada())&&actual.getEntrada().before(lugar.getSalida())) {
						Query q4 = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE email = ?");
						q4.setParameters("rojo" , actual.getEmailpersona() );
						respuesta+= "se cambio el estado de la persona con email "+actual.getEmailpersona()+" a rojo ";
					}

				}



			}



		}

		else{
			Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersona () + " SET estado = ? WHERE email = ?");
			q.setParameters(nuevoEstado , email );
			respuesta = "Se cambio el estado de la persona con email "+email+"a rojo ";      
		}

		return respuesta;
	}
}
