/**
 * 
 */
package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import modelo.Vuelos;

/**
 * @author Usr
 *
 */
public class AccesoFichero implements GestorAccesoDatos {
	File infoVuelos;
	BufferedReader br;

	public AccesoFichero() {
		System.out.println("ACCESO A DATOS - FICHEROS DE TEXTO");

	}

	@Override
	public HashMap<String, Vuelos> leerVuelos() {
		HashMap<String, Vuelos> verInfoVuelos = new HashMap<String, Vuelos>();
		infoVuelos = new File("Ficheros/datos/Vuelos.txt");
		br = null;
		try {
			br = new BufferedReader(new FileReader(infoVuelos));
			String textoPrincipal = "";
			Vuelos mVuelos;
			int aux = 1;
			while ((textoPrincipal = br.readLine()) != null) {
				String[] textoSecundario = textoPrincipal.split(";");
				mVuelos = new Vuelos();
				mVuelos.setId(aux);
				mVuelos.setCodigo_vuelo(textoSecundario[1]);
				mVuelos.setOrigen(textoSecundario[2]);
				mVuelos.setDestino(textoSecundario[3]);
				mVuelos.setHora(textoSecundario[4]);
				mVuelos.setFecha(textoSecundario[5]);
				mVuelos.setPlazas_totales(Integer.parseInt(textoSecundario[6]));
				mVuelos.setPlazas_disponibles(Integer.parseInt(textoSecundario[7]));
				verInfoVuelos.put(textoSecundario[0], mVuelos);
				aux++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return verInfoVuelos;
	}

}
