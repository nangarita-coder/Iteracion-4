package uniandes.isis2304.ccandes.negocio;

public class TipoEspacio implements VOTipoEspacio {
	
	private String tipo;
	
	public TipoEspacio() {
		setTipo("default");
	}

	public TipoEspacio(String tipo) {
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
