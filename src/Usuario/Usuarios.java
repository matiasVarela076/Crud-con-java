package Usuario;

import java.io.Serializable;
import java.util.HashMap;
import Util.*;

public class Usuarios implements Serializable{
	private static final long serialVersionUID = -2296659306937953291L;
	public static final String RUTA = "Usuarios.txt";
	private static Usuarios singleton;
	
	public static Usuarios getInstance() {
		if (singleton == null) {
			singleton = new Usuarios();
		}
		return singleton;
	}
	
	private Usuarios() {	
	}
	/**Guarda el archivo*/
	public static void guardar() {
		Archivador archive = new Archivador();	
		archive.createFile(RUTA);
		archive.save(singleton, RUTA);
	}
	/**Carga el archivo*/
	public static void cargar() {
		Archivador archive = new Archivador();	
		if (archive.existe(RUTA)) {
			singleton = (Usuarios) archive.load(RUTA);
		}
	}
	
	private HashMap<String, Usuario> diccionarioUsuarios = new HashMap<String,Usuario>();
	
	/**
	 * Agrega un nuevo usuario al HashMap de usuarios.
	 * @param nombre nombre del usuario
	 * @param contraseña contraseña del usuario
	 * @param tipoDeUsuario si es empleado o cliente
	 * @return true si se crea un nuevo usuario, false si ya existe.
	 * */
	public boolean agregarUsuario(String nombre, String contrasenia, String tipoDeUsuario, double cantidadDinero) {
		if(this.usuarioExistente(nombre)) {
			return false;
		}
		Usuario usuario = new Usuario (nombre, contrasenia, tipoDeUsuario, cantidadDinero);
		diccionarioUsuarios.put(nombre.toLowerCase(), usuario);
		return true;
	}
	/**
	 * Compara los datos de inicio de sesion ingresados con los ya existentes en el archivo de usuarios
	 * @param nombre el nombre de usuario
	 * @param contraseña la contraseña correspondiente el nombre ingresado
	 * @return false si los datos son incorrectos, true si los datos coinciden.
	 * */
	public boolean inicioSesion(String nombre, String contrasenia) {
		if(this.usuarioExistente(nombre.toLowerCase())) {
			if (this.getUsuario(nombre.toLowerCase()).getContrasenia().equals(contrasenia)) {
				return true;
			} else return false;
		} else {
			return false;
		}	
	}
	/**
	 * metodo para devolver la informacion completa del usuario
	 * @param nombre el nombre del usuario
	 * @return devuelve el usuario cuyo nombre se ingreso
	 * */
	public Usuario getUsuario(String nombre) {
		return this.diccionarioUsuarios.get(nombre);
	}
	/**
	 * metodo para verificar si el usuario ya existe
	 * @param nombre se ingresa el nombre del usuario para la verificacion
	 * @return true si el usuario ya esta en existencia
	 * */
	public boolean usuarioExistente(String nombre) {
		return this.diccionarioUsuarios.containsKey(nombre.toLowerCase());
	}
	
	/**
	 * Transferencia de dinero de un usuario a otro
	 * 
	 * @param cantidadTransferencia Cantidad a transferir
	 * @param nombre El nombre del destinatario
	 * */
	public void transferencia(double cantidadTransferencia, String nombre) {
		
		double cantidadDinero = Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).getCantidadDinero();
		Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).setCantidadDinero(cantidadDinero-cantidadTransferencia);
		
		double cantidadDineroDestino = Usuarios.getInstance().getDiccionarioUsuarios().get(nombre).getCantidadDinero();
		Usuarios.getInstance().getDiccionarioUsuarios().get(nombre).setCantidadDinero(cantidadDineroDestino + cantidadTransferencia);
		
		Usuarios.guardar();
		
	}
	
	/*getter y setter*/
	public HashMap<String, Usuario> getDiccionarioUsuarios() {
		return diccionarioUsuarios;
	}
	public void setDiccionarioUsuarios(HashMap<String, Usuario> diccionarioUsuarios) {
		this.diccionarioUsuarios = diccionarioUsuarios;
	}
}
