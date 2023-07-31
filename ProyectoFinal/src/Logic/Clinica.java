package Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Clinica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3422390151419653470L;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Usuario> misUsuarios;
	private static Clinica clinica;
	private static int codConsulta = 0;
	private static int codVacuna = 0;
	private static int codEfermedad = 0;
	private int contador = 0;
	
	
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
	
	public static Clinica getClinica() {
		return clinica;
	}

	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
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
	
	public void insertarConsulta(Consulta consulta){
		misConsultas.add(consulta);
		
	}

	public void agregarEnfermedad(Enfermedad enfermedad) {
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
			if(persona instanceof Doctor && persona.getCedula().equalsIgnoreCase(cedula)) {
				
			
				return (Doctor) persona;
			}
		}
		return null;
	}

	public boolean existeCedulaRol(String cedula, boolean esPaciente, boolean esAdmin) {
		for(Persona persona : misPersonas) {
			if(((esPaciente && persona instanceof Paciente) || (!esPaciente || !esAdmin && persona instanceof Doctor) || (!(persona instanceof Paciente) && !(persona instanceof Doctor)))
					&& persona.getCedula().equalsIgnoreCase(cedula)){
				return true;
			}
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

	public void eliminarPersona(Persona selectedPersona) {
		misPersonas.remove(selectedPersona);
	}

	public Enfermedad buscarEnfermedadByCode(String codigo) {
		Enfermedad aux = null;
		boolean encontrado = false;
		int i = 0;
		while (i < misEnfermedades.size() && !encontrado) {
			if(misEnfermedades.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado = true;
				aux = misEnfermedades.get(i);
			}
			i++;
		}
		return aux;
	}

	public void eliminarEnfermedad(Enfermedad selectedEnfermedad) {
		misEnfermedades.remove(selectedEnfermedad);
		
	}

	public Consulta buscarConsultaByCode(String codigo) {
		for (Consulta consulta : misConsultas) {
			if(consulta.getCodigo().equals(codigo))
				return consulta;			
		}
		return 	null;
	}

	public void eliminarConsulta(Consulta selectedConsulta) {
		misConsultas.remove(selectedConsulta);
		
	}
	

	private int cantidadPaciententesGenero(char genero) {
		contador = 0;
		for (Persona persona : misPersonas) {
			if(persona instanceof Paciente && persona.getGenero() == genero) {
				contador++;
			}
		}
		return contador;
	}
	
	private int cantidadPacientesAtendidos(){
		contador = 0;
		for (Persona persona : misPersonas) {
			if(persona instanceof Paciente && ((Paciente) persona).isVigilancia() == false) {
				contador++;
			}
		}
		return contador;
	}

	public boolean existeVacuna(String nombre) {
		for(Vacuna vacuna : misVacunas) {
			if(vacuna.getNombre().equalsIgnoreCase(nombre))
				return true;
		}
		return false;
	}

	public void insertarVacuna(Vacuna vacuna) {
		misVacunas.add(vacuna);
		codVacuna++;
	}

	public void eliminarVacuna(Vacuna selectedVacuna) {
		misVacunas.remove(selectedVacuna);
		
	}

	public Vacuna buscarVacunaByCode(String codigo) {
		
		
		Vacuna aux = null;
		boolean encontrado = false;
		int i = 0;
		while (i < misVacunas.size() && !encontrado) {
			if(misVacunas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado = true;
				aux = misVacunas.get(i);
				
			}
			i++;
			
		}
		return aux;	}

	public void uptadeVacuna(Vacuna miVacuna) {
		int index = buscarIndexVacunaByCode(miVacuna.getCodigo());
		if(index != -1) {
			misVacunas.set(index, miVacuna);
		}
		
	}

	private int buscarIndexVacunaByCode(String codigo) {
		int aux = -1;
		boolean encontrado = false;
		int i = 0;
		while (i < misVacunas.size() && !encontrado) {
			if (misVacunas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado = true;
				aux = i;
			}
			i++;
			
		}
		return aux;
	}
	public void agregarUsuario(Usuario aux) {
		misUsuarios.add(aux);
	}

	public boolean existeNombreUsuario(String nombre) {
		for(Usuario usuario : misUsuarios) {
			if(usuario.getNombre().equalsIgnoreCase(nombre))
				return true;
		}
		return false;
	}

	public Usuario buscarUsuarioByPersona(Persona persona) {
		for(Usuario usuario : misUsuarios) {
			if(usuario.getPersona().equals(persona))
				return usuario;
		}
		return null;
	}

	public Usuario buscarUsiarioByNombreAndClave(String nombre, String clave) {
		for(Usuario usuario : misUsuarios) {
			if(usuario.getNombre().equals(nombre) && usuario.getClave().equals(clave))
				return usuario;
		}
		return null;
	}

	public boolean existeCedulaPersona(String cedula) {
		for(Persona persona : misPersonas) {
			if(persona.getCedula().equals(cedula))
				return true;
		}
		return false;
	}

	public void modificarConsulta(Consulta miConsulta) {
		int index = buscarIndexConsultaByCode(miConsulta.getCodigo());
		misConsultas.set(index, miConsulta);		
	}

	private int buscarIndexConsultaByCode(String codigo) {
		int aux = -1;
		boolean encontrado = false;
		int i = 0;
		while (i < misConsultas.size() && !encontrado) {
			if (misConsultas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				encontrado = true;
				aux = i;
			}
			i++;
			
		}
		return aux;
	}

	public void eliminarUsuario(Usuario usuario) {
		misUsuarios.remove(usuario);
	}

	public Number calcCantPacientes() {
	    int cont = 0;
	    for (Usuario usuario: misUsuarios) {
	        if (usuario.getPersona() instanceof Paciente)
	            cont++;
	    }
	    return cont;
	}

	public Number calcCantDoctores() {
	    int cont = 0;
	    for (Usuario usuario: misUsuarios) {
	        if (usuario.getPersona() instanceof Doctor)
	            cont++;
	    }
	    return cont;
	}

	public Number calcCantAdmins() {
	    int cont = 0;
	    for (Usuario usuario: misUsuarios) {
	        if (!(usuario.getPersona() instanceof Paciente || usuario.getPersona() instanceof Doctor))
	            cont++;
	    }
	    return cont;
	}

	public void actualizaRegistroPaciente(Paciente paciente, Enfermedad selectedEnfermedad, Vacuna selectedVacuna, String observaciones, Consulta miConsulta) {
	    ResumenClinico resumenClinico = paciente.getResumenClinico();

	    if (resumenClinico == null) {
	        resumenClinico = new ResumenClinico(new ArrayList<Enfermedad>(), new ArrayList<Vacuna>(), new ArrayList<String>());
	    }

	    if (selectedEnfermedad != null) {
	        ArrayList<Enfermedad> enfermedadesPaciente = resumenClinico.getMisEnfermedades();
	        enfermedadesPaciente.add(selectedEnfermedad);
	    }

	    if (selectedVacuna != null) {
	        ArrayList<Vacuna> hojaVacunacion = resumenClinico.getHojaVacunacion();
	        hojaVacunacion.add(selectedVacuna);
	    }

	    if (observaciones != null) {
	        ArrayList<String> notas = resumenClinico.getNotas();
	        notas.add(observaciones);
	    }

	    if (miConsulta != null) {
	        ArrayList<Consulta> consultasPaciente = paciente.getMisConsultas();
	        consultasPaciente.add(miConsulta);
	    }

	    paciente.setResumenClinico(resumenClinico);
	    uptadePersona(paciente);
	}




	public String generarResumenClinico(Paciente paciente) {
		StringBuilder resumenClinico = new StringBuilder();
	    for (int i = 0; i < paciente.getResumenClinico().getNotas().size(); i++) {
	        resumenClinico.append("Nota #" + String.valueOf(i) + "\n" + paciente.getResumenClinico().getNotas().get(i) + "\n\n");
	    }
	    return resumenClinico.toString();
	}
	
	
}

