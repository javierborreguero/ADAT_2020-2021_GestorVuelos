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
public class AccesoBaseDeDatos implements GestorAccesoDatos {
	public AccesoBaseDeDatos() {
		// TODO Auto-generated constructor stub
		System.out.println("ACCESO A DATOS - BASES DE DATOS");
	}

	@Override
	public HashMap<String, Vuelos> leerVuelos() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
