package hibernate.persistence.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import hibernate.persistence.interfaces.PropuestaDaoInterface;
import hibernate.persistence.models.Propuesta;

public class PropuestaDao extends ACommonDao<Propuesta> implements PropuestaDaoInterface {

	// Session de la base de datos
	private Session session;

	/**
	 * Constructor cliente DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public PropuestaDao(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> buscarPorVehiculo(int id_vehiculo) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (List<Propuesta>) session.createQuery("FROM Propuesta WHERE id_vehiculo='" + id_vehiculo + "'").list();

	}

	@Override
	public Propuesta buscarPorId(int id) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		return (Propuesta) session.createQuery("FROM Propuesta WHERE id ='" + id + "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> listarTodos() {
		// VerificaciÃ³n de sesiÃ³n abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}
		return (List<Propuesta>) session.createQuery("FROM Propuesta").list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> listarVendidos() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}
		return (List<Propuesta>) session.createQuery("FROM Propuesta WHERE vendido = 1").list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> listarVendidosUltimoAno() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Obtener la fecha actual y restar un aÃ±o
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		Date fechaHaceUnAno = calendar.getTime();

		// Ejecutar la consulta para obtener las propuestas vendidas en el Ãºltimo aÃ±o
		return (List<Propuesta>) session.createQuery("FROM Propuesta WHERE vendido = 1 AND fecha >= :fecha")
				.setParameter("fecha", fechaHaceUnAno).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> listarVendidosMesAnterior() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Obtener la fecha actual y el primer dÃ­a del mes anterior
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date primerDiaDelMesAnterior = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date ultimoDiaDelMesAnterior = calendar.getTime();

		// Ejecutar la consulta para obtener las propuestas vendidas el mes anterior
		return (List<Propuesta>) session.createQuery(
				"FROM Propuesta WHERE vendido = 1 AND fecha BETWEEN :primerDiaDelMesAnterior AND :ultimoDiaDelMesAnterior")
				.setParameter("primerDiaDelMesAnterior", primerDiaDelMesAnterior)
				.setParameter("ultimoDiaDelMesAnterior", ultimoDiaDelMesAnterior).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> listarVendidosSemanaPasada() {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}

		// Obtener la fecha actual y el primer dÃ­a de la semana anterior
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		Date primerDiaDeLaSemana = calendar.getTime();
		Date fechaFinalSemanaPasada = new Date(primerDiaDeLaSemana.getTime() + (7 * 24 * 60 * 60 * 1000) - 1);

		// Ejecutar la consulta para obtener las propuestas vendidas la semana pasada
		return (List<Propuesta>) session.createQuery(
				"FROM Propuesta WHERE vendido = 1 AND fecha BETWEEN :primerDiaDeLaSemana AND :fechaFinalSemanaPasada")
				.setParameter("primerDiaDeLaSemana", primerDiaDeLaSemana)
				.setParameter("fechaFinalSemanaPasada", fechaFinalSemanaPasada).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> listarBuscados(String busqueda) {
		// Verificación de sesión abierta
		if (session.getTransaction().getStatus() != TransactionStatus.ACTIVE) {
			session.getTransaction().begin();
		}
		return (List<Propuesta>) session.createQuery("FROM Propuesta WHERE vendido = 1 AND ").list();

	}

}
