package uniandes.isis2304.ccandes.negocio;

public class TipoVisitante implements VOTipoVisitante{
private String tipo;
private String horario_id;

	
	public TipoVisitante() {
		setTipo("default");
	}

	public TipoVisitante(String tipo,String horario_id) {
		setTipo(tipo);
		setHorario_id(horario_id);
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

	/**
	 * @return the horario_id
	 */
	public String getHorario_id() {
		return horario_id;
	}

	/**
	 * @param horario_id the horario_id to set
	 */
	public void setHorario_id(String horario_id) {
		this.horario_id = horario_id;
	}
}
