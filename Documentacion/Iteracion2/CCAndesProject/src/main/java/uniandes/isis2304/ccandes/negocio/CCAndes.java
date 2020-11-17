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

package uniandes.isis2304.ccandes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.ccandes.persistencia.PersistenciaCCAndes;


/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Santiago Forero
 */
public class CCAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(CCAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaCCAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public CCAndes ()
	{
		pp = PersistenciaCCAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public CCAndes (JsonObject tableConfig)
	{
		pp = PersistenciaCCAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Iteracion 3
	 *****************************************************************/
	public String cambioEstadoVisitante (String email,String nuevoEstado )
	{
		log.info ("Cambiando estado visitante");        
		String persona=  pp.cambioEstadoVisitante(email, nuevoEstado);
        log.info ("Se cambio el estado del visitante con email: " + email );
        return persona;
	}
	public String cambioEstadoEspacio(long id, String nuevoEstado)
	{
		log.info ("Cambiando estado espacio");        
		String persona=  pp.cambioEstadoEspacio(id, nuevoEstado);
        log.info ("Se cambio el estado del espacio con id: " + id );
        return persona;
	}
	public String deshabilitarEspacioTipo( String tipo) 
	{
		log.info ("Deshabilitando espacios");        
		String persona=  pp.deshabilitarEspacioTipo(tipo);
        log.info ("Se deshabilito los espacios con el tipo "+tipo );
        return persona;
	}
	public String  rehabilitarEspacioTipo( String tipo) 
	{
		log.info ("Reshabilitando espacios");        
		String persona=  pp.rehabilitarEspacioTipo(tipo);
        log.info ("Se deshabilito los espacios con el tipo "+tipo);
        return persona;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ESPACIOS
	 *****************************************************************/
	public List<VOEspacio> darVOEspacios ()
	{
		log.info ("Generando los espacios");        
        List<VOEspacio> voTipos = new LinkedList<VOEspacio> ();
        for (Espacio tb : pp.darEspacios())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los espacios: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	public Espacio adicionarEspacios (long idesp,String nombre, int aforo, String tipoespacio, int personasadentro,int estado)
	{
		log.info ("Adicionando Espacio");        
		Espacio espacio=  pp.adicionarEspacio(idesp, nombre, aforo, tipoespacio, personasadentro, estado);
        log.info ("Se ha agregado el espacio: " + espacio.getNombre() );
        return espacio;
	}

	
	public Espacio adicionarEstablecimiento (long idesp,String nombre, int aforo, String tipoespacio, int personasadentro,int estado,String tipocomercio)
	{
		log.info ("Adicionando Espacio");        
		Espacio espacio=  pp.adicionarEstablecimiento(idesp, nombre, aforo, tipoespacio, personasadentro, estado, tipocomercio);
        log.info ("Se ha agregado el establecimiento: " + espacio.getNombre() );
        return espacio;
	}

	
	

	

	
	/* ****************************************************************
	 * 			Métodos para manejar las Personas
	 *****************************************************************/
	
	public List<VOPersona> darVOPersona ()
	{
		log.info ("Generando los VOPersona");        
        List<VOPersona> voTipos = new LinkedList<VOPersona> ();
        for (VOPersona tb : pp.darPersonas())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los tipos de espacios: " + voTipos.size() + " existentes");
        return voTipos;
	}
	

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
//	public long [] limpiarParranderos ()
//	{
//        log.info ("Limpiando la BD de Parranderos");
//        long [] borrrados = pp.limpiarParranderos();	
//        log.info ("Limpiando la BD de Parranderos: Listo!");
//        return borrrados;
//	}
}
