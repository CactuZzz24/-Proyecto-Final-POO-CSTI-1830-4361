/**
 * 
 */
package logico;

import java.util.Date;

/**
 * @author edwin
 *
 */
public class Paciente extends Persona {

	/**
	 * @param cedula
	 * @param nombre
	 * @param edad
	 * @param telefono
	 * @param genero
	 * @param direccion
	 */
	private String tipoSangre;
	private Date fechaNacim;
	private String cedula;
	public Paciente(String cedula, String nombre, int edad, String telefono, char genero, String direccion) {
		super(cedula, nombre, edad, telefono, genero, direccion);
		// TODO Auto-generated constructor stub
	}

}
