package controlador;

import java.util.HashMap;

import modelo.Vuelos;

public interface GestorAccesoDatos {
	public HashMap<String, Vuelos> leerVuelos();
}
