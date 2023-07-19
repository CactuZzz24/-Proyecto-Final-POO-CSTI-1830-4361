package Logic;

import java.util.ArrayList;
import java.util.Iterator;

public class Clinica {

	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Usuario> misUsuarios;
	private static Clinica clinica;
	private static int codConsulta = 0;
	private static int codVacuna = 0;
	private static int codEfermedad = 0;
	
	
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

	public void agregarEnfermedad(Enfermedad enfermedad) {
		// TODO Auto-generated method stub
		misEnfermedades.add(enfermedad);
		
	}

	public void modificarEnfermedad(Enfermedad miEnfermedad) {

		int index = bucarIndexEnfermedadByCode(miEnfermedad.getCodigo());
		misEnfermedades.set(index, miEnfermedad);
		
	}

	private int bucarIndexEnfermedadByCode(String codigo) {
		int aux = -1;
		boolean encontrado = false;
		int i =0;
		while (i < misEnfermedades.size() && !encontrado) {
			if(misEnfermedades.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado = true;
				aux = i;
			}
			i++;
			
		}
		return aux;
	}
	public Paciente buscarPacienteByCedula(String cedula) {
		for(Persona persona : misPersonas) {
			if(persona instanceof Paciente && persona.getCedula().equalsIgnoreCase(cedula))
				return (Paciente) persona;
		}
		return null;
	}

	public Doctor buscarMedicoByCedula(String cedula) {
		for(Persona persona : misPersonas) {
			if(persona instanceof Doctor & persona.getCedula().equalsIgnoreCase(cedula));
				return (Doctor) persona;
		}
		return null;
	}

	public boolean seRepiteCedula(String cedula) {
		for(Usuario usuario : misUsuarios) {
			if(usuario.getCedula().equalsIgnoreCase(cedula))
				return true;
		}
		return false;
	}

	public Persona buscarPersonaByCedula(String cedula) {
		
		for (Persona persona : misPersonas) {
			if(persona.getCedula().equals(cedula))
			{
				return persona;
			}
			
		}
		return null;
	}

	public void insertarPersona(Persona persona) {
		misPersonas.add(persona);
		
	}

	public void uptadePersona(Persona miPersona) {
		int index = buscarIndexUsuarioByCedula(miPersona.getCedula());
		if(index != -1) {
			misPersonas.set(index, miPersona);
		}
		
	}

	private int buscarIndexUsuarioByCedula(String cedula) {
		int aux = -1;
		boolean encontrado = false;
		int i = 0;
		while(i < misPersonas.size() && !encontrado) {
			if(misPersonas.get(i).getCedula().equalsIgnoreCase(cedula)) {
				encontrado = true;
				aux = i;
			}
			i++;
		}
		return aux;
	}

	public void eliminarPersona(Doctor selectedDoctor) {
		misPersonas.remove(selectedDoctor);
		
	}

	public Enfermedad buscarEnfermedadByCode(String codigo) {
		
		for (Enfermedad enfermedad : misEnfermedades) {
			if(enfermedad.getCodigo().equals(codigo))
			{
				return enfermedad;
			}
			
		}
		return null;
	}

	public void eliminarEnfermedad(Enfermedad selectedEnfermedad) {
		misEnfermedades.remove(selectedEnfermedad);
		
	}

	public Consulta buscarConsultaByCode(String codigo) {
		

		for (Consulta consulta : misConsultas) {
			if(consulta.getCodigo().equals(codigo))
			{
				return consulta;
			}
			
		}
		return 	null;
				}

	public void eliminarConsulta(Consulta selectedConsulta) {
		misConsultas.remove(selectedConsulta);
		
	}
	
		
	

	

}

