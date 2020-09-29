/**
 * 
 */
package vista;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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
		System.out.println(
				"¿Que quieres hacer?\n1. Leer vuelos\n2. Insertar un vuelo\n3. Borrar\n4. Buscar un vuelo\n5. Modificar los datos de un vuelo");
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
				"Introduzca la ciudad de origen " + " (esta información es necesaria para poder lozalizar el vuelo)");
		String introducir_ciudad_origen = sc.nextLine();
		System.out.print(
				"Introduzca la ciudad destino " + " (esta información es necesaria para poder lozalizar el vuelo)");
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
		modificar = null;
		System.out.println("¿Estas seguro que quieres cambiar la información de un vuelo?\n1. Si\n2. No");
		int si_no = sc.nextInt();
		switch (si_no) {
		case 1:
			modificar = mostrarDatosGuardados();
			break;
		case 2:
			menuElegirOpcion();
			break;
		default:
			break;
		}
		switch (si_no) {
		case 1:
			// actualizarVuelo();
			break;
		case 2:
			menuElegirOpcion();
			break;
		default:
			break;
		}
	}

	private String mostrarDatosGuardados() throws IOException {
		// TODO Auto-generated method stub
		int contador = 1;
		System.out.println("En nuestro fichero tenemos almacenada la siguiete información:");
		for (Entry<String, Vuelos> entry : mControlador.leerDatos().entrySet()) {
			System.out.println("<---- Vuelo " + contador + " ----->");
			System.out.print("Id: " + entry.getValue().getId() + " " + "Origen: " + entry.getValue().getOrigen() + " "
					+ "Destino: " + entry.getValue().getDestino() + "\n");
			System.out.println("-----------------------------------");
			contador++;
		}
		sc.nextLine();
		System.out.println("Elige el id del vuelo que quieres modificar\n");
		int elegirId = sc.nextInt();
		for (Entry<String, Vuelos> entry : mControlador.leerDatos().entrySet()) {
			if (elegirId == entry.getValue().getId()) {
				System.out.println("Estos son los datos que puedes modificar\n");
				System.out.println("Codigo del vuelo: " + entry.getValue().getCodigo_vuelo());
				System.out.println("Origen: " + entry.getValue().getOrigen());
				System.out.println("Destino: " + entry.getValue().getDestino());
				System.out.println("Hora: " + entry.getValue().getHora());
				System.out.println("Fecha: " + entry.getValue().getFecha());
				System.out.println("Numero de plazas totales: " + entry.getValue().getPlazas_totales());
				System.out.println("Numero de plazas disponibles: " + entry.getValue().getPlazas_disponibles() + "\n");
			}
		}
		actualizarVuelo();
		return modificar;
	}

	private void actualizarVuelo() throws IOException {
		sc.nextLine();
		System.out.println("Indica cual es el nuevo punto de origen");
		String nuevo_punto_origen = sc.nextLine();
		System.out.println("Indica cual es el nuevo punto de destino");
		String nuevo_punto_destino = sc.nextLine();
		System.out.println("Indica la nueva hora a la que sale el vuelo");
		String nuevo_horario = sc.nextLine();
		System.out.println("Indica la nueva fecha programada para el vuelo");
		String nueva_fecha = sc.nextLine();
		System.out.println("¿Cuantas plazas tiene el avión en total?");
		String nuevo_numero_plazas_totales = sc.nextLine();
		System.out.println("¿Cuántas plazas tiene disponibles?");
		String nuevo_numero_plazas_disponibles = sc.nextLine();
		Vuelos mVuelos = new Vuelos(Integer.parseInt(modificar), nuevo_punto_origen, nuevo_punto_destino, nueva_fecha,
				nuevo_horario, Integer.parseInt(nuevo_numero_plazas_totales),
				Integer.parseInt(nuevo_numero_plazas_disponibles));
		if (mControlador.modificarVuelo(modificar, mVuelos)) {
			System.out.println("yaap");
		} else {
			System.out.println("nooh");
		}
	}

}