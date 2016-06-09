package Product.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.tree.SelectExpression;

import hibernate.util.HibernateUtil;

public class ProductHibernateDAO implements ProductDAO {

	public static void main(String[] args) {
		//
		// List<ProductBean> list=select();
		//
		// for(ProductBean a:list){
		// System.out.println(a);
		// }

	}
	private SessionFactory sessionFactory;
	
	public ProductHibernateDAO(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
		
	}
	

	public ProductBean select(int id) {
		ProductBean bean = null;
		Session session = this.getSession();
		
			
	bean = (ProductBean) session.load(ProductBean.class, id);
				
		return bean;
	}

	@Override
	public List<ProductBean> select() {
		List<ProductBean> list = null;
		Session session = this.getSession();

		
			Query query = session.createQuery("from ProductBean order by id");
			list = query.list();
		

		return list;

	}

	@Override
	public ProductBean insert(ProductBean bean) {
	Session session =this.getSession();
	
		session.save(bean);
		return bean;
	}

	@Override
	public ProductBean update(String name, double price, Date make, int expire, int id) {
		Session session= this.getSession();
		
	
		
			ProductBean bean=(ProductBean)session.get(ProductBean.class, id);		
			bean.setName(name);
			bean.setPrice(price);
			bean.setExpire(expire);
			bean.setMake(make);
			session.getTransaction().commit();
		
		return null;
	}

	@Override
	public boolean delete(int id) {
		
	Session session =this.getSession();
		
	
			ProductBean bean =(ProductBean)session.get(ProductBean.class,id);
			session.delete(bean);
			
		
		return false;
	}

}
