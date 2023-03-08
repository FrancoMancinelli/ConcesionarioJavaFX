package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Director;
import hibernate.persistence.models.Propuesta;
import hibernate.persistence.models.Reparacion;
import hibernate.persistence.models.Vehiculo;

public interface ReparacionDaoInterface extends ACommonDaoInterface<Reparacion> {

	/**
	 * Devuelve las Reparaciones que tienen ese vehiculo en la base de datos
	 * 
	 * @param id, identificador del vehiculo
	 * @return Reparacion que contienen vehiculo
	 */
	public List<Reparacion> buscarPorVehiculo(final int id_vehiculo);

	/**
	 * Devuelve la reparacion que tiene ese id en la base de datos
	 * 
	 * @param id, de la Reparacion que queremos obtener de la base de datos
	 * @return Reparacion que contienen ese id
	 */
	public Reparacion buscarPorId(final int id);

	/**
	 * 
	 * @return
	 */
	public List<Reparacion> getReparacionesPendientes();

	/**
	 * 
	 * @return
	 */
	public List<Reparacion> listarTodos();

}
