package logico;
/*- String: Cedula;
- String: nombre
- int: edad
- String: teléfono
- char: genero
- String: dirección
*/
public abstract class Persona {
	private String cedula;
	private String nombre;
	private int edad;
	private String telefono;
	private char genero;
	private String direccion;
	public Persona(String cedula, String nombre, int edad, String telefono, char genero, String direccion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.genero = genero;
		this.direccion = direccion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	

}
