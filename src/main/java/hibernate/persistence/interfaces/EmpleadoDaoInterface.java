package hibernate.persistence.interfaces;

import java.util.List;

import hibernate.persistence.models.Concesionario;
import hibernate.persistence.models.Director;
import hibernate.persistence.models.Empleado;

public interface EmpleadoDaoInterface extends ACommonDaoInterface<Empleado>{
	
	/**
	 * Devuelve los clientes que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los clientes que queremos obtener de la base de datos
	 * @return Clientes que contienen ese name
	 */
	public List<Empleado> buscarPorNombre(final String nombre);

	/**
	 * Devuelve el Empleado que tiene ese dni en la base de datos
	 * 
	 * @param dni, identificador del Empleado
	 * @return Empleado que contienen ese id
	 */
	public Empleado buscarPorDni(final String dni);
	
	/**
	 * Comprueba si la contraseña de el usuario es correcta
	 * 
	 * @param username, del Empleado
	 * @return true si la contraseña es  correcta
	 */
	public Empleado buscarPorUserAndPass(final String username, final String password);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Empleado buscarPorId(int id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean esDirector(int id);

	/**
	 * 
	 * REVISAR CREO QUE SERÍA MAS SENCILLO METER DIRECTAMENTE EL OBJETO
	 * 
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param password
	 * @param dni
	 * @param telefono
	 * @param direccion
	 * @param concesionario
	 */
	public void registrarEmpleado(String nombre, String apellidos, String password, String dni, int telefono, String direccion,
			Concesionario concesionario);

	/**
	 * 
	 * @return
	 */
	public List<Empleado> listarTodos();

	boolean esVendedor(int id);

}
