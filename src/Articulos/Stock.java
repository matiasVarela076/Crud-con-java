package Articulos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import Util.Archivador;

public class Stock implements Serializable{
	
	private static final long serialVersionUID = 2338064953948053270L;
	private static Stock singleton;
	private static final String RUTASTOCK = "Stock.txt";
	private int cantidadArticulosEnStock = 0;
	
	public static Stock getInstance() {
		if (singleton == null) {
			singleton = new Stock();
		}
		return singleton;
	}
	
	private Stock () {	
	}
	/**Guarda el archivo*/
	public static void guardar() {
		Archivador archive = new Archivador();
		archive.createFile(RUTASTOCK);
		archive.save(singleton, RUTASTOCK);	
	}
	/**Carga el archivo*/
	public static void cargar() {
		Archivador archive = new Archivador();	
		if(archive.existe(RUTASTOCK)) {
			singleton = (Stock) archive.load(RUTASTOCK);
		}
	}
	
	
	private HashMap<String,Articulos> listaDeArticulos = new HashMap<String,Articulos>();
	
	public HashMap<String, Articulos> getListaDeArticulos() {
		return listaDeArticulos;
	}
	
	/**
	 * Crea un nuevo articulo con el constructor de Articulos, luego lo inserta en el diccionario.
	 * 
	 * @param codigo el codigo del articulo, el mismo se va a usar para identificarlo.
	 * @param nombre nombre o descripcion del articulo.
	 * @param precio el precio unitario del articulo.
	 * @param cantidadDeArticulos cantidad de los articulos en stock.
	 * @return true si se crea el articulo.
	 * 
	 * */
	public boolean add(String codigo, String nombre, double precio, int cantidadDeArticulos) {
		Articulos art = new Articulos(codigo, nombre, precio, cantidadDeArticulos);
		listaDeArticulos.put(codigo, art);
		cantidadArticulosEnStock++;
		return true;
	}
	/**
	 *Ver la lista no ordenada de todos los articulos en stock.
	 *los mismos se van a mostrar aunque la cantidad de los articulos sea cero.
	 *
	 * */
	public void verArticulos() {	
		Set<String> codigos = listaDeArticulos.keySet(); 
		for (String codigo : codigos) {
			
			System.out.print("Codigo del articulo: " + codigo + " || ");
			System.out.println(listaDeArticulos.get(codigo));
		}
	}
	
	/*
	 * Ver la cantidad de unidades del articulo del cual se ingrese el codigo identificador
	 * 
	 * @param codigo se ingresa el codigo del articulo para ver la cantidad de u.
	 * @return devuelve un numero
	 * */
	
	public int verCantidad(String codigo) {
		return listaDeArticulos.get(codigo).getCantidadDeArticulos();
	}
	
	/**
	 * metodo para eliminar del stock el articulo cuyo codigo identificador sea ingresado.
	 * 
	 * @param codigo el codigo identificador del articulo
	 * */
	public void empleadosOpcionTres(String codigo) {
		listaDeArticulos.remove(codigo);
		cantidadArticulosEnStock--;
	}

	public int getCantidadArticulosEnStock() {
		return cantidadArticulosEnStock;
	}
	
	
}