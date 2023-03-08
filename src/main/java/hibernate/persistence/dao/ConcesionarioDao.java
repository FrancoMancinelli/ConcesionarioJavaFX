package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.ConcesionarioDaoInterface;
import hibernate.persistence.models.Concesionario;

public class ConcesionarioDao extends ACommonDao<Concesionario> implements ConcesionarioDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public ConcesionarioDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Concesionario> buscarPorNombre(String nombre) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Concesionario>) session.createQuery("FROM Concesionario WHERE nombre='" + nombre + "'").list();
	}

	@Override
	public Concesionario buscarPorId(int id) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Concesionario) session.createQuery("FROM Concesionario WHERE id ='" + id + "'") .uniqueResult();

	}

}
