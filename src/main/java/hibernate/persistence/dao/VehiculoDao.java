package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.ReparacionDaoInterface;
import hibernate.persistence.interfaces.VehiculoDaoInterface;
import hibernate.persistence.models.Concesionario;
import hibernate.persistence.models.Reparacion;
import hibernate.persistence.models.Vehiculo;

public class VehiculoDao extends ACommonDao<Vehiculo> implements VehiculoDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public VehiculoDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> buscarPorModelo(String modelo) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Vehiculo>) session.createQuery("FROM Vehiculo WHERE modelo='" + modelo + "'").list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> buscarPorConcesionario(int id_concesionario) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Vehiculo>) session.createQuery("FROM Vehiculo WHERE id_concesionario='" + id_concesionario + "'")
				.list();
	}

	@Override
	public Vehiculo buscarPorId(int id) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Vehiculo) session.createQuery("FROM Vehiculo WHERE id ='" + id + "'").uniqueResult();
	}

	@Override
	public Vehiculo traerUltimoVehiculo() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		Vehiculo vehiculo = (Vehiculo) session.createQuery("FROM Vehiculo ORDER BY id DESC").setMaxResults(1)
				.uniqueResult();
		return vehiculo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> listarTodos() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (List<Vehiculo>) session.createQuery("FROM Vehiculo").list();
	}

}
