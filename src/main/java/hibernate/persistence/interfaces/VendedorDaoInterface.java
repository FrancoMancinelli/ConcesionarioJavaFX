package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Director;
import hibernate.persistence.models.Vendedor;

public interface VendedorDaoInterface extends ACommonDaoInterface <Vendedor>{
	
	/**
	 * Devuelve los Vendedor que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los Vendedores que queremos obtener de la base de datos
	 * @return Vendedor que contienen ese name
	 */
	public List<Vendedor> buscarPorNombre(final String nombre);
	
	/**
	 * Devuelve el Vendedor que tiene ese id en la base de datos
	 * 
	 * @param id, identificador del Vendedor
	 * @return Vendedor que contienen ese id
	 */
	public Vendedor buscarPorId(final int dni);

}
