package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.EspecialidadDaoInterface;
import hibernate.persistence.models.Especialidad;

public class EspecialidadDao extends ACommonDao<Especialidad> implements EspecialidadDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public EspecialidadDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Especialidad> buscarPorDescripcion(String descripcion) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Especialidad>) session.createQuery("FROM Especialidad WHERE descripcion='" + descripcion + "'")
				.list();

	}

	@Override
	public Especialidad buscarPorId(int id) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Especialidad) session.createQuery("FROM Especialidad WHERE id ='" + id + "'").uniqueResult();

	}

}
