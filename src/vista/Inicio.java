/**
 * 
 */
package vista;

import java.util.Scanner;

/**
 * @author Usr
 *
 */
public class Inicio {
	private Scanner sc;

	/**
	 * @param args
	 */
	public Inicio() {
		sc = new Scanner(System.in);
	}

	public void run() {
		menuAccesoDatos();

	}

	private void menuAccesoDatos() {
		// TODO Auto-generated method stub
		System.out.println("<----- BIENVENIDOS AL CONTROLADOR DE VUELOS ----->");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inicio mInicio = new Inicio();
		mInicio.run();

	}

}
