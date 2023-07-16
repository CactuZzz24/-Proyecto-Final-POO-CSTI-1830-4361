package Logic;

import java.util.Date;

public class Consulta {
	Date fecha;
	Persona miPersona;
	Doctor miDoctor;
	String observaciones;
	//double precio;
	
	public Consulta(Date fecha, Persona miPersona, Doctor miDoctor, String observaciones) {
		super();
		this.fecha = fecha;
		this.miPersona = miPersona;
		this.miDoctor = miDoctor;
		this.observaciones = observaciones;
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