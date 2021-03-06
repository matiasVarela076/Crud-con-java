package Articulos;

import java.util.HashMap;
import java.util.Set;

public class Carrito {
	
	private static Carrito singleton;
	private String codigo;
	private int cantidadArticulosEnCarrito = 0;
//	private int cantidadArticulosAComprar = 0;


	public static Carrito getInstance() {
		if (singleton == null) {
			singleton = new Carrito();
		}
		return singleton;
	}
	
	private Carrito () {	
	}
	
	private HashMap<String, Articulos> carritoDeCompra = new HashMap<String, Articulos>();
	
	private HashMap<Articulos, Integer> cantidadCarrito = new HashMap<Articulos, Integer>();
	
	/**
	 * agrega un articulo al carrito de compras, aumenta la cantidad de articulos en el carrito y suma el precio total del articulo, (contemplando el precio * la cantidad) al total del carrito.
	 * @param codigo el codigo del articulo que se va a agregar al carrito
	 * @param stock utiliza la lista del stock para seleccionar de ahi el articulo
	 * @param cantidad la cantidad del mismo articulo que se va a agregar al carrito
	 * @return true cuando termine de agregar el articulo
	 * */
	public boolean agregarArticulo(String codigo, int cantidad) {
		
		if (cantidad <= Stock.getInstance().getListaDeArticulos().get(codigo).getCantidadDeArticulos()) {
			
			if (carritoDeCompra.containsKey(codigo)) {
				cantidadCarrito.put(carritoDeCompra.get(codigo), cantidadCarrito.get(carritoDeCompra.get(codigo)) + cantidad);
			} else {
				carritoDeCompra.put(codigo, Stock.getInstance().getListaDeArticulos().get(codigo));
				cantidadCarrito.put(carritoDeCompra.get(codigo), cantidad);
			}
		}
		
		cantidadArticulosEnCarrito ++;
		return true;
	}
	
	
	public void removerArticulo(String codigo) {
		carritoDeCompra.remove(codigo);
		cantidadArticulosEnCarrito--;
	}
	
	
	/**
	 * metodo que devuelve el precio total de todos los articulos del carrito.
	 * */
	public double totalCarrito() {
		double totalCarrito = 0;
		for (String codigoArticulo : codigos) {
			totalCarrito += Stock.getInstance().getListaDeArticulos().get(codigoArticulo).getPrecio() * cantidadCarrito.get(carritoDeCompra.get(codigoArticulo));
		}
		return totalCarrito;
	}
	 
	 Set<String> codigos = carritoDeCompra.keySet();//set de articulos del carrito
	/**
	  * lista de articulos que estan en el carrito de compras
	  * muestra codigo, nombre, precio, cantidad a comprar y total del articulo. Si está vacio muestra el mensaje "Carrito vacio"
	  * */
	public void verArticulosCarrito() {
		if (Carrito.getInstance().getCantidadArticulosEnCarrito() > 0) {
			for (String codigo : codigos) {
			System.out.println( "\t***Lista de artículos: ***\n Codigo del articulo: " + codigo + " (" + 
									carritoDeCompra.get(codigo).getNombreDescriptivo() + ") || "
					+ "Precio: " + carritoDeCompra.get(codigo).getPrecio() + " || Cantidad: " +  cantidadCarrito.get(carritoDeCompra.get(codigo))
					+ " || Total del articulo: " + String.format("%.2f", cantidadCarrito.get(carritoDeCompra.get(codigo)) * carritoDeCompra.get(codigo).getPrecio()));
			}
		}  else System.err.println("Carrito vacio.");
	}
	
	/**
	 * Confirmar compra: se encarga de actualizar el stock una vez que el usuario decide confirmar la compra. Limpia el carrito y guarda el archivo Stock.txt
	 * */
	public void confirmarCompra() {
		
		for (String codigo : codigos) {
			int cantidadAReducir = cantidadCarrito.get(carritoDeCompra.get(codigo));
			int cantidadActual = Stock.getInstance().getListaDeArticulos().get(codigo).getCantidadDeArticulos();
			int cantidadNueva = cantidadActual - cantidadAReducir;
			Stock.getInstance().getListaDeArticulos().get(codigo).setCantidadDeArticulos(cantidadNueva);
		}
		carritoDeCompra.clear();
		Carrito.getInstance().setCantidadArticulosEnCarrito(0);
		Stock.guardar();
	}
	
	
	
	/**
	 * getters y setters
	 * */
		
//	public int getCantidadArticulosAComprar() {
//		return cantidadArticulosAComprar;
//	}
//	public void setCantidadArticulosAComprar(int cantidadArticulosAComprar) {
//		this.cantidadArticulosAComprar = cantidadArticulosAComprar;
//	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getCantidad() {
		return cantidadArticulosEnCarrito;
	}
	public void setCantidad(int cantidad) {
		this.cantidadArticulosEnCarrito = cantidad;
	}
	public HashMap<String, Articulos> getCarritoDeCompra() {
		return carritoDeCompra;
	}
	public void setCarritoDeCompra(HashMap<String, Articulos> carritoDeCompra) {
		this.carritoDeCompra = carritoDeCompra;
	}
	public int getCantidadArticulosEnCarrito() {
		return cantidadArticulosEnCarrito;
	}
	public void setCantidadArticulosEnCarrito(int cantidadArticulosEnCarrito) {
		this.cantidadArticulosEnCarrito = cantidadArticulosEnCarrito;
	}
}