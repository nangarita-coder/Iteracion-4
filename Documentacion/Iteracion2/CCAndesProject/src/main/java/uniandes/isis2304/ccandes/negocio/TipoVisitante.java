package uniandes.isis2304.ccandes.negocio;

public class TipoVisitante implements VOTipoVisitante{
private String tipo;
	
	public TipoVisitante() {
		setTipo("default");
	}

	public TipoVisitante(String tipo) {
		setTipo(tipo);
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
