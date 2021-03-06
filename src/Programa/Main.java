package Programa;
import java.util.Scanner;

import Articulos.*;
import Util.*;
import Usuario.*;

public class Main {

	public static void main(String[] args) {
		/* INICIO CARRITO, CARGAR STOCK, INICIO SCANNER, INICIO-CARGA USUARIOS, INICIO CONTADOR BUCLE (MENU EMPELADOS), INICIO MODULO BANCARIO (CLIENTES) */
		Carrito.getInstance();
		Stock.cargar();
		Usuarios.cargar();
		Modulo mod = new Modulo();
		Scanner sc = new Scanner(System.in);
		int i = 0, j = 0;
		/*BUCLE LOGUEO*/
		while(true) {
			Menues.menuUsuarios();
			String opcionUsuario = sc.next();
			/*REGISTRARSE*/
			if(opcionUsuario.equals("1")) {
				Menues.crearUsuario(sc);
			} 
			/*LOGUEARSE*/
			else if (opcionUsuario.equals("2")) {
				i = 0; 
				
				if(Menues.iniciarSesion(sc)) {
					/*MENU DE EMPLEADOS*/
					if (Usuarios.getInstance().getUsuario(Menues.username).getTipoDeUsuario().toLowerCase().equals("empleado")) {
						while(i == 0) {
							Menues.menuEmpleados();
							switch(sc.next()) {
							case "1" : Menues.empleadosOpcionUno(); break;
							case "2" : Menues.empleadosOpcionDos(sc); break;
							case "3" : Menues.empleadosOpcionTres(sc); break;
							case "4" : Menues.empleadosOpcionCuatro(sc); break;
							case "5" : Menues.empleadosOpcionCinco(sc); break;
							case "6" :	i = 1; break;
							default: System.out.println("La opcion ingresada es incorrecta. Ingrese un numero del 1 al 6. ");
							
							}
						}
					}
					
					else if (Usuarios.getInstance().getUsuario(Menues.username).getTipoDeUsuario().toLowerCase().equals("cliente")) {
						j=0;
						while(j==0) {
							Menues.menuClientes();
							String opcion = sc.next();
							switch(opcion) {
								case "1": Stock.getInstance().verArticulos(); break;
								case "2": Menues.clientesOpcionDos(sc); break;
								case "3": Menues.clientesOpcionTres(sc); break;
								case "4": Menues.clientesOpcionCuatro(); break;
								case "5": Menues.clientesOpcionCinco(sc); break;
								case "6": Menues.moduloBancarioMenu(sc, mod); break;	
								case "7": j = Menues.clientesOpcionSiete(sc); break;
								default: System.out.println("La opcion ingresada es incorrecta. Ingrese un numero del 1 al 7. ");
							}
						}
					}
				}
			}  //cierra opcion 2
			else if (opcionUsuario.equals("3")) {
				System.out.println("Gracias por utilizar nuestro servicio");
				break;
			} 
			else System.err.println("Opci�n incorrecta.");
		}
	}
}
