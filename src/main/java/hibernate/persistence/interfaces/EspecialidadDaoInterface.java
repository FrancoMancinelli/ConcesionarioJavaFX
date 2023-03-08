package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Director;
import hibernate.persistence.models.Especialidad;

public interface EspecialidadDaoInterface extends ACommonDaoInterface <Especialidad>{
	
	/**
	 * Devuelve los clientes que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los clientes que queremos obtener de la base de datos
	 * @return Clientes que contienen ese name
	 */
	public List<Especialidad> buscarPorDescripcion(final String descripcion);
	
	/**
	 * Devuelve el Especialidad que tiene ese id en la base de datos
	 * 
	 * @param id, identificador del Especialidad
	 * @return Especialidad que contienen ese id
	 */
	public Especialidad buscarPorId(final int id);

}
