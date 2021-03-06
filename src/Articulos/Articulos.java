package Articulos;

import java.io.Serializable;
 
public class Articulos implements Serializable {
	

	private static final long serialVersionUID = 3573292890385440854L;

	/*
	 * INFORMACION DEL ARTICULO
	 * */
	@Override
	public String toString() {
		return " Nombre: " + nombreDescriptivo
				+ " || Precio: " + precio + " || Stock: " + cantidadDeArticulos;
	}
	
	/*
	 * ATRIBUTOS
	 * */
	private String codigoDeArticulo;
	private String nombreDescriptivo;
	private double precio;
	private int cantidadDeArticulos;
	
	/*
	 * CONSTRUCTOR
	 * */
	public Articulos(String codigoDeArticulo, String nombreDescriptivo, double precio, int cantidadDeArticulos) {
		this.codigoDeArticulo = codigoDeArticulo;
		this.nombreDescriptivo = nombreDescriptivo;
		this.precio = precio;
		this.cantidadDeArticulos = cantidadDeArticulos;
	}
	
	/* 
	 * GETTERS Y SETTERS
	 * */
	
	public int getCantidadDeArticulos() {
		return cantidadDeArticulos;
	}

	public void setCantidadDeArticulos(int cantidadDeArticulos) {
		this.cantidadDeArticulos = cantidadDeArticulos;
	}

	public String getCodigoDeArticulo() {
		return codigoDeArticulo;
	}
	public void setCodigoDeArticulo(String codigoDeArticulo) {
		this.codigoDeArticulo = codigoDeArticulo;
	}
	public String getNombreDescriptivo() {
		return nombreDescriptivo;
	}
	public void setNombreDescriptivo(String nombreDescriptivo) {
		this.nombreDescriptivo = nombreDescriptivo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}	
}
