package Customer.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.experimental.theories.Theories;

import hibernate.util.HibernateUtil;

public class CustomerHibernateDAO {

	static final String SELECT_ALL = "from as CustomerBean order by CustomerBean.custid";
	static CustomerBean bean;

	// public static void main(String[]args){
	//
	// CustomerBean bean2=select("Alex");
	// System.out.println(bean2);
	// }
	SessionFactory sessionFactory;
	// public void CustomerHibernateDAO(){
	// this.sessionFactory=HibernateUtil.getSessionFactory();
	// }

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public CustomerHibernateDAO(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
	}

	public CustomerBean select(String custid) {
		Session session = getSession();

		try {
			session.beginTransaction();

			bean = (CustomerBean) session.get(CustomerBean.class, custid);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		System.out.println(bean);
		return bean;
	}

	public boolean update(byte[] password, String email, Date birth, String custid) {
		boolean result = false;

		Session session = this.getSession();

		try {
			session.beginTransaction();
			bean = new CustomerBean();
			bean.setCustid(custid);
			bean.setPassword(password);
			bean.setBirth(birth);
			bean.setEmail(email);
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return result;
	}

	public boolean insert(String custid, byte[] psword, String email, Date birth) {
		boolean result = false;
		Session session = getSession();

		try {
			session.beginTransaction();
			bean = new CustomerBean();

			bean.setBirth(birth);
			bean.setEmail(email);
			bean.setPassword(psword);
			session.saveOrUpdate(bean);
			result = true;
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return result;
	}

	public boolean delete(String custid) {
		boolean result = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (CustomerBean) session.get(CustomerBean.class, custid);
			session.delete(bean);
			result = true;
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public List<CustomerBean> select_All() {
		List<CustomerBean> list = null;
		Session session = getSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery(SELECT_ALL);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return list;
	}

}
