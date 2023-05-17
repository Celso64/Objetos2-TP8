package main;

import almacenamiento.PersonaDAO;
import modelo.Persona;
import modelo.Telefono;

public class App {

    public static void main(String[] args) {

	PersonaDAO dao = new PersonaDAO();
	Persona p = dao.personaPorId(1);
	System.out.println(p.nombre());
	for (Telefono telefono : p.telefonos()) {
	    System.out.println(telefono);
	}
    }

}
