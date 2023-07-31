/**
 * 
 */
package Logic;

import java.io.Serializable;

/**
 * @author edwin
 *
 */
public class Enfermedad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1920903675029221677L;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String gravedad; // Puede ser "Leve", "Moderada" o "Grave"
	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param gravedad
	 */
	public Enfermedad(String codigo, String nombre, String descripcion, String gravedad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.gravedad = gravedad;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the gravedad
	 */
	public String getGravedad() {
		return gravedad;
	}
	/**
	 * @param gravedad the gravedad to set
	 */
	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

}
