package Util;

import java.util.InputMismatchException;
import java.util.Scanner;

import Articulos.Carrito;
import Articulos.Stock;
import Usuario.*;

public class Menues {

	public static String username;
		
	public static void menuUsuarios() {
		System.out.println("\tMenu Usuarios");
		System.out.println("Elija una opcion: ");	
		System.out.println("(1) Registrarse.");
		System.out.println("(2) Iniciar sesión. ");
		System.out.println("(3) Finalizar. ");
	}
	/**
	 * metodo para crear un nuevo usuario, ingresa nombre, contraseña y tipo de usuario.
	 * si crea el usuario finaliza el bucle, si hay un error vuelve a iterar.
	 * @param sc ingresa el Scanner
	 * */
	
	public static void crearUsuario(Scanner sc) {
		while( true) {
			System.out.println("\t***Registro de Usuarios***\n");
			System.out.print("Ingrese nombre de usuario: ");
			String nombre = sc.next();
			System.out.print("Ingrese contraseña: ");
			String contrasenia = sc.next();
			System.out.print("Ingrese si es empleado o cliente: ");
			String tipoDeUsuario = sc.next();
				if(tipoDeUsuario.toLowerCase().equals("empleado") || tipoDeUsuario.toLowerCase().equals("cliente")) {
					
					if(Usuarios.getInstance().agregarUsuario(nombre, contrasenia, tipoDeUsuario.toLowerCase(), 0)) {
						Usuarios.guardar();
			            System.out.println("\nRegistro exitoso.");
			            break;
					} else {
						System.out.println("Ya existe el usuario.");
						break;
					} 
		         }
		         else 
		             System.err.println("Opcion incorrecta, debe ingresar si es empleado o cliente");
					 break;
		} 
	}
	/**
	 * Al ingresar un nombre y una contraseña, los compara con los datos existentes en el .txt de usuarios.
	 * Si los datos coinciden el usuario puede iniciar sesión y comenzar a utilizar el programa, si los datos son incorrectos puede volver a intentarlo.
	 * @param sc ingresa el Scanner
	 * */
	/**
	 * @param sc
	 */
	public static boolean iniciarSesion(Scanner sc) {
		
		while(true) {
		   
		    System.out.println("\t***Inicio Sesion***");
			System.out.print("Ingrese nombre: ");
			String nombre = sc.next();
			System.out.print("Ingrese contraseña: ");
			String contrasenia = sc.next();

			
			if(Usuarios.getInstance().usuarioExistente(nombre)) {
				if (contrasenia.equals(Usuarios.getInstance().getUsuario(nombre).getContrasenia())) {
					System.out.println("Bienvenid@, (" + Usuarios.getInstance().getUsuario(nombre.toLowerCase()).getTipoDeUsuario() + ") "
							+ Usuarios.getInstance().getUsuario(nombre.toLowerCase()).getNombre() + "!");
						username = nombre.toLowerCase();
						return true;
				}
			}
			
			else System.out.println("Datos incorrectos, vuelva a intentarlo.");
			return false;
		}
	}
	
	public static void menuEmpleados() {
		System.out.println("\nMenú");
		System.out.println("(1) Ver articulos. ");
		System.out.println("(2) Agregar articulo. ");
		System.out.println("(3) Remover articulo. ");
		System.out.println("(4) Modificar stock. ");
		System.out.println("(5) Editar articulo. ");
		System.out.println("(6) Cerrar sesión. ");
	}
	
	public static void empleadosOpcionUno() {
		if(Stock.getInstance().getCantidadArticulosEnStock()>=1) {
			Stock.getInstance().verArticulos();
		} 
		else System.out.println("No hay articulos");
	}
	
	/**
	 * Opcion dos: agregar un articulo al stock.
	 * Se ingresa codigo, nombre, precio y cantidad.
	 * agrega el articulo y lo guarda automaticamente en el archivo Stock.txt.
	 * devuelve una confirmación
	 * 
	 * @param sc Ingresa un Scanner
	 * */
	public static void empleadosOpcionDos(Scanner sc) {
		System.out.print("Ingrese el codigo del articulo: ");
		String codigo = sc.next();
		System.out.print("Ingrese el nombre del articulo: ");
		String nombreDeArticulo = sc.next();
		
		while(true) try {
		System.out.print("Ingrese el precio del articulo: ");
		double precio = sc.nextDouble();
		
		if (precio > 0) {
			
			System.out.print("Ingrese la cantidad de stock: ");
			int cantidad = sc.nextInt();
			if(cantidad>=0) {
				Stock.getInstance().add(codigo, nombreDeArticulo, precio, cantidad);
				Stock.guardar();
				System.out.println("Articulo agregado."); break;
			}
			else if(cantidad<0) {
				System.err.println("Cantidad incorrecta.");
				}
			} else System.out.println("Valor incorrecto.");
		
	}catch(InputMismatchException e) {
			System.err.println("\n\nDio un " + e +"El precio del articulo debe ser un valor numerico, elija una opcion:  ");break;
	}	
}
	
	
	/**
	 * Eliminar un articulo: ingresa un codigo, se corrobora su existencia y se elimina, en caso de que el codigo ingresado sea incorrecto se envia un mensaje de error.
	 * @param sc Scanner
	 * */
	public static void empleadosOpcionTres(Scanner sc) {
		System.out.print("Ingrese el codigo del articulo a eliminar: ");
		String codigo = sc.next();
		if (Stock.getInstance().getListaDeArticulos().containsKey(codigo)) {
			Stock.getInstance().empleadosOpcionTres(codigo);
			Stock.guardar();
			System.out.println("Articulo eliminado exitosamente!");
		} else System.err.println("Código erróneo.");
	}
	
	/**
	 * Stock: ver cantidad, editar cantidad y volver al menu.
	 * @param sc Scanner
	 * */
	public static void empleadosOpcionCuatro(Scanner sc) {
		while(true) {
			System.out.println("(1) Ver cantidad.");
			System.out.println("(2) Editar cantidad.");
			System.out.println("(3) Volver al menú.");
			String opcionDos = sc.next();
			
			if(opcionDos.equals("1")) {
				System.out.print("Ingrese codigo de articulo: ");
				String codigo = sc.next();
				if (Stock.getInstance().getListaDeArticulos().containsKey(codigo)) {
				System.out.println("\nNombre: " + Stock.getInstance().getListaDeArticulos().get(codigo).getNombreDescriptivo()
						+ ", cantidad: " + Stock.getInstance().verCantidad(codigo));
				}
				else System.err.println("Código no valido");
			} 
			else if (opcionDos.equals("2")) {
                // opcion b editar la cantidad
                System.out.print("Ingrese codigo de articulo: ");
                String codigo = sc.next();
                if (Stock.getInstance().getListaDeArticulos().containsKey(codigo)) {
                		
                	
                	
                	try {
                		System.out.print("Ingrese la cantidad: ");
                        int cantidadNueva = sc.nextInt();
                		
                		 if(cantidadNueva>=0) {
                            Stock.getInstance().getListaDeArticulos().get(codigo).setCantidadDeArticulos(cantidadNueva);
                            Stock.guardar();
                            System.out.println("Articulo modificado exitosamente\n");
                        }
                        else if(cantidadNueva<0) {
                            System.out.println("Cantidad incorrecta");
                        }
               
                else {System.out.println("Código no válido.");
                
                }
                
                	}catch (InputMismatchException ime){
                		System.err.println("Error. Debe ingresar un valor numerico. \n");
                	}	
                	
                
                
                }
                	
                       
            
}
			else if(opcionDos.equals("3")) {
				
				break;
			}
			
			
		}
	}
	
	public static void empleadosOpcionCinco(Scanner sc) {
		
		System.out.print("Ingrese codigo del articulo a editar: ");
		String codigo = sc.next();
		if(Stock.getInstance().getListaDeArticulos().containsKey(codigo)) {
			System.out.println("Ingrese los nuevos datos: ");
			System.out.print("Nombre: ");
			String nombreNuevo = sc.next();
			System.out.print("Precio: ");
			double precioNuevo = sc.nextDouble();
			
			if(precioNuevo > 0) {
				Stock.getInstance().getListaDeArticulos().get(codigo).setNombreDescriptivo(nombreNuevo);
				Stock.getInstance().getListaDeArticulos().get(codigo).setPrecio(precioNuevo);
				
				Stock.guardar();
			} else System.out.println("Valor incorrecto.");
			
		} else System.out.println("Codigo incorrecto.");
		
		
	}
	
			
	public static void menuClientes() {
		
		System.out.println("\t\n***Menu Cliente***");
		System.out.println("Ingrese una opcion:");
		System.out.println("(1) Ver lista de articulos.");
		System.out.println("(2) Agregar articulo al carrito.");
		System.out.println("(3) Eliminar articulo del carrito.");
		System.out.println("(4) Ver carrito.");
		System.out.println("(5) Confirmar compra.");
		System.out.println("(6) Modulo bancario.");
		System.out.println("(7) Cerrar sesión.");
	}
	/**Agregar un articulo al carrito de compras*/
	public static void clientesOpcionDos(Scanner sc) {
		
		while(true) {
			System.out.print("Ingrese codigo de articulo: ");
			String codigo = sc.next();
			
			if(Stock.getInstance().getListaDeArticulos().containsKey(codigo)) {
				System.out.print("Ingrese la cantidad de articulos: ");
				int cantidad = sc.nextInt();
				
				if(cantidad<0) {
					System.out.println("La cantidad no puede ser negativa.");
					break;
				}
				
				if(Stock.getInstance().getListaDeArticulos().get(codigo).getCantidadDeArticulos() >= cantidad) {
					Carrito.getInstance().agregarArticulo(codigo, cantidad);
					System.out.println("Articulos agregados exitosamente!\n");
					break;
				} else if (Stock.getInstance().getListaDeArticulos().get(codigo).getCantidadDeArticulos() == 0) {
					System.err.println("Sin stock.");break;
				} else if (Stock.getInstance().getListaDeArticulos().get(codigo).getCantidadDeArticulos() < cantidad) {
					System.err.println("Error. Cantidad disponible: " + Stock.getInstance().getListaDeArticulos().get(codigo).getCantidadDeArticulos());
					break;
					
				} 
			
			} else {
				System.err.println("Código erroneo.");
				break;
				} 
		}
	}
	
	public static void clientesOpcionTres(Scanner sc) {
		System.out.print("Ingrese el codigo del articulo a eliminar: ");
		String codigo = sc.next();
		
		System.out.println("\nArticulo eliminado del carrito!");
		
		if (Carrito.getInstance().getCarritoDeCompra().containsKey(codigo)) {
			Carrito.getInstance().removerArticulo(codigo);
		} else System.err.println("Codigo erróneo.");
		
	}
	
	/**Ver los articulos del carrito.*/
	public static void clientesOpcionCuatro() {
		if(Carrito.getInstance().getCantidadArticulosEnCarrito() > 0) {
			
			Carrito.getInstance().verArticulosCarrito();
			
			System.out.println("Total: " + String.format("%.2f", Carrito.getInstance().totalCarrito()));
		} 
		else Carrito.getInstance().verArticulosCarrito();
	}
	/**Confirma la compra y actualiza el stock. Puede abonarse con el saldo del modulo bancario.*/
	public static void clientesOpcionCinco(Scanner sc) {
		System.out.println("¿Debitar de la cuenta?");
		System.out.println("(1) Si");
		System.out.println("(2) No");
		
		String opcionDebito = sc.next();
				
		if(Carrito.getInstance().totalCarrito() <= 0) {	
			System.err.println("No posee nada en el carrito");
		}
		else {
			if(opcionDebito.equals("1")) {
				double dineroUsuario = Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).getCantidadDinero();
				if (Carrito.getInstance().totalCarrito() < dineroUsuario) {
					dineroUsuario -= Carrito.getInstance().totalCarrito();
					Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).setCantidadDinero(dineroUsuario);
					Usuarios.guardar();
					Carrito.getInstance().confirmarCompra();
					System.out.println("Compra realizada con éxito.");
				} 
				else  {
					System.err.println("Saldo insuficiente.");
				}
			}
			else {
				Carrito.getInstance().confirmarCompra();
				System.out.println("Compra realizada con éxito.");
			}
		}	
	}
	
	public static int clientesOpcionSiete(Scanner sc) {
		int j=0;
		if (Carrito.getInstance().getCantidadArticulosEnCarrito() >= 1) {
			
			if (Menues.cerrarSesion(sc)) {
				j=1;
			} 
		} else {j=1;}
		return j;
	}
	
	/**
	 * metodo para advertir al usuario de que no confirmó su compra.
	 * @param sc pasa por parametro el Scanner
	 * @return true si se desea cerrar y eliminar el carrito, false si desea continuar y confirmar compra.
	 * */
	public static boolean cerrarSesion(Scanner sc) {
		boolean cerrar = true;
		
		if (Carrito.getInstance().getCantidadArticulosEnCarrito() >=1) {
			System.err.println("Advertencia: si cierra sesión sin confirmar su compra el carrito se eliminará.");
			System.out.println("¿Cerrar sesión de todos modos?");
			System.out.println("1:Si \n2:No");
			
			int opcion = sc.nextInt();
			if (opcion==1) {
				Carrito.getInstance().getCarritoDeCompra().clear();
				System.out.println("Gracias por utilizar nuestros servicios");
				cerrar = true;
			} else if (opcion==2) {
				cerrar = false;
			}else System.err.println("Opcion incorrecta.");
		}
		
		return cerrar;
	}
	
	/**Todas las operaciones del modulo bancario.
	 * @param sc Scanner
	 * @param mod objeto de la clase Modulo*/
	public static void moduloBancarioMenu(Scanner sc, Modulo mod) {
		int i=0;
		while(i==0) {
			
			System.out.println("Elija una opción");
			System.out.println("1 - Agregar monto");
			System.out.println("2 - Transferir dinero");
			System.out.println("3 - Retirar dinero ");
			System.out.println("4 - Ver saldo");
			System.out.println("5 - Salir");
	     
			switch (sc.next()) {
				case "1": 
					mod.opcionUno(sc);
					break;
				case "2":
					mod.opcionDos(sc);
					break;
				case "3":
					mod.opcionTres(sc);
					break;
				case "4":
					mod.opcionCuatro();
					break;
				case "5":
					i=1;
					break;
				default: System.out.println("La opcion ingresada es incorrecta. Ingrese un numero del 1 al 5. ");	
			}
		}
 	
	}
	
}