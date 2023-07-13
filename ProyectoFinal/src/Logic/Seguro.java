/**
 * 
 */
package Logic;

/**
 * @author edwin
 *
 */
public class Seguro {
	private String especialidad;
	private String numAfil;
	private String aseguradora;
	private String telefonoAseguradora;
	private String nss;
	/**
	 * @param especialidad
	 * @param numAfil
	 * @param aseguradora
	 * @param telefonoAseguradora
	 * @param nss
	 */
	public Seguro(String especialidad, String numAfil, String aseguradora, String telefonoAseguradora, String nss) {
		super();
		this.especialidad = especialidad;
		this.numAfil = numAfil;
		this.aseguradora = aseguradora;
		this.telefonoAseguradora = telefonoAseguradora;
		this.nss = nss;
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
	 * @return the numAfil
	 */
	public String getNumAfil() {
		return numAfil;
	}
	/**
	 * @param numAfil the numAfil to set
	 */
	public void setNumAfil(String numAfil) {
		this.numAfil = numAfil;
	}
	/**
	 * @return the aseguradora
	 */
	public String getAseguradora() {
		return aseguradora;
	}
	/**
	 * @param aseguradora the aseguradora to set
	 */
	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}
	/**
	 * @return the telefonoAseguradora
	 */
	public String getTelefonoAseguradora() {
		return telefonoAseguradora;
	}
	/**
	 * @param telefonoAseguradora the telefonoAseguradora to set
	 */
	public void setTelefonoAseguradora(String telefonoAseguradora) {
		this.telefonoAseguradora = telefonoAseguradora;
	}
	/**
	 * @return the nss
	 */
	public String getNss() {
		return nss;
	}
	/**
	 * @param nss the nss to set
	 */
	public void setNss(String nss) {
		this.nss = nss;
	}
	
}
