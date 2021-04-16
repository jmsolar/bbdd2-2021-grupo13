package ar.edu.unlp.info.bd2.models;

public class Provider {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCuit() {
		return cuit;
	}

	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}

	public String name;
	public Long cuit;
	
	public Provider(String name, Long cuit) {
		this.name = name;
		this.cuit = cuit;
	}
}