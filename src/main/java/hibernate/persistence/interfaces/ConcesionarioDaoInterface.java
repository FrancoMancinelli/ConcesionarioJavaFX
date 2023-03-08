package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Concesionario;

public interface ConcesionarioDaoInterface extends ACommonDaoInterface<Concesionario>{
	
	/**
	 * Devuelve los clientes que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los clientes que queremos obtener de la base de datos
	 * @return Clientes que contienen ese name
	 */
	public List<Concesionario> buscarPorNombre(final String nombre);
	
	/**
	 * Devuelve el Concesionario que tiene ese id en la base de datos
	 * 
	 * @param id, identificador del Concesionario
	 * @return Concesionario que contienen ese id
	 */
	public Concesionario buscarPorId(final int id);

}