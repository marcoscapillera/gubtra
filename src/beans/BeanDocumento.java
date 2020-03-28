package beans;

public class BeanDocumento {
	
	private Long numdocumento;	
	private String nombredocumento;
	private String validez;
	private String fotoBase64;
	private String contentType;

	
	private String tempFotoDocumento;
	
	
	public String getTempFotoDocumento() {
		
		tempFotoDocumento = "data:"+contentType + ";base64," + fotoBase64;
		return tempFotoDocumento;
	}
	

	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public Long getNumdocumento() {
		return numdocumento;
	}


	public void setNumdocumento(Long numdocumento) {
		this.numdocumento = numdocumento;
	}


	public String getNombredocumento() {
		return nombredocumento;
	}


	public void setNombredocumento(String nombredocumento) {
		this.nombredocumento = nombredocumento;
	}


	public String getValidez() {
		return validez;
	}


	public void setValidez(String validez) {
		this.validez = validez;
	}


	public void setTempFotoTramite(String tempFotoTramite) {
		this.tempFotoDocumento = tempFotoTramite;
	}

	
	

}
