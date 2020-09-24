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
	HashMap<String, Vuelos> mVuelos;

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
		System.out.println("Selecciona desde donde quieres acceder a la información:\n1. Ficheros\n2. Base de datos");
		String elegirModoAcceso = "";
		try {
			int acceso = sc.nextInt();
			switch (acceso) {
			case 1:
				elegirModoAcceso = "Ficheros";
				mControlador.elegirModoAccesoDatos(acceso);
				menuElegirOpcion();
				break;
			case 2:
				elegirModoAcceso = "Base de datos";
				mControlador.elegirModoAccesoDatos(acceso);
				menuElegirOpcion();
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void menuElegirOpcion() {
		// TODO Auto-generated method stub
		System.out.println("¿Que quieres hacer?\n1. Leer\n2. Insertar\n3. Borrar");
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
		case 2:
			try {
				insertarDatos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				borrarVUelos();
			} catch (Exception e) {
				// TODO: handle exception
			}
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

	private void insertarDatos() throws IOException {
		// TODO Auto-generated method stub
		// boolean datosAlmacenados = mControlador.;
		if (mControlador.leerDatos() != null) {
			System.out.println(mControlador.leerDatos());
		}
		sc.nextLine();
		System.out.print("Indica el Id del vuelo ");
		String id_vuelo = sc.nextLine();
		System.out.print("¿Cual es el código del vuelo? ");
		String codigo_vuelo = sc.nextLine();
		System.out.print("¿En qué ciudad despega el avión? ");
		String ciudad_origen = sc.nextLine();
		System.out.print("¿En qué ciudad aterriza el avión ");
		String ciudad_destino = sc.nextLine();
		System.out.print("¿A qué hora está programado el vuelo? ");
		String hora_vuelo = sc.nextLine();
		System.out.print("¿y para que día está programado? ");
		String fecha_vuelo = sc.nextLine();
		System.out.print("Indica la cantidad de plazas que dispone el avión ");
		String numero_plazas_totales = sc.nextLine();
		System.out.print("Indica las plazas que hay disponibles ");
		String numero_plazas_disponibles = sc.nextLine();
		Vuelos mVuelos = new Vuelos(Integer.parseInt(id_vuelo), codigo_vuelo, ciudad_origen, ciudad_destino,
				fecha_vuelo, hora_vuelo, Integer.parseInt(numero_plazas_totales),
				Integer.parseInt(numero_plazas_disponibles));
		if (mControlador.insetarVuelo(mVuelos)) {
			System.out.println("La información ha sido almacenada correctamente");
		} else {
			System.out.println("Se ha producido un error al guardar los datos");
		}
	}
	private void borrarVUelos() throws IOException {
		if (mControlador.borrarDatosVuelos()) {
			System.out.println("Los datos han sido borrados correctamente");
		} else {
			System.out.println("No se han podido borrar los datos");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inicio mInicio = new Inicio();
		mInicio.run();

	}

}