package Logic;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable{
	private static final long serialVersionUID = -6563299755341571991L;
	private String secretaria;
	private String codigo;
	private Date fecha;
	private Persona miPersona;
	private Doctor miDoctor;
	
	public Cita(String secretaria, String codigo, Date fecha, Persona miPersona, Doctor miDoctor) {
		super();
		this.secretaria = secretaria;
		this.codigo = codigo;
		this.fecha = fecha;
		this.miPersona = miPersona;
		this.miDoctor = miDoctor;
	}
	public String getSecretaria() {
		return secretaria;
	}
	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
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
	public Persona getMiPersona() {
		return miPersona;
	}
	public void setMiPersona(Persona miPersona) {
		this.miPersona = miPersona;
	}
	public Doctor getMiDoctor() {
		return miDoctor;
	}
	public void setMiDoctor(Doctor miDoctor) {
		this.miDoctor = miDoctor;
	}
	
	


}