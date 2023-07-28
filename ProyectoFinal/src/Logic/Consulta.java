package Logic;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8403556167981137462L;
	String codigo;
	Date fecha;
	Persona miPersona;
	Doctor miDoctor;
	String observaciones;
	//double precio;
	
	public Consulta(Date fecha, Persona miPersona, Doctor miDoctor, String observaciones, String codigo) {
		super();
		this.fecha = fecha;
		this.miPersona = miPersona;
		this.miDoctor = miDoctor;
		this.observaciones = observaciones;
		this.codigo = codigo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Doctor getMiDoctor() {
		return miDoctor;
	}
	public void setMiDoctor(Doctor miDoctor) {
		this.miDoctor = miDoctor;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Persona getMiPersona() {
		return miPersona;
	}
	public void setMiPersona(Persona miPersona) {
		this.miPersona = miPersona;
	}
	/*public void generarFactura(Persona miPersona, Doctor miDoctor, Date fecha) {
		
	}
	*/
	

}