package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Director;
import hibernate.persistence.models.Propuesta;
import hibernate.persistence.models.Reparacion;

public interface PropuestaDaoInterface extends ACommonDaoInterface <Propuesta>{
	
	
	/**
	 * Devuelve las Propuestas que tienen ese vehiculo en la base de datos
	 * 
	 * @param id, identificador del vehiculo
	 * @return Propuestas que contienen ese vehiculo
	 */
	public List<Propuesta> buscarPorVehiculo(final int id);
	
	/**
	 * Devuelve la Propuesta que tiene ese id en la base de datos
	 * 
	 * @param id, de la Propuesta que queremos obtener de la base de datos
	 * @return Propuesta que contienen ese id
	 */
	public Propuesta buscarPorId(final int id);

	/**
	 * 
	 * @return
	 */
	public List<Propuesta> listarVendidos();

	/**
	 * 
	 * @return
	 */
	public List<Propuesta> listarTodos();

	/**
	 * 
	 * @return
	 */
	public List<Propuesta> listarVendidosUltimoAno();

	/**
	 * 
	 * @return
	 */
	public List<Propuesta> listarVendidosMesAnterior();

	/**
	 * 
	 * @return
	 */
	public List<Propuesta> listarVendidosSemanaPasada();

	/**
	 * 
	 * @return
	 */
	public List<Propuesta> listarBuscados(String busqueda);

}
