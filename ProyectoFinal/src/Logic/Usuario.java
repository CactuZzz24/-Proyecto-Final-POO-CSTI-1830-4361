package Logic;

import java.io.Serializable;

public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8876152773256353458L;
	private Persona persona;
	private String cedula;
	private String clave;
	private boolean paciente;
	private boolean admin;
	
	public Usuario(Persona persona, String usuario, String clave, boolean paciente, boolean admin) {
		super();
		this.persona = persona;
		this.cedula = usuario;
		this.clave = clave;
		this.paciente = paciente;
		this.admin = admin;
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public boolean isPaciente() {
		return paciente;
	}
	public void setPaciente(boolean paciente) {
		this.paciente = paciente;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}
