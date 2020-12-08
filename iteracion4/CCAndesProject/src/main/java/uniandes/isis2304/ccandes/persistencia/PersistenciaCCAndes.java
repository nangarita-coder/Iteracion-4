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


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.ccandes.negocio.Espacio;
import uniandes.isis2304.ccandes.negocio.Persona;
import uniandes.isis2304.ccandes.negocio.TipoEspacio;
import uniandes.isis2304.ccandes.negocio.Visita;




/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaCCAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaCCAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaCCAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;
	
//	/**
//	 * Atributo para el acceso a la tabla TIPOBEBIDA de la base de datos
//	 */
	private SQLEspacio sqlEspacio;
	
	
	/**
//	 * Atributo para el acceso a la tabla TIPOESPACIO de la base de datos
//	 */
	private SQLTipoEspacio sqlTipoEspacio;
//	
//	
	/**
	 * Atributo para el acceso a la tabla PERSONA de la base de datos
	 */
	private SQLPersona sqlPersona;
	
//	/**
//	 * Atributo para el acceso a la tabla BAR de la base de datos
//	 */
//	private SQLBar sqlBar;
//	
//	/**
//	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
//	 */
//	private SQLBebedor sqlBebedor;
//	
//	/**
//	 * Atributo para el acceso a la tabla GUSTAN de la base de datos
//	 */
//	private SQLGustan sqlGustan;
//	
//	/**
//	 * Atributo para el acceso a la tabla SIRVEN de la base de datos
//	 */
//	private SQLSirven sqlSirven;
//	
//	/**
//	 * Atributo para el acceso a la tabla VISITAN de la base de datos
//	 */
	private SQLVisita sqlVisita;
//	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaCCAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("CCAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("ccandes_sequence");//0
		tablas.add ("ESPACIO");//1
		tablas.add ("HORARIO");//2
		tablas.add ("PERSONA");//3
		tablas.add ("TIPOCOMERCIO");//4
		tablas.add ("TIPOCOMERCIOHORARIO");//5
		tablas.add ("TIPOESPACIO");//6
		tablas.add ("TIPOVISITANTE");//7
		tablas.add ("TIPOVISITANTEHORARIO");//8
		tablas.add ("VISITA");//9

}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaCCAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaCCAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaCCAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaCCAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaCCAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;
		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlEspacio = new SQLEspacio(this);
		sqlTipoEspacio = new SQLTipoEspacio(this);
		sqlPersona = new SQLPersona(this);
//		sqlBebida = new SQLBebida(this);
//		sqlBar = new SQLBar(this);
//		sqlBebedor = new SQLBebedor(this);
//		sqlGustan = new SQLGustan(this);
//		sqlSirven = new SQLSirven (this);
	sqlVisita = new SQLVisita(this);		
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqCCAndes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de espacio de parranderos
	 */
	public String darTablaEspacio ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaTipoEspacio ()
	{
		return tablas.get (6);
	}
	
	
	public String darTablaPersona ()
	{
		return tablas.get (3);
	}
//
//	/**
//	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
//	 */
//	public String darTablaBar ()
//	{
//		return tablas.get (3);
//	}
//
//	/**
//	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
//	 */
//	public String darTablaBebedor ()
//	{
//		return tablas.get (4);
//	}
//
//	/**
//	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
//	 */
//	public String darTablaGustan ()
//	{
//		return tablas.get (5);
//	}
//
//	/**
//	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
//	 */
//	public String darTablaSirven ()
//	{
//		return tablas.get (6);
//	}
//
//	/**
//	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
//	 */
//	public String darTablaVisitan ()
//	{
//		return tablas.get (7);
//	}
//	
	public String darTablaVisita ()
{
		return tablas.get (10);}
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ESPACIOS
	 *****************************************************************/

	public List<Espacio> darEspacios ()
	{
		return sqlEspacio.darEspacios(pmf.getPersistenceManager());
	}
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Espacio adicionarEspacio(long idesp,String nombre, int aforo,String tipoespacio, int personasadentro,int estado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlEspacio.adicionarEspacio(pm, idesp, nombre, aforo, tipoespacio, personasadentro, estado);
            tx.commit();
            
            log.trace ("Inserción de espacio");
            
            return new Espacio(idesp, nombre, aforo, tipoespacio, personasadentro, estado, null);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Espacio adicionarEstablecimiento(long idesp,String nombre, int aforo,String tipoespacio, int personasadentro,int estado,String tipocomercio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlEspacio.adicionarEstablecimiento(pm, idesp, nombre, aforo, personasadentro, estado, tipocomercio);
            tx.commit();
            
            log.trace ("Inserción de espacio");
            
            return new Espacio(idesp, nombre, aforo, tipoespacio, personasadentro, estado, tipocomercio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ESPACIOS
	 *****************************************************************/

	public List<TipoEspacio> darTipoEspacios ()
	{
		return sqlTipoEspacio.darTipoEspacio(pmf.getPersistenceManager());
	}
	
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public TipoEspacio adicionarTipoEspacio(String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idTipoBebida = nextval ();
            long tuplasInsertadas = sqlTipoEspacio.adicionarTipoEspacio(pm, tipo);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoEspacio(tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los PERSONA
	 *****************************************************************/
	
	public List<Persona> darPersonas ()
	{
		return sqlPersona.darEspacios(pmf.getPersistenceManager());
	}
	
	
	
//
//	/**
//	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el nombre del tipo de bebida
//	 * Adiciona entradas al log de la aplicación
//	 * @param nombre - El nombre del tipo de bebida
//	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
//	 */
//	public long eliminarTipoBebidaPorNombre (String nombre) 
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();
//            long resp = sqlTipoBebida.eliminarTipoBebidaPorNombre(pm, nombre);
//            tx.commit();
//            return resp;
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//            return -1;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//
//	/**
//	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
//	 * Adiciona entradas al log de la aplicación
//	 * @param idTipoBebida - El identificador del tipo de bebida
//	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
//	 */
//	public long eliminarTipoBebidaPorId (long idTipoBebida) 
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();
//            long resp = sqlTipoBebida.eliminarTipoBebidaPorId(pm, idTipoBebida);
//            tx.commit();
//            return resp;
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//            return -1;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
//	}
//
//	/**
//	 * Método que consulta todas las tuplas en la tabla TipoBebida
//	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
//	 */
//	public List<TipoBebida> darTiposBebida ()
//	{
//		return sqlTipoBebida.darTiposBebida (pmf.getPersistenceManager());
//	}
// 
//	/**
//	 * Método que consulta todas las tuplas en la tabla TipoBebida que tienen el nombre dado
//	 * @param nombre - El nombre del tipo de bebida
//	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
//	 */
//	public List<TipoBebida> darTipoBebidaPorNombre (String nombre)
//	{
//		return sqlTipoBebida.darTiposBebidaPorNombre (pmf.getPersistenceManager(), nombre);
//	}
// 
//	/**
//	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
//	 * @param idTipoBebida - El identificador del tipo de bebida
//	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
//	 */
//	public TipoBebida darTipoBebidaPorId (long idTipoBebida)
//	{
//		return sqlTipoBebida.darTipoBebidaPorId (pmf.getPersistenceManager(), idTipoBebida);
//	}
// 
	/**
	 * M�todo que consulta todas las tuplas en la tabla VISITAN
	 * @return La lista de objetos VISITAN, construidos con base en las tuplas de la tabla VISITAN
	 */
	public List<Visita> darVisitanPorEspacio (long id)
	{
		return sqlVisita.darVisitanPorEspacio(pmf.getPersistenceManager(),id);
	}	
	/* ****************************************************************
	 * 			Metodos iteracion 3
	 *****************************************************************/
	/**
	 *RF8
	 */
	
	public String cambioEstadoVisitante (String email,String nuevoEstado )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            String resp = sqlPersona.cambioEstadoVisitante(pm, email,nuevoEstado);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
    	    e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return "no se pudo hacer el cambio";
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 *RF9
	 */
	public String cambioEstadoEspacio(long id, String nuevoEstado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            String resp = sqlEspacio.cambioEstadoEspacio(pm, id, nuevoEstado);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
    	    e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return "no se pudo hacer el cambio";
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 *RF11
	 */
	public String deshabilitarEspacioTipo( String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            String resp = sqlEspacio.deshabilitarEspacioTipo( pm,  tipo) ;
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
    	    e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return "no se pudo hacer el cambio";
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 *RF12
	 */
	public String rehabilitarEspacioTipo( String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            String resp = sqlEspacio.rehabilitarEspacioTipo(pm, tipo) ;
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
    	    e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return "no se pudo hacer el cambio";
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 }
