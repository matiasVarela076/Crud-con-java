package Usuario;


import java.util.InputMismatchException;
import java.util.Scanner;

import Util.Menues;

public class Modulo {
	
	private double cantidadDinero = 0;
	

	/**
	 * Agregar saldo a la cuenta: Ingresa la cantidad que desea agregar a la cuenta y éste se agrega solo si el monto es mayor a cero.
	 * Una vez agregado el saldo, se actualiza el archivo Usuarios.txt 
	 * @param sc Scanner
	 * */
	public void opcionUno(Scanner sc) {
		try { 
		System.out.println("Ingrese la cantidad que desea agregar en su cuenta: ");
		double ingreso = sc.nextDouble();
		cantidadDinero = Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).getCantidadDinero();
	    System.out.println("\nMonto agregado exitosamente!");
		
		if (ingreso > 0) {
			cantidadDinero += ingreso;
			Usuarios.getInstance().getUsuario(Menues.username).setCantidadDinero(cantidadDinero);
			Usuarios.guardar();
		} else System.err.println("El monto ingresado es inválido.");
		
	}catch(InputMismatchException e) {
	
		
		
		System.err.println("\n\nDio un " + e +" Debe ingresar datos numericos, elija una opcion:  " );
	}
		
	
	}
	
	
		
	
	
	/**
	 * Transferencia de dinero: ingresa la cantidad a transferir y el nombre del destinatario. Lleva a cabo la transaccion solo si 
	 * la cantidad a transferir es mayor a cero y menor o igual al saldo del usuario. 
	 * @param sc Scanner
	 * */
	public void opcionDos (Scanner sc) {
		
		cantidadDinero = Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).getCantidadDinero();
		
		System.out.print("Ingrese la cantidad que desea transferir: ");
		double cantidadTransferencia = sc.nextDouble();
		System.out.print("Ingrese el nombre del destinatario: ");
		String nombre = sc.next();
		
		if(cantidadTransferencia > 0 && cantidadTransferencia <= cantidadDinero) {
			Usuarios.getInstance().transferencia(cantidadTransferencia, nombre);
			System.out.println("Transferencia Exitosa!");
		} else if (cantidadTransferencia > cantidadDinero) {
			System.err.println("Saldo insuficiente");
		} else System.err.println("El monto ingresado es inválido.");
		
	}
	
	
	/**
	 * Retirar dinero de la cuenta. Se ingresa la cantidad de dinero que el usuario desea retira, y se lleva a cabo solo si el monto a retirar 
	 * es menor o igual al saldo de la cuenta, y si es mayor a 0.
	 * @param sc Scanner
	 * */
	public void opcionTres (Scanner sc) {

		System.out.println("Ingrese la cantidad que desea retirar de su cuenta: ");
		double retiro = sc.nextDouble();
		cantidadDinero = Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).getCantidadDinero();
		if (retiro > 0 && retiro <= cantidadDinero ) {	
			cantidadDinero -= retiro;
			Usuarios.getInstance().getUsuario(Menues.username).setCantidadDinero(cantidadDinero);
			Usuarios.guardar();
			System.out.println("Retiro exitoso!, su saldo en cuenta es: "+cantidadDinero);
		}else if (retiro > cantidadDinero) {
			System.err.println("Saldo insuficiente.");
		}
		else{
			System.err.println("No puede retirar una cantidad negativa. ");
		}
		
		
	}
	
	/**
	 * Devuelve el saldo del usuario logueado en el banco.
	 * */
	public void opcionCuatro() {
		System.out.println("Saldo: " + Usuarios.getInstance().getDiccionarioUsuarios().get(Menues.username).getCantidadDinero());
	}
	
		
}
