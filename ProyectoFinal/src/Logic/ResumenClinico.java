package Logic;

import java.util.ArrayList;

public class ResumenClinico {
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> hojaVacunacion;
	private ArrayList<String> notas;
	
	public ResumenClinico(ArrayList<Enfermedad> misEnfermedades, ArrayList<Vacuna> hojaVacunacion,
			ArrayList<String> notas) {
		super();
		this.misEnfermedades = misEnfermedades;
		this.hojaVacunacion = hojaVacunacion;
		this.notas = notas;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}
	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}
	public ArrayList<Vacuna> getHojaVacunacion() {
		return hojaVacunacion;
	}
	public void setHojaVacunacion(ArrayList<Vacuna> hojaVacunacion) {
		this.hojaVacunacion = hojaVacunacion;
	}
	public ArrayList<String> getNotas() {
		return notas;
	}
	public void setNotas(ArrayList<String> notas) {
		this.notas = notas;
	}
	
}
