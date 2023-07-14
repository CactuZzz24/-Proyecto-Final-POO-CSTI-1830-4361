package Logic;

import java.util.ArrayList;

public class Clinica {

	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Usuario> misUsuarios;
	private static Clinica clinica;
	private static int codConsulta;
	private static int codVacuna;
	private static int codEfermedad;
	
	
	public Clinica() {
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.misConsultas = new ArrayList<Consulta>();
		this.misPersonas = new ArrayList<Persona>();
		this.misUsuarios = new ArrayList<Usuario>();
	}
	
	public static Clinica getInstance() {
		if(clinica == null)
			clinica = new Clinica();
		return clinica;
	}
	
	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}
	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}
	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}
	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}
	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}
	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public Usuario buscarUsiarioByCedula(String cedula) {
		for(Usuario usuario : misUsuarios) {
			if(usuario.getCedula().equalsIgnoreCase(cedula))
				return usuario;
		}
		return null;
	}
	
	public void insertarConsulta(Consulta consulta){
		misConsultas.add(consulta);
		
	}

}
