package controlador;

import java.io.IOException;
import java.util.HashMap;

import modelo.Vuelos;

public interface GestorAccesoDatos {
	public HashMap<String, Vuelos> leerVuelos() throws IOException;

	public boolean insertarVuelo(Vuelos vuelos) throws IOException;

}
