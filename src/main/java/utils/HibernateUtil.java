package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;

public class HibernateUtil {

	/*
	 * ATRIBUTOS
	 */
	private static SessionFactory sessionFactory;
	private static Session session;

	/*
	 * METODOS
	 */
	/**
	 * Metodo que abre la sesion con la clase Session
	 * @return sesion abierta
	 */
	public static Session getSession() {
		if (sessionFactory == null) {
			session = getSessionFactory().openSession();
			System.out.println("CONCECTADO");
		}
		return session;
	}

	/**
	 * Metodo que cierra la sesion si existe y llama al metodo sesion factory
	 */
	public static void closeSession() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if (session != null) {
			session.close();
		}
		closeSessionFactory();
	}

	/**
	 * Metodo que, por regla general, es llamado por el closeSesion() para
	 * cerrar la sessionFactory con la condicion de que no sea nula y la session 
	 * ya este cerrada
	 */
	public static void closeSessionFactory() {
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
		

	}

	/**
	 * Metodo que devuelve una sesion generada a partir de la session factory
	 * si esta es nula la crea
	 * @return sesion creado
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
			sessionFactory = new MetadataSources(ssr).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}

}
