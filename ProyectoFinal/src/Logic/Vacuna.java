/**
 * 
 */
package Logic;

import java.io.Serializable;

public class Vacuna implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2810176989299158222L;
	private String codigo;
	private String nombre;
	private String descripcion;
	private int edadRequerida;
	private int cantidadDisponible;
	private String codLote;
	private String distribuidor;
	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param edadRequerida
	 * @param cantidadDisponible
	 * @param codLote
	 * @param distribuidor
	 */
	public Vacuna(String codigo, String nombre, String descripcion, int edadRequerida, int cantidadDisponible,
			String codLote, String distribuidor) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.edadRequerida = edadRequerida;
		this.cantidadDisponible = cantidadDisponible;
		this.codLote = codLote;
		this.distribuidor = distribuidor;
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
	 * @return the edadRequerida
	 */
	public int getEdadRequerida() {
		return edadRequerida;
	}
	/**
	 * @param edadRequerida the edadRequerida to set
	 */
	public void setEdadRequerida(int edadRequerida) {
		this.edadRequerida = edadRequerida;
	}
	/**
	 * @return the cantidadDisponible
	 */
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	/**
	 * @param cantidadDisponible the cantidadDisponible to set
	 */
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	/**
	 * @return the codLote
	 */
	public String getCodLote() {
		return codLote;
	}
	/**
	 * @param codLote the codLote to set
	 */
	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}
	/**
	 * @return the distribuidor
	 */
	public String getDistribuidor() {
		return distribuidor;
	}
	/**
	 * @param distribuidor the distribuidor to set
	 */
	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
}
