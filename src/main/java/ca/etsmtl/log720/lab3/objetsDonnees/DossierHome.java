package ca.etsmtl.log720.lab3.objetsDonnees;

// Generated 2014-12-01 02:55:37 by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Dossier.
 * @see ca.etsmtl.log720.lab3.objetsDonnees.Dossier
 * @author Hibernate Tools
 */
public class DossierHome {

	private static final Log log = LogFactory.getLog(DossierHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("hibernate_log720_lab3");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Dossier transientInstance) {
		log.debug("persisting Dossier instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Dossier instance) {
		log.debug("attaching dirty Dossier instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dossier instance) {
		log.debug("attaching clean Dossier instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Dossier persistentInstance) {
		log.debug("deleting Dossier instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dossier merge(Dossier detachedInstance) {
		log.debug("merging Dossier instance");
		try {
			Dossier result = (Dossier) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dossier findById(int id) {
		log.debug("getting Dossier instance with id: " + id);
		try {
			Dossier instance = (Dossier) sessionFactory.getCurrentSession()
					.get("ca.etsmtl.log720.lab3.objetsDonnees.Dossier", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Dossier instance) {
		log.debug("finding Dossier instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"ca.etsmtl.log720.lab3.objetsDonnees.Dossier")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
