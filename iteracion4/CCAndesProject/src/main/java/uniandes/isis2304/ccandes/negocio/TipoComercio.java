package uniandes.isis2304.ccandes.negocio;

public class TipoComercio implements VOTipoComercio {
	
	public TipoComercio() {
		this.tipo = "";
	}

	public TipoComercio(String tipo) {
		this.tipo = tipo;
	}

	private  String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
