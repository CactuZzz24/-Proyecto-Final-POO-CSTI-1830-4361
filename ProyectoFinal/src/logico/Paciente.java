/**
 * 
 */
package logico;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author edwin
 * 
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
	 * @param tipoSangre
	 * @param fechaNacim
	 */
	private Date fechaNacim;
	private String tipoSangre;
	private ArrayList<Seguro> misSeguros;
	boolean status;
	//ArrayList<Consulta> misConsultas;
	//ArrayList<Enfermedad> misEnfermedades;
	//ArrayList<Vacunas> hojaVacunacion;
	
	
	
	public Paciente(String cedula, String nombre, int edad, String telefono, char genero, String direccion,
			String tipoSangre, Date fechaNacim) {
		// TODO Auto-generated constructor stub

		super(cedula, nombre, edad, telefono, genero, direccion);
		this.tipoSangre = tipoSangre;
		this.fechaNacim = fechaNacim;
		misSeguros = new ArrayList<>();
	}

	/**
	 * @return the tipoSangre
	 */
	public String getTipoSangre() {
		return tipoSangre;
	}
	/**
	 * @param tipoSangre the tipoSangre to set
	 */
	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	/**
	 * @return the fechaNacim
	 */
	public Date getFechaNacim() {
		return fechaNacim;
	}
	/**
	 * @param fechaNacim the fechaNacim to set
	 */
	public void setFechaNacim(Date fechaNacim) {
		this.fechaNacim = fechaNacim;
	}
	
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the misSeguros
	 */
	public ArrayList<Seguro> getMisSeguros() {
		return misSeguros;
	}
	
	public void addSeguro(Seguro miSeguro) {
		misSeguros.add(miSeguro);
	}
		
	

}
