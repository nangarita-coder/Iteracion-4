package uniandes.isis2304.ccandes.negocio;

public class TipoComercioHorario implements VOTipoComercioHorario {
	
	public TipoComercioHorario() {
		this.idtipocomercio = "";
		this.idhorario = 0;
	}

	public TipoComercioHorario(String idtipocomercio, int idhorario) {
		this.idtipocomercio = idtipocomercio;
		this.idhorario = idhorario;
	}

	private String idtipocomercio;
	
	private int idhorario;

	public String getIdtipocomercio() {
		return idtipocomercio;
	}

	public int getIdhorario() {
		return idhorario;
	}

	public void setIdtipocomercio(String idtipocomercio) {
		this.idtipocomercio = idtipocomercio;
	}

	public void setIdhorario(int idhorario) {
		this.idhorario = idhorario;
	}
	

}
