package Usuario;
import java.io.Serializable;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 7658952329863950488L;
	@Override
	public String toString() {
		return "nombre: " + nombre + ", contraseña =" + contrasenia + ", tipoDeUsuario =" + tipoDeUsuario;
	}
	private String nombre;
	private String contrasenia;
	private String tipoDeUsuario;
	private double cantidadDinero;
	
	public double getCantidadDinero() {
		return cantidadDinero;
	}
	public void setCantidadDinero(double cantidadDinero) {
		this.cantidadDinero = cantidadDinero;
	}
	public Usuario(String nombre, String contrasenia, String tipoDeUsuario, double cantidadDinero) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.tipoDeUsuario = tipoDeUsuario;
		this.cantidadDinero = 0;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	public void setTipoDeUsuario(String tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}
}
