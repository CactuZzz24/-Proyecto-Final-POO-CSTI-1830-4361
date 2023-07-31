package Logic;

import java.util.ArrayList;
import java.util.Date;

public class Paciente extends Persona {
	private static final long serialVersionUID = 1L;
	private ResumenClinico resumenClinico;
	private ArrayList<Consulta> misConsultas;
	private String TipoSangre;
	private boolean vigilancia;
	
	public Paciente(String cedula, String nombre, Date fchNacim, String telefono, String direccion, char genero,
			ResumenClinico resumenClinico, ArrayList<Consulta> misConsultas, String tipoSangre, boolean vigilancia) {
		super(cedula, nombre, fchNacim, telefono, direccion, genero);
		this.resumenClinico = resumenClinico;
		this.misConsultas = misConsultas;
		TipoSangre = tipoSangre;
		this.vigilancia = vigilancia;
	}
	
	public ResumenClinico getResumenClinico() {
		return resumenClinico;
	}
	public void setResumenClinico(ResumenClinico resumenClinico) {
		this.resumenClinico = resumenClinico;
	}
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	public String getTipoSangre() {
		return TipoSangre;
	}
	public void setTipoSangre(String tipoSangre) {
		TipoSangre = tipoSangre;
	}
	public boolean isVigilancia() {
		return vigilancia;
	}
	public void setvigilancia(boolean status) {
		this.vigilancia = status;
	}
	
	
}
