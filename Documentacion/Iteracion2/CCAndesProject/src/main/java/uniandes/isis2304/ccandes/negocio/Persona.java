package uniandes.isis2304.ccandes.negocio;

public class Persona implements VOPersona {

	public Persona() {
		this.nombre = "";
		this.identificacion = 0;
		this.email = "";
		this.telefono = 0;
		this.emertelefono = 0;
		this.emernombre = "";
		this.establecimientodondetrabaja = 0;
		this.companiadomicilios = "";
		this.tipovisitante = "";
	}

	public Persona(String nombre, int identificacion, String email, int telefono, int emertelefono, String emernombre,
			int establecimientodondetrabaja, String companiadomicilios, String tipovisitante) {
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.email = email;
		this.telefono = telefono;
		this.emertelefono = emertelefono;
		this.emernombre = emernombre;
		this.establecimientodondetrabaja = establecimientodondetrabaja;
		this.companiadomicilios = companiadomicilios;
		this.tipovisitante = tipovisitante;
	}

	private String nombre;
	
	private int identificacion;
	
	private String email;
	
	private int telefono;
	
	private int emertelefono;
	
	private String emernombre;
	
	private int establecimientodondetrabaja;
	
	private String companiadomicilios;
	
	private String tipovisitante;

	public String getNombre() {
		return nombre;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public String getEmail() {
		return email;
	}

	public int getTelefono() {
		return telefono;
	}

	public int getEmertelefono() {
		return emertelefono;
	}

	public String getEmernombre() {
		return emernombre;
	}

	public int getEstablecimientodondetrabaja() {
		return establecimientodondetrabaja;
	}

	public String getCompaniadomicilios() {
		return companiadomicilios;
	}

	public String getTipovisitante() {
		return tipovisitante;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public void setEmertelefono(int emertelefono) {
		this.emertelefono = emertelefono;
	}

	public void setEmernombre(String emernombre) {
		this.emernombre = emernombre;
	}

	public void setEstablecimientodondetrabaja(int establecimientodondetrabaja) {
		this.establecimientodondetrabaja = establecimientodondetrabaja;
	}

	public void setCompaniadomicilios(String companiadomicilios) {
		this.companiadomicilios = companiadomicilios;
	}

	public void setTipovisitante(String tipovisitante) {
		this.tipovisitante = tipovisitante;
	}
	
	
}
