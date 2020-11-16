package uniandes.isis2304.ccandes.negocio;

public class Espacio implements VOEspacio {

	public Espacio() {
		this.idesp = 0;
		this.nombre = "";
		this.aforo = 0;
		this.tipoespacio = "";
		this.personasadentro = 0;
		this.estado = 0;
		this.tipocomercio = "";	}

	public Espacio(long idesp, String nombre, int aforo, String tipoespacio, int personasadentro, int estado,
			String tipocomercio) {
		this.idesp = idesp;
		this.nombre = nombre;
		this.aforo = aforo;
		this.tipoespacio = tipoespacio;
		this.personasadentro = personasadentro;
		this.estado = estado;
		this.tipocomercio = tipocomercio;
	}

	private long idesp;

	private String nombre;

	private int aforo;

	private String tipoespacio;

	private int personasadentro;
	
	private int estado;
	
	private String tipocomercio;

	public long getIdesp() {
		return idesp;
	}

	public void setIdesp(long idesp) {
		this.idesp = idesp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public String getTipoespacio() {
		return tipoespacio;
	}

	public void setTipoespacio(String tipoespacio) {
		this.tipoespacio = tipoespacio;
	}

	public int getPersonasadentro() {
		return personasadentro;
	}

	public void setPersonasadentro(int personasadentro) {
		this.personasadentro = personasadentro;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getTipocomercio() {
		return tipocomercio;
	}

	public void setTipocomercio(String tipocomercio) {
		this.tipocomercio = tipocomercio;
	}

	@Override
	public String toString() {
		return "Espacio [idesp=" + idesp + ", nombre=" + nombre + ", aforo=" + aforo + ", tipoespacio=" + tipoespacio
				+ ", personasadentro=" + personasadentro + ", estado=" + estado + ", tipocomercio=" + tipocomercio
				+ "]";
	}

	



}
