/**
 * 
 */
package vista;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import controlador.Controlador;
import controlador.GestorAccesoDatos;
import modelo.Vuelos;

/**
 * @author Usr
 *
 */
public class Inicio {
	private Scanner sc;
	private Controlador mControlador;
	

	/**
	 * @param args
	 */
	public Inicio() {
		sc = new Scanner(System.in);
		mControlador = new Controlador();

	}

	public void run() {
		menuAccesoDatos();

	}

	private void menuAccesoDatos() {
		// TODO Auto-generated method stub
		System.out.println("<----- BIENVENIDOS AL CONTROLADOR DE VUELOS ----->\n");
		System.out.println("Selecciona desde donde quieres acceder a la información:\n 1. Ficheros\n 2. Base de datos");
		String elegirModoAcceso = "";
		try {
			int acceso = sc.nextInt();
			switch (acceso) {
			case 1:
				elegirModoAcceso = "Ficheros";
				mControlador.elegirModoAccesoDatos(acceso);
				menuElegirOpcion();
				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void menuElegirOpcion() {
		// TODO Auto-generated method stub
		System.out.println("¿Que quieres hacer?\n 1. Leer");
		int acceso = sc.nextInt();
		switch (acceso) {
		case 1:
			try {
				leerDatos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

	}

	private void leerDatos() throws IOException {
		int contador = 1;
		if (mControlador.leerDatos() == null) {
			System.out.println("No existe información de ningún vuelo");
		} else {
			for (Entry<String, Vuelos> entry : mControlador.leerDatos().entrySet()) {
				System.out.println("<----- Vuelo " + contador + " ----->");
				System.out.println("Id: " + entry.getValue().getId());
				System.out.println("Codigo del vuelo: " + entry.getValue().getCodigo_vuelo());
				System.out.println("Origen: " + entry.getValue().getOrigen());
				System.out.println("Destino: " + entry.getValue().getDestino());
				System.out.println("Hora: " + entry.getValue().getHora());
				System.out.println("Fecha: " + entry.getValue().getFecha());
				System.out.println("Numero de plazas totales: " + entry.getValue().getPlazas_totales());
				System.out.println("Numero de plazas disponibles: " + entry.getValue().getPlazas_disponibles());
				System.out.println("-----------------------\n");
				contador++;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inicio mInicio = new Inicio();
		mInicio.run();

	}

}
