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
	Vuelos mVuelos;
	private String modificar;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inicio mInicio = new Inicio();
		mInicio.run();

	}

	private void menuAccesoDatos() {
		// TODO Auto-generated method stub
		System.out.println("<----- BIENVENIDOS AL CONTROLADOR DE VUELOS ----->\n");
		System.out.println("Selecciona desde donde quieres acceder a la informaci�n:\n1. Ficheros\n2. Base de datos");
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
		System.out.println(
				"�Que quieres hacer?\n1. Leer vuelos\n2. Insertar un vuelo\n3. Borrar\n4. Buscar un vuelo\n5. Modificar los datos de un vuelo");
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
			break;
		case 4:
			try {
				buscarVuelo();
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case 5:
			try {
				String modificar = null;
				modificarVuelo();
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		default:
			break;
		}

	}

	/* ------------ LEER ------------ */
	private void leerDatos() throws IOException {
		int contador = 1;
		if (mControlador.leerDatos() == null) {
			System.out.println("No existe informaci�n de ning�n vuelo");
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

	/*------------ INSERTAR ------------*/
	private void insertarDatos() throws IOException {
		// TODO Auto-generated method stub
		// boolean datosAlmacenados = mControlador.;
		if (mControlador.leerDatos() != null) {
			System.out.println(mControlador.leerDatos());
		}
		sc.nextLine();
		System.out.print("Indica el Id del vuelo ");
		String id_vuelo = sc.nextLine();
		System.out.print("�Cual es el c�digo del vuelo? ");
		String codigo_vuelo = sc.nextLine();
		System.out.print("�En qu� ciudad despega el avi�n? ");
		String ciudad_origen = sc.nextLine();
		System.out.print("�En qu� ciudad aterriza el avi�n ");
		String ciudad_destino = sc.nextLine();
		System.out.print("�A qu� hora est� programado el vuelo? ");
		String hora_vuelo = sc.nextLine();
		System.out.print("�y para que d�a est� programado? ");
		String fecha_vuelo = sc.nextLine();
		System.out.print("Indica la cantidad de plazas que dispone el avi�n ");
		String numero_plazas_totales = sc.nextLine();
		System.out.print("Indica las plazas que hay disponibles ");
		String numero_plazas_disponibles = sc.nextLine();
		Vuelos mVuelos = new Vuelos(Integer.parseInt(id_vuelo), codigo_vuelo, ciudad_origen, ciudad_destino,
				fecha_vuelo, hora_vuelo, Integer.parseInt(numero_plazas_totales),
				Integer.parseInt(numero_plazas_disponibles));
		if (mControlador.insetarVuelo(mVuelos)) {
			System.out.println("La informaci�n ha sido almacenada correctamente");
		} else {
			System.out.println("Se ha producido un error al guardar los datos");
		}
	}

	/* ------------ BORRAR ------------ */
	private void borrarVUelos() throws IOException {
		if (mControlador.borrarDatosVuelos()) {
			System.out.println("Los datos han sido borrados correctamente");
		} else {
			System.out.println("No se han podido borrar los datos");
		}

	}

	/* ------------ BUSCAR ------------ */
	private void buscarVuelo() throws IOException {
		sc.nextLine();
		int contador = 1;
		System.out.print(
				"Introduzca la ciudad de origen " + " (esta informaci�n es necesaria para poder lozalizar el vuelo)");
		String introducir_ciudad_origen = sc.nextLine();
		System.out.print(
				"Introduzca la ciudad destino " + " (esta informaci�n es necesaria para poder lozalizar el vuelo)");
		String introducir_ciudad_destino = sc.nextLine();
		for (Entry<String, Vuelos> entry : mControlador.leerDatos().entrySet()) {
			if (introducir_ciudad_origen.equalsIgnoreCase(entry.getValue().getOrigen())
					&& introducir_ciudad_destino.equalsIgnoreCase(entry.getValue().getDestino())) {
				System.out.println("<---- Resultado de la busqueda ----->\n" + "<----- Vuelo " + contador + " ----->");
				System.out.println("Id: " + entry.getValue().getId());
				System.out.println("Codigo del vuelo: " + entry.getValue().getCodigo_vuelo());
				System.out.println("Origen: " + entry.getValue().getOrigen());
				System.out.println("Destino: " + entry.getValue().getDestino());
				System.out.println("Hora: " + entry.getValue().getHora());
				System.out.println("Fecha: " + entry.getValue().getFecha());
				System.out.println("Numero de plazas totales: " + entry.getValue().getPlazas_totales());
				System.out.println("Numero de plazas disponibles: " + entry.getValue().getPlazas_disponibles());
				contador++;
				System.out.println("-----------------------\n");

			}

		}
		System.out.println("-----------------------\n");
	}

	/* ------------ MODIFICAR ------------ */
	private void modificarVuelo() throws IOException {
		sc.nextLine();
		System.out.println("�Estas seguro que quieres cambiar la informaci�n de un vuelo? Si/No");
		String si_no = sc.nextLine();
		if (si_no.equalsIgnoreCase("Si")) {
			actualizarVuelo();
		} else if (si_no.equalsIgnoreCase("No")) {
			menuElegirOpcion();
		}
//		System.out.println("Para cambiar informaci�n de un vuelo en primer lugar");
//		buscarVuelo();
//		System.out.println("�Cual es el nuevo punto de origen?");
//		String nuevo_origen = sc.nextLine();
//		System.out.println("�CUal es el nuevo punto de destino?");
//		String nuevo_destino = sc.nextLine();
//		System.out.println("Si el vuelo se ha adelantado o retradado, indique el nuevo horario");
//		String nuea_hora = sc.nextLine();
//		System.out.println("?La fecha ha sufrido alguna modificaci�n?");
//		String nueva_fecha = sc.nextLine();
//		System.out.println("Introduzca el cambio que se han producido en las plazas totales");
//		String nuevas_plazas_totales = sc.nextLine();
//		System.out.println("Introduzca el cambio que se han producido en las plazas disponibles");
//		String nuevas_plazas_disponibles = sc.nextLine();
//		Vuelos mVuelos = new Vuelos(nuevo_origen, nuevo_destino, nueva_fecha, nuea_hora,
//				Integer.parseInt(nuevas_plazas_totales), Integer.parseInt(nuevas_plazas_disponibles));
//		if (mControlador.modificarVuelo(modificar, mVuelos)) {
//			System.out.println("La informaci�n ha sido actualizada");
//		} else {
//			System.out.println("Se ha producido un error al guardar los datos");
//		}
	}

	private void actualizarVuelo() throws IOException {
		System.out.println("Procederemos a actualizar el vuelo");
	}

}