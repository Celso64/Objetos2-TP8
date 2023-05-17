package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import modelo.DBFacade;
import modelo.DBManagment;

public class App {

    public static void main(String[] args) {

	String consulta = "SELECT p.nombre Nombre, t.numero Telefono FROM personas p LEFT JOIN telefonos T ON (p.id = t.idPersona) ORDER BY Nombre ASC, Telefono ASC";

	Connection conn = null;
	try {
	    conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/agenda_db");
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	DBFacade database = new DBManagment(conn);

	database.open();
	var res1 = database.queryResultAsAsociation(consulta);
	database.close();
	Integer i = 0;
	for (Map<String, String> fila : res1) {
	    System.out.println("FILA " + i);
	    var columnas = fila.keySet();
	    columnas.stream().forEach(x -> System.out.println(" ".repeat(3) + x + " -> " + fila.get(x)));
	    i++;
	}

	System.out.println("\n************************************************\n");

	database.open();
	var res2 = database.queryResultAsArray(consulta);
	database.close();

	System.out.println("Tabla con Array - FILAS: " + res2.size());
	for (String[] fila : res2) {
	    for (int j = 1; j < fila.length; j++) {
		System.out.print(fila[j] + " ".repeat(2));
	    }
	    System.out.println();
	}

    }

}
