package hibernate.persistence.interfaces;

import java.util.List;

public interface ACommonDaoInterface <T> {
	
	/**
	 * Inserta un objeto a la base de datos
	 * 
	 * @param paramT Objeto que queremos insertar en la base de datos
	 */
	public void insert(final T paramT);

	/**
	 * Actualiza un objeto de la base de datos
	 * 
	 * @param paramT Objeto que queremos actualizar
	 */
	public void update(final T paramT);

	/**
	 * Elimina un objeto de la base de datos
	 * 
	 * @param paramT Objeto de la base de datos que queremos eliminar
	 */
	public void delete(final T paramT);

	/**
	 * Localiza un objeto por su ID de la base de datos	
	 * 
	 * @param id ID del objeto que queremos buscar
	 * @return Objeto con esa ID
	 */
	public T searchById(final int id);

	/**
	 * Devuelve todos los objetos de la base de datos
	 * 
	 * @return List<T> List con todos los objetos de las base de datos
	 */
	public List<T> searchAll();

}
