/**
 * 
 */
package logico;

/**
 * @author edwin
 *
 */
/*
 * - String: especialidad;
- boolean: cogeSeguro (yes/no);

 */
public class Medico extends Persona {
	private String especialidad;
	private boolean cogeSeguro;
	/**
	 * @param cedula
	 * @param nombre
	 * @param edad
	 * @param telefono
	 * @param genero
	 * @param direccion
	 * @param especialidad
	 * @param cogeSeguro
	 */
	public Medico(String cedula, String nombre, int edad, String telefono, char genero, String direccion,
			String especialidad, boolean cogeSeguro) {
		super(cedula, nombre, edad, telefono, genero, direccion);
		this.especialidad = especialidad;
		this.cogeSeguro = cogeSeguro;
	}
	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}
	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	/**
	 * @return the cogeSeguro
	 */
	public boolean isCogeSeguro() {
		return cogeSeguro;
	}
	/**
	 * @param cogeSeguro the cogeSeguro to set
	 */
	public void setCogeSeguro(boolean cogeSeguro) {
		this.cogeSeguro = cogeSeguro;
	}
	

}
