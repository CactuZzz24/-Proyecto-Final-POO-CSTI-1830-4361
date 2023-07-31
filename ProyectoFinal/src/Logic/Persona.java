package Logic;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {
	private static final long serialVersionUID = 1129465876595645426L;
	private String cedula;
	private String nombre;
	private Date fchNacim;
	private String telefono;
	private String direccion;
	private char genero;
	
	public Persona(String cedula, String nombre, Date fchNacim, String telefono, String direccion, char genero) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.fchNacim = fchNacim;
		this.telefono = telefono;
		this.direccion = direccion;
		this.genero = genero;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFchNacim() {
		return fchNacim;
	}
	public void setFchNacim(Date fchNacim) {
		this.fchNacim = fchNacim;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}

	
	
}
