package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Concesionario;
import hibernate.persistence.models.Director;

public interface DirectorDaoInterface extends ACommonDaoInterface<Director>{
	
	/**
	 * Devuelve los clientes que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los clientes que queremos obtener de la base de datos
	 * @return Clientes que contienen ese name
	 */
	public List<Director> buscarPorNombre(final String nombre);
	
	/**
	 * Devuelve el Director que tiene ese id en la base de datos
	 * 
	 * @param id, identificador del Director
	 * @return Director que contienen ese id
	 */
	public Director buscarPorId(final int dni);

}
