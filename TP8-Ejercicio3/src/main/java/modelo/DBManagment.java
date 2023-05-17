package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBManagment implements DBFacade {

    private static final String VALOR_NULO = "NADA";

    private Connection conexion;
    private Boolean conexionAbierta;

    public DBManagment(Connection conexion) {
	this.conexion = conexion;
	this.conexionAbierta = false;
    }

    public void open() {
	this.conexionAbierta = true;
    }

    public List<Map<String, String>> queryResultAsAsociation(String sql) {

	this.laConexionEstaAbierta();

	var tabla = new ArrayList<Map<String, String>>();
	Map<String, String> fila;

	try (PreparedStatement statement = this.conexion.prepareStatement(sql);) {

	    ResultSet result = statement.executeQuery();
	    var titulares = result.getMetaData();

	    while (result.next()) {
		fila = new HashMap<>();

		for (int i = 1; i <= titulares.getColumnCount(); i++) {
		    Object contenidoCelda = result.getObject(i);
		    String celda = (contenidoCelda == null) ? VALOR_NULO : contenidoCelda.toString();
		    fila.put(titulares.getColumnName(i), celda);
		}

		tabla.add(fila);
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage());
	}
	return tabla;

    }

    public List<String[]> queryResultAsArray(String sql) {

	this.laConexionEstaAbierta();

	List<String[]> tabla = new LinkedList<>();
	String[] fila;

	try (PreparedStatement statement = this.conexion.prepareStatement(sql);) {

	    var result = statement.executeQuery();
	    var cantidadColumnas = result.getMetaData().getColumnCount();

	    while (result.next()) {

		fila = new String[cantidadColumnas + 1];

		for (int i = 1; i <= cantidadColumnas; i++) {
		    Object contenidoCelda = result.getObject(i);
		    String celda = (contenidoCelda == null) ? VALOR_NULO : contenidoCelda.toString();
		    fila[i] = celda;
		}

		tabla.add(fila);

	    }
	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage());
	}

	return tabla;

    }

    public void close() {
	this.conexionAbierta = false;
    }

    private void laConexionEstaAbierta() {
	if (!this.conexionAbierta)
	    throw new RuntimeException("Conexion Cerrada");
    }

}
