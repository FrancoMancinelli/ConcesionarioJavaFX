package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.EmpleadoDaoInterface;
import hibernate.persistence.models.Concesionario;
import hibernate.persistence.models.Director;
import hibernate.persistence.models.Empleado;
import hibernate.persistence.models.Vendedor;

public class EmpleadoDao extends ACommonDao<Empleado> implements EmpleadoDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public EmpleadoDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> listarTodos() {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}
		return (List<Empleado>) session.createQuery("FROM Empleado").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> buscarPorNombre(String nombre) {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Empleado>) session.createQuery("FROM Empleado WHERE nombre='" + nombre + "'").list();

	}

	@Override
	public Empleado buscarPorDni(String dni) {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Empleado) session.createQuery("FROM Empleado WHERE dni ='" + dni + "'");
	}

	@Override
	public Empleado buscarPorId(int id) {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Empleado) session.createQuery("FROM Empleado WHERE id ='" + id + "'");
	}

	@Override
	public Empleado buscarPorUserAndPass(String username, String password) {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		Empleado empleado = null;
		Query query = session
				.createQuery("FROM Empleado WHERE username = '" + username + "' AND password = '" + password + "'");
		empleado = (Empleado) query.uniqueResult();

		return empleado;
	}

	@Override
	public boolean esDirector(int id) {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		Director director = null;

		director = (Director) session.createQuery("FROM Director WHERE id = '" + id + "'").uniqueResult();

		if (director != null && director.getId() == id) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean esVendedor(int id) {
	    // Verificación de sesión abierta
	    if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
	        session.getTransaction().begin();
	    }

	    Vendedor vendedor = session.get(Vendedor.class, id);

	    if (vendedor != null && vendedor.getEmpleado().getId() == id) {
	        return true;
	    } else {
	        return false;
	    }
	}



	@Override
	public void registrarEmpleado(String nombre, String apellidos, String password, String dni, int telefono,
			String direccion, Concesionario concesionario) {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		String username = nombre + apellidos.charAt(0);

		// Insercci�n.
		session.save(new Empleado(dni, concesionario, nombre, apellidos, direccion, telefono, username, password));
		session.flush();

		// Commit.
		session.getTransaction().commit();
	}
}
