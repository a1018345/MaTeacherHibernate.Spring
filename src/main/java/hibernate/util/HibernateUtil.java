package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateUtil {

	
	private static SessionFactory sessionFactory=null;
//	private static final ServiceRegistry serviceRegistry;
	
	static{
		
	
//		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		
//		Hibernate 4.3後被deprecated
//		Configuration configuration=new Configuration();
//		configuration.configure();
//		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		
//		Hibernate4.3後
//		Configuration configuration=new Configuration();
//		configuration.configure();
//		serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		
		try{
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
		applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		}catch(Throwable t){
//			StandardServiceRegistryBuilder.destroy(sessionFactory);
			System.err.println("SessionFactoryError:"+t.getStackTrace());;
			
		}
	}
	
	
	
	public static SessionFactory getSessionFactory(){
		
		return sessionFactory;
	}
	
	
	public static void closeSessionFactory(){
		if(sessionFactory!=null){
			sessionFactory.close();
			
		}
		
	}
	
	
}
