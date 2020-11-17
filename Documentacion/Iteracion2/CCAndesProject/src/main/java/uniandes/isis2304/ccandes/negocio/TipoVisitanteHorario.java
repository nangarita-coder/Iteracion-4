package uniandes.isis2304.ccandes.negocio;

public class TipoVisitanteHorario implements VOTipoVisitanteHorario {
	
	public TipoVisitanteHorario() {
		this.idtipovisitante = "";
		this.idhorario = 0;
	}

	public TipoVisitanteHorario(String idtipovisitante, int idhorario) {
		this.idtipovisitante = idtipovisitante;
		this.idhorario = idhorario;
	}

	private String idtipovisitante;
	
	private int idhorario;

	public String getIdtipovisitante() {
		return idtipovisitante;
	}

	public int getIdhorario() {
		return idhorario;
	}

	public void setIdtipovisitante(String idtipovisitante) {
		this.idtipovisitante = idtipovisitante;
	}

	public void setIdhorario(int idhorario) {
		this.idhorario = idhorario;
	}
}
