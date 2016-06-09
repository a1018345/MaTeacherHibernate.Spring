package BussinessService;

import java.util.Arrays;

import Customer.model.CustomerBean;

import Customer.model.CustomerHibernateDAO;

public class CustomerService {
	CustomerHibernateDAO dao;
	
	
	
	
	public CustomerService(CustomerHibernateDAO dao) {
	
		this.dao = dao;
	}

	public static void main(String[] args) {

//		CustomerService customerServer=new CustomerService();//測試登入
//		CustomerBean bean=customerServer.Login("alex", "B");
//		System.out.print(bean);
		
//		customerServer.CheangPassword("alex", "B", "A");//測試改密碼
		
		
	}

	public CustomerBean Login(String userName, String password) {
		CustomerBean bean = dao.select(userName);
		if (userName != null) {
			if (password != null && password.length() != 0) {
				byte[] pass = bean.getPassword();
				byte[] temp = password.getBytes();
				if (Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}

	public boolean CheangPassword(String userName, String oldPassword, String newPassword) {
		CustomerBean bean=this.Login(userName,oldPassword);
		if(bean!=null){
			byte[] temp=newPassword.getBytes();
			return dao.update(temp,bean.getEmail(),bean.getBirth(),userName);	
		}
		return false;
	}

}
