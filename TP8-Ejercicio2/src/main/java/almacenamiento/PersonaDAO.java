package almacenamiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Persona;
import modelo.TelefonosDefault;

public class PersonaDAO {

    private Connection obtenerConexion() {
	Connection conn = null;

	String url = "jdbc:sqlite:./src/main/resources/agenda_db";

	try {
	    conn = DriverManager.getConnection(url);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return conn;
    }

    public Persona personaPorId(int id) {
	String sql = "SELECT p.nombre FROM personas p WHERE p.id = ?";
	try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
	    statement.setInt(1, id);
	    ResultSet result = statement.executeQuery();
	    String nombrePersona = null;
	    while (result.next()) {
		nombrePersona = result.getString(1);

	    }
	    TelefonosDefault telefonos = new TelefonosDefault(new TelefonoDAO(), id);
	    Persona persona = new Persona(id, nombrePersona, telefonos);
	    return persona;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

}
