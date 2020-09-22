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
		br = null;
	}

	@Override
	public HashMap<String, Vuelos> leerVuelos() {
		HashMap<String, Vuelos> verInfoVuelos = new HashMap<String, Vuelos>();
		infoVuelos = new File("Ficheros/datos/Vuelos.txt");
		try {
			br = new BufferedReader(new FileReader(infoVuelos));
			String textoPrincipal = br.readLine();
			while (textoPrincipal != null) {
				System.out.println(textoPrincipal);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
