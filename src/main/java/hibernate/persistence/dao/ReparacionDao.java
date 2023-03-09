package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.PropuestaDaoInterface;
import hibernate.persistence.interfaces.ReparacionDaoInterface;
import hibernate.persistence.models.Concesionario;
import hibernate.persistence.models.Propuesta;
import hibernate.persistence.models.Reparacion;

public class ReparacionDao extends ACommonDao<Reparacion> implements ReparacionDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public ReparacionDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reparacion> buscarPorVehiculo(int id_vehiculo) {
		// Verificaciï¿½n de sesiï¿½n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (List<Reparacion>) session.createQuery("FROM Reparacion WHERE id_vehiculo='" + id_vehiculo + "'").list();

	}

	@Override
	public Reparacion buscarPorId(int id) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Reparacion) session.createQuery("FROM Reparacion WHERE id =" + id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reparacion> getReparacionesPendientes() {
		// Verificaciï¿½n de sesiï¿½n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (List<Reparacion>) session.createQuery("FROM Reparacion WHERE fechaFin IS NULL").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reparacion> listarTodos() {
		// Verificaciï¿½n de sesiï¿½n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (List<Reparacion>) session.createQuery("FROM Reparacion").list();
	}

}
