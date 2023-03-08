package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Cliente;

public interface ClienteDaoInterface extends ACommonDaoInterface<Cliente>{
	
	/**
	 * Devuelve los clientes que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los clientes que queremos obtener de la base de datos
	 * @return Clientes que contienen ese name
	 */
	public List<Cliente> buscarPorNombre(final String nombre);
	
	/**
	 * Devuelve el Cliente que tiene ese id en la base de datos
	 * 
	 * @param id, identificador del Cliente
	 * @return Cliente que contienen ese id
	 */
	public Cliente buscarPorId(final int dni);
	
	/**
	 * Devuelve la lista de Clientes actuales
	 * @return lista de Clientes
	 */
	public List<Cliente> traerListaClientes();

	/**
	 * 
	 * @return
	 */
	public List<Cliente> listarTodos();

}