package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Mecanico;

public interface MecanicoDaoInterface extends ACommonDaoInterface <Mecanico>{

	/**
	 * Devuelve los clientes que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los clientes que queremos obtener de la base de datos
	 * @return Clientes que contienen ese name
	 */
	public List<Mecanico> buscarPorNombre(final String nombre);
	
	/**
	 * Devuelve el Mecanico que tiene ese id en la base de datos
	 * 
	 * @param id, identificador del Mecanico
	 * @return Mecanico que contienen ese id
	 */
	public Mecanico buscarPorId(final int dni);

	/**
	 * Devuelve una lista con todos los mecánicos disponibles
	 * @return List de Mecanico
	 */
	public List<Mecanico> listarTodos();
}
