package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.VendedorDaoInterface;
import hibernate.persistence.models.Vendedor;

public class VendedorDao extends ACommonDao<Vendedor> implements VendedorDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public VendedorDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendedor> buscarPorNombre(String nombre) {
		// Verificaci�n de sesi�n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Vendedor>) session.createQuery("FROM Vendedor WHERE nombre='" + nombre + "'").list();

	}

	@Override
	public Vendedor buscarPorId(int id) {
	    // Verificación de sesión abierta
	    if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
	        session.getTransaction().begin();
	    }

	    Query query = session.createQuery("FROM Vendedor WHERE id ='" + id + "'");
	    Vendedor vendedor = (Vendedor) query.uniqueResult();

	    return vendedor;
	}


}
