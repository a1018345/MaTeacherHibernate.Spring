/**
 * 
 */
package Customer.model;

import java.util.List;

/**
 * @author lanyao
 *
 */
public interface CustomerDAO {

	CustomerBean select(String custid);

	boolean update(byte[] password, String email, java.util.Date birth, String custid);
	
	 boolean insert(String custid,byte[] psword,String email,java.util.Date birth);
	 
	 boolean delete(String custid);
	 
	 List<CustomerBean>  select_All();
	
}
