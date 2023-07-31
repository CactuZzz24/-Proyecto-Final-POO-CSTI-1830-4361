/**
 * 
 */
package Logic;

import java.io.Serializable;

public class Vacuna implements Serializable{
	private static final long serialVersionUID = -2810176989299158222L;
	private String codigo;
	private String nombre;
	private String descripcion;
	private int edadRequerida;
	private int cantidadDisponible;
	private String codLote;
	private String distribuidor;
	
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
	public int getEdadRequerida() {
		return edadRequerida;
	}
	public void setEdadRequerida(int edadRequerida) {
		this.edadRequerida = edadRequerida;
	}
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	public String getCodLote() {
		return codLote;
	}
	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}
	public String getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
}
