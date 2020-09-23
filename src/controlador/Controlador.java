/**
 * 
 */
package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import modelo.Vuelos;

/**
 * @author Usr
 *
 */
public class Controlador {
	public GestorAccesoDatos eligoModoAccesoDatos;
	HashMap<String, Vuelos> verInfoVuelos;

	public Controlador() {

	}

	public void elegirModoAccesoDatos(int acceso) {
		if (acceso == 1) {
			eligoModoAccesoDatos = new AccesoFichero();
		} else if (acceso == 2) {
			eligoModoAccesoDatos = new AccesoBaseDeDatos();
		}
	}

	public HashMap<String, Vuelos> leerDatos() throws IOException {
		HashMap<String, Vuelos> verInfoVuelos = eligoModoAccesoDatos.leerVuelos();
		return verInfoVuelos;
	}

	public boolean insetarVuelo(Vuelos vuelos) {
		try {
			eligoModoAccesoDatos.insertarVuelo(vuelos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
