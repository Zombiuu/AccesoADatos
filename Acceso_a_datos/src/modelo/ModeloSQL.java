package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import entidades.Alumnos;

public class ModeloSQL extends ModeloPrincipal implements Acceso_a_datos{
		String url;
		String usuario;
		String clave;
		
		
	
	
	 public ModeloSQL() {
		
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File config = new File("conf/configuracion.ini");
			if (config.exists()) {
				entrada = new FileInputStream(config);

				propiedades.load(entrada);

				 url = propiedades.getProperty("BaseDatos");
				usuario = propiedades.getProperty("Usuario");
				clave = propiedades.getProperty("Clave");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	 

		
	 public Connection getConnection() {
			Connection con;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, usuario, clave);
				System.out.println(con);
				return con;

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"No se pudo conectar con la base de datos, modifique la informacion en la configuracion");
				return null;
				// TODO: handle exception
			}
		}
	 
	 

	@Override
	public ArrayList<Alumnos> recogerDatosBBDD() {
		
		ArrayList<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
		Connection connection = getConnection();

		String query = "SELECT * from alumnos";
		Statement st;
		ResultSet rs;
		System.out.println("dentro de recogerDatos()");
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Alumnos alumno;
			while (rs.next()) {
				alumno = new Alumnos(rs.getInt("cod"), rs.getString("dni"), rs.getString("nombre"),
						rs.getString("apellido"), rs.getInt("telefono"), rs.getString("nacionalidad"));
				arrAlumnos.add(alumno);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("recoge los datos" + arrAlumnos);
		return arrAlumnos;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarTodo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	
	
	


}
