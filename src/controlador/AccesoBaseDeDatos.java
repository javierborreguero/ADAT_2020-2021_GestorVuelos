/**
 * 
 */
package controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

import modelo.Vuelos;

/**
 * @author Usr
 *
 */
public class AccesoBaseDeDatos implements GestorAccesoDatos {
	Connection mConnection;
	final String URL, DRIVER, USER, PASSWORD;

	public AccesoBaseDeDatos() {
		System.out.println("<----- BASE DE DATOS ----->");
		this.URL = "jdbc:mysql://localhost:3306/adat_vuelos";
		this.DRIVER = "com.mysql.cj.jdbc.Driver";
		this.USER = "root";
		this.PASSWORD = "";
		try {
			Class.forName(DRIVER).newInstance();
			mConnection = DriverManager.getConnection(URL, USER, PASSWORD);
			if (mConnection != null) {
				System.out.println("Conexion con la base de datos establecida");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}

	@Override
	public HashMap<String, Vuelos> leerVuelos() throws IOException {
		// TODO Auto-generated method stub
		HashMap<String, Vuelos> verInfoVuelos = new HashMap<String, Vuelos>();
		Vuelos mVuelos;
		PreparedStatement pstm;
		ResultSet rset;
		try {
			pstm = mConnection.prepareStatement("Select * from vuelos");
			rset = pstm.executeQuery();
			while (rset.next()) {
				mVuelos = new Vuelos(Integer.parseInt(rset.getString("Id")), rset.getString("Codigo_Vuelo"),
						rset.getString("Origen"), rset.getString("Destino"), rset.getString("Fecha"),
						rset.getString("Hora"), Integer.parseInt(rset.getString("Plazas_totales")),
						Integer.parseInt(rset.getString("PLazas_disponibles")));
				verInfoVuelos.put(rset.getString("Id"), mVuelos);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return verInfoVuelos;
	}

}
