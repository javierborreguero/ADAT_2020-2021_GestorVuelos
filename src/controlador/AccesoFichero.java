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
import java.util.Scanner;
import java.util.Map.Entry;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import modelo.Vuelos;
import vista.Inicio;

/**
 * @author Usr
 *
 */
public class AccesoFichero implements GestorAccesoDatos {
	File infoVuelos;
	Controlador mControlador;

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
	public boolean comprobarIdVuelo(Vuelos vuelos) throws IOException {
		HashMap<String, Vuelos> comprobarId = leerVuelos();
		for (Entry<String, Vuelos> entry : comprobarId.entrySet()) {
			if (entry.getValue().getId() == vuelos.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean insertarVuelo(Vuelos vuelos) throws IOException {
		infoVuelos = new File("Ficheros/datos/Vuelos.txt");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(infoVuelos, true));
			bw.write(vuelos.getId() + ";" + vuelos.getCodigo_vuelo() + ";" + vuelos.getOrigen() + ";"
					+ vuelos.getDestino() + ";" + vuelos.getHora() + ";" + vuelos.getFecha() + ";"
					+ vuelos.getPlazas_totales() + ";" + vuelos.getPlazas_disponibles());
			bw.write("\n");
			bw.write("*");
			bw.write("\n");
			bw.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;

	}

	@Override
	public void guardarDatosVuelo(HashMap<String, Vuelos> listaVuelos) throws IOException {
		// TODO Auto-generated method stub
		borrarDatos();
		for (Entry<String, Vuelos> entry : listaVuelos.entrySet()) {
			insertarVuelo(listaVuelos.get(entry.getKey()));
		}

	}

	@Override
	public boolean borrarDatos() throws IOException {
		// TODO Auto-generated method stub
		infoVuelos = new File("Ficheros/datos/Vuelos.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(infoVuelos));
		bw.write("");
		bw.close();
		return true;
	}

	@Override
	public boolean modificarVuelo(String modificar, Vuelos mVuelos) throws IOException {
		// TODO Auto-generated method stub
		HashMap<String, Vuelos> vueloModificar = leerVuelos();
		boolean vueloHaSidoModificado = false;
		for (Entry<String, Vuelos> entry : vueloModificar.entrySet()) {
			if (entry.getKey().equals(modificar)) {
				entry.setValue(mVuelos);
				vueloHaSidoModificado = true;
				// insertarVuelo(vueloModificar);
			}
		}
		if (vueloHaSidoModificado) {
			guardarDatosVuelo(vueloModificar);
		}
		return vueloHaSidoModificado;
	}

}