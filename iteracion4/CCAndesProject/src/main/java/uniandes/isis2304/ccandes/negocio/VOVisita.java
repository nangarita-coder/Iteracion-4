package uniandes.isis2304.ccandes.negocio;

import java.sql.Timestamp;

public interface VOVisita {

	public int getIdvisita();

	public Timestamp getEntrada();

	public Timestamp getSalida();

	public int getTemperatura();
	
	public String getEmailpersona();

	public int getEspacioid();
}
