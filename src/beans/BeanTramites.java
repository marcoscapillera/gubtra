package beans;

public class BeanTramites {
	
	private Long id;	
	private String fecha;
	private String costo;
	private String tramite;
	
	public void setTramite(String tramite) {
		this.tramite = tramite;
	}
	
	
	public String getTramite() {
		return tramite;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	

	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}


}
