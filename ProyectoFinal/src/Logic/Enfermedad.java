/**
 * 
 */
package Logic;

import java.io.Serializable;

public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1920903675029221677L;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String gravedad; // Puede ser "Leve", "Moderada" o "Grave"
	
	public Enfermedad(String codigo, String nombre, String descripcion, String gravedad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.gravedad = gravedad;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getGravedad() {
		return gravedad;
	}
	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

}
