package controlador;

import java.io.IOException;
import java.util.HashMap;

import modelo.Vuelos;

public interface GestorAccesoDatos {
	public HashMap<String, Vuelos> leerVuelos() throws IOException;

	public boolean insertarVuelo(Vuelos vuelos) throws IOException;

	public void guardarDatosVuelo(HashMap<String, Vuelos> listaVuelos) throws IOException;

	public boolean comprobarIdVuelo(Vuelos vuelos) throws IOException;

	public boolean borrarDatos() throws IOException;

	public boolean modificarVuelo(String modificar, Vuelos mVuelos) throws IOException;

}