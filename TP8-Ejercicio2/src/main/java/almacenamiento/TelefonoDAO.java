package almacenamiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import modelo.Telefono;

public class TelefonoDAO {

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

    public Set<Telefono> findByrPersonaId(int personaId) {

	Set<Telefono> telefonos = new HashSet<>();

	String sql = "select numero from telefonos t where t.idPersona = ?";
	try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
	    statement.setInt(1, personaId);

	    ResultSet result = statement.executeQuery();
	    while (result.next()) {
		telefonos.add(new Telefono(result.getString(0)));
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

	return telefonos;
    }

    public Set<Telefono> findByrPersonaNombre(String personaNombre) {

	Set<Telefono> telefonos = new HashSet<>();

	String sql = "select t.numero from telefonos t where t.idPersona in (select p.id from personas p where p.nombre = ?)";

	try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
	    statement.setString(1, personaNombre);

	    ResultSet result = statement.executeQuery();
	    while (result.next()) {
		telefonos.add(new Telefono(result.getString(1)));
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e.getMessage());
	}

	return telefonos;
    }

}
