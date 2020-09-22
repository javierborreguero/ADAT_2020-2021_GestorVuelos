/**
 * 
 */
package controlador;

import java.io.IOException;
import java.util.HashMap;

import modelo.Vuelos;

/**
 * @author Usr
 *
 */
public class Controlador {
	public GestorAccesoDatos eligoModoAccesoDatos;

	public Controlador() {

	}

	public void elegirModoAccesoDatos(int acceso) {
		if (acceso == 1) {
			eligoModoAccesoDatos = new AccesoFichero();
		}
	}

	public HashMap<String, Vuelos> leerDatos() throws IOException {
		HashMap<String, Vuelos> verInfoVuelos = eligoModoAccesoDatos.leerVuelos();
		return verInfoVuelos;
	}

}
