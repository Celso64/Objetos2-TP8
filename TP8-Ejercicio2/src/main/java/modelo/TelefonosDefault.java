package modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import almacenamiento.TelefonoDAO;

public class TelefonosDefault implements Set<Telefono> {

    private Set<Telefono> telefonos;
    private TelefonoDAO telefonoDAO;
    private Integer idDePersona;

    public TelefonosDefault(TelefonoDAO telefonoDAO, Integer idPersona) {
	this.telefonos = new HashSet<>();
	this.telefonoDAO = telefonoDAO;
	this.idDePersona = idPersona;
    }

    private Set<Telefono> recuperarTelefonos() {
	return this.telefonos = this.telefonoDAO.findByrPersonaId(this.idDePersona);
    }

    @Override
    public int size() {
	return this.telefonos.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
	return (T[]) this.recuperarTelefonos().toArray();
    }

    // Todo estos no importan para este caso.
    @Override
    public boolean isEmpty() {
	return false;
    }

    @Override
    public boolean contains(Object o) {
	return false;
    }

    @Override
    public Iterator<Telefono> iterator() {
	return null;
    }

    @Override
    public Object[] toArray() {
	return this.recuperarTelefonos().toArray();
    }

    @Override
    public boolean add(Telefono e) {
	return false;
    }

    @Override
    public boolean remove(Object o) {
	return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
	return false;
    }

    @Override
    public boolean addAll(Collection<? extends Telefono> c) {
	return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
	return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
	return false;
    }

    @Override
    public void clear() {
    }

}
