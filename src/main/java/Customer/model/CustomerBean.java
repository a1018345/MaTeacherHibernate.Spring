package Customer.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class CustomerBean implements Serializable{
	private String custid;
	private byte[] password;
	private String email;
	private Date birth;
	
	@Override//不能直接印出物件，故需呼叫toString()方法來得到回傳的物件描述
	public String toString(){
		return "CustomerBean custid:"+custid+",password:"+Arrays.toString(password)+",email:"+email+",birth:"+birth;
	}
	 
	
	
	
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] passsword) {
		this.password = passsword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	
}
