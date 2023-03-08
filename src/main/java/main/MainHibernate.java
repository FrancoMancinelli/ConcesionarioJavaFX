package main;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.persistence.dao.ClienteDao;
import hibernate.persistence.dao.ConcesionarioDao;
import hibernate.persistence.dao.DirectorDao;
import hibernate.persistence.dao.EmpleadoDao;
import hibernate.persistence.models.Cliente;
import hibernate.persistence.models.Concesionario;
import hibernate.persistence.models.Director;
import hibernate.persistence.models.Empleado;
import hibernate.persistence.models.Mecanico;
import hibernate.persistence.models.Propuesta;
import utils.HibernateUtil;

public class MainHibernate {

	public static void main(String[] args) {

		final Session session = HibernateUtil.getSessionFactory().openSession();

		Query query = session.createQuery(
				"FROM Mecanico WHERE id =" + 3);
		
		Mecanico mecanico = (Mecanico) query.uniqueResult();

	
			System.out.println(mecanico);
		
		
		
		session.close();
	}

}
