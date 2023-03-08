package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Director;
import hibernate.persistence.models.Vehiculo;

public interface VehiculoDaoInterface extends ACommonDaoInterface<Vehiculo> {

	/**
	 * Devuelve el Vehiculo que tiene ese id en la base de datos
	 * 
	 * @param id del Vehiculo que queremos obtener de la base de datos
	 * @return Vehiculo que contienen ese name
	 */
	public Vehiculo buscarPorId(final int id);

	/**
	 * Devuelve los Vehiculos que tienen ese modelo en la base de datos
	 * 
	 * @param modelo, modelo del Director
	 * @return Vehiculos que tienen ese modelo
	 */
	public List<Vehiculo> buscarPorModelo(final String modelo);

	/**
	 * Devuelve los Vehiculos que tiene ese concesionario en la base de datos
	 * 
	 * @param id_concesionario, concesionario al que pertenece el vehiculo
	 * @return Vehiculos que tiene ese concesionario
	 */
	public List<Vehiculo> buscarPorConcesionario(final int id_concesionario);

	/**
	 * 
	 * @return
	 */
	public Vehiculo traerUltimoVehiculo();

	/**
	 * 
	 * @return
	 */
	public List<Vehiculo> listarTodos();

}
