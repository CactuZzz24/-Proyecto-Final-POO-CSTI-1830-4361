package Logic;

import java.util.Date;

public class Doctor extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidad;
	
	public Doctor(String cedula, String nombre, Date fchNacim, String telefono, String direccion, char genero,
			String especialidad) {
		super(cedula, nombre, fchNacim, telefono, direccion, genero);
		this.especialidad = especialidad;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
}
