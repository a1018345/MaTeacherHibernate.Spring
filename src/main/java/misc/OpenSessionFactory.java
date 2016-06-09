package misc;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import hibernate.util.HibernateUtil;

public class OpenSessionFactory implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	
		HibernateUtil.closeSessionFactory();
	}

}
