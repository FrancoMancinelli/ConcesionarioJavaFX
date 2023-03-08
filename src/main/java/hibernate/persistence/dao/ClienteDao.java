package hibernate.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.ClienteDaoInterface;
import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Mecanico;

public class ClienteDao extends ACommonDao<Cliente> implements ClienteDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public ClienteDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listarTodos() {
		// Verificaciï¿½n de sesiï¿½n abierta
				if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
					session.getTransaction().begin();
				}
				
				return (List<Cliente>) session.createQuery("FROM Cliente").list();
					
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscarPorNombre(String nombre) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Devuelve los clientes con ese nombre
		return (List<Cliente>) session.createQuery("FROM Cliente WHERE nombre='" + nombre + "'").list();
	}

	@Override
	public Cliente buscarPorId(int id) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Cliente) session.createQuery("FROM Cliente WHERE id =" + id).uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> traerListaClientes() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		List<Cliente> listaClientes = (List<Cliente>) session.createQuery("FROM Cliente").list();
				
		// Devuelve los clientes con ese nombre
		return listaClientes;
	}

}
