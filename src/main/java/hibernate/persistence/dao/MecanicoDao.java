package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.MecanicoDaoInterface;
import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Mecanico;

public class MecanicoDao extends ACommonDao<Mecanico> implements MecanicoDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public MecanicoDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> buscarPorNombre(String nombre) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Mecanico>) session.createQuery("FROM Mecanico WHERE nombre='" + nombre + "'").list();

	}


	@Override
	public Mecanico buscarPorId(int id) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Mecanico) session.createQuery("FROM Mecanico WHERE id = " + id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> listarTodos() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}
		List<Mecanico> listaMecanicos = (List<Mecanico>) session.createQuery("FROM Mecanico").list();

		return listaMecanicos;
	}

}
