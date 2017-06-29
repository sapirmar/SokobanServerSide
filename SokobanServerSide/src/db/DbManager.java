package db;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DbManager<V> {
	private SessionFactory factory;

	public DbManager() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);// להעלים כיתוב
																	// מיותר
																	// באדום
		Configuration config = new Configuration();
		config.configure();
		// קורא את האסקמל ומתוך זה אפשר להשתמש בסשנים
		factory = config.buildSessionFactory();
	}

	public void add(V v) {
		Transaction tx = null;
		Session session = null;
		try {
			session = factory.openSession();// לכל פעולה לפתוח טרנזקשיין
			tx = session.beginTransaction();
			session.save(v);
			tx.commit();// חייב בסוף כל פעולה

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();// להחזיר למצב הקודם
		} finally {
			if (session != null) {

				session.close();
			}

		}
	}

	public void update(V v) {

	}

	public void delete(V v) {
		Transaction tx = null;
		Session session = null;
		try {
			session = factory.openSession();// לכל פעולה לפתוח טרנזקשיין
			tx = session.beginTransaction();
			session.delete(v);
			tx.commit();// חייב בסוף כל פעולה

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();// להחזיר למצב הקודם
		} finally {
			if (session != null) {

				session.close();
			}

		}

	}

	public List<V> sort_top(String sorter, String table, int num) {
		Session session = factory.openSession();
		Query query = session.createQuery("FROM "+ table + " ORDER BY " +sorter);
		query.setMaxResults(num);
		List<V> list = query.list();
		session.close();
		return list;
	}

	public List<V> if_exist(String table, String column, String parameter) {
		Session session = factory.openSession();
		
		Query query = session.createQuery("FROM " + table + " WHERE " + column + " LIKE " + "'" + parameter + "'");
		List<V> list = query.list();
		session.close();

		return list;

	}

	public List<V> show_all_table(String table)
	{
		Session session = factory.openSession();
		Query query = session.createQuery("FROM " + table);
		List<V> list= query.list();
		session.close();
		return list;

	}
	public List<V> deleteAll()
	{
		Session session = factory.openSession();
		Query query = session.createQuery("DELETE FROM Records WHERE LevelID = Level1");
		List<V> list = query.list();
		session.close();

		return list;
	}
}
