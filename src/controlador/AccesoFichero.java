/**
 * 
 */
package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import modelo.Vuelos;

/**
 * @author Usr
 *
 */
public class AccesoFichero implements GestorAccesoDatos {
	File infoVuelos;

	public AccesoFichero() {
		System.out.println("<----- FICHEROS DE TEXTO ----->");

	}

	@Override
	public HashMap<String, Vuelos> leerVuelos() {
		HashMap<String, Vuelos> verInfoVuelos = new HashMap<String, Vuelos>();
		infoVuelos = new File("Ficheros/datos/Vuelos.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(infoVuelos));
			String texoPrincipal;
			Vuelos mVuelos;
			while ((texoPrincipal = br.readLine()) != null) {
				if (!texoPrincipal.equals("*")) {
					String[] textoSecundario = texoPrincipal.split(";");
					mVuelos = new Vuelos();
					mVuelos.setId(Integer.parseInt(textoSecundario[0]));
					mVuelos.setCodigo_vuelo(textoSecundario[1]);
					mVuelos.setOrigen(textoSecundario[2]);
					mVuelos.setDestino(textoSecundario[3]);
					mVuelos.setHora(textoSecundario[4]);
					mVuelos.setFecha(textoSecundario[5]);
					mVuelos.setPlazas_totales(Integer.parseInt(textoSecundario[6]));
					mVuelos.setPlazas_disponibles(Integer.parseInt(textoSecundario[7]));
					verInfoVuelos.put(textoSecundario[0], mVuelos);
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return verInfoVuelos;
	}

	@Override
	public boolean insertarVuelo(Vuelos vuelos) throws IOException {
		infoVuelos = new File("Ficheros/datos/Vuelos.txt");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(infoVuelos));
			bw.write(vuelos.getId() + ";" + vuelos.getCodigo_vuelo() + ";" + vuelos.getOrigen() + ";"
					+ vuelos.getDestino() + ";" + vuelos.getHora() + ";" + vuelos.getFecha() + ";"
					+ vuelos.getPlazas_totales() + ";" + vuelos.getPlazas_disponibles() + "\n" + "*" + "\n");
			bw.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;

	}

}
