package Product.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.xml.ws.Response;

import BussinessService.ProductService;
import Customer.model.CustomerBean;

import Product.model.ProductBean;
import Product.model.ProductHibernateDAO;
import hibernate.util.HibernateUtil;


@WebServlet("/pages/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductBean bean = new ProductBean();
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	List<ProductBean> list = null;
	ProductService service;
	@Override
	public void init() throws ServletException {
		service=new ProductService(new ProductHibernateDAO(HibernateUtil.getSessionFactory()));
	
	}
	
	
@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//1.接收2.轉換3.驗證4.呼叫5.呼叫Model, 根據Model執行結果顯示View
		String temp1=request.getParameter("id");
		String name=request.getParameter("name");
		String temp2=request.getParameter("price");
		String temp3=request.getParameter("make");
		String temp4=request.getParameter("expire");
		String prodaction=request.getParameter("prodaction");
		
		Map<String,String> error=new HashMap<String,String>();
		request.setAttribute("error", error);
		
		
		double price=0;
		if(temp2!=null&&temp2.trim().length()!=0){
			try{
			price=Double.parseDouble(temp2);
			}catch(NumberFormatException n){
				error.put("error", "必須是數字");
			}
			
		}
		
		java.util.Date make=null;
		if(temp3!=null&&temp3.length()!=0){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			try {
				make=sdf.parse(temp3);
			} catch (ParseException e) {
				error.put("make","日期格式必須是yyyy/mm/dd");
				e.printStackTrace();
			}
		}
			
		
		
		int expire=0;
		if(temp4!=null&&temp4.trim().length()!=0){
			try{
			expire=Integer.parseInt(temp4);
			}catch(NumberFormatException e){
				e.printStackTrace();
				error.put("expire","exprie需是整數");
			}
		}
			
		
		
		
//		if("Select".equals(prodaction)||prodaction.equals("Insert")||"Delete".equals(prodaction)||
//				prodaction.equals("Update")){
//			if(temp1==null&&temp1.trim().length()==0){
//				error.put("id","請輸入ID");
//			}
//		}
		int id=0;
		

		
		if(!error.isEmpty()&&error!=null){
			request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			return;
		}
		bean.setId(id);
		bean.setName(name);
		bean.setPrice(price);
		bean.setExpire(expire);
		bean.setMake(make);
		
		
		if(prodaction.equals("Select")){
			if(temp1!=null&&temp1.trim().length()!=0){
				 id=Integer.parseInt(temp1);
			}else{
				error.put("id","必須是整數");
			}
			bean.setId(id);
			List<ProductBean> list=service.select(bean);
			
			request.setAttribute("product",list);
			request.getRequestDispatcher("/pages/display.jsp").forward(request,response);	
			return;
		}else if(prodaction.equals("Insert")){
			
			if(temp1!=null&&temp1.trim().length()!=0){
				 id=Integer.parseInt(temp1);
			}else{
				error.put("id","必須是整數");
			}
			bean.setId(id);
			System.out.println("insertID:"+bean.getId());
			System.out.println("id:"+bean.getId());
			System.out.println("name"+bean.getName());
			System.out.println("prices:"+bean.getPrice());
			System.out.println("make"+bean.getMake());
			System.out.println("expire"+bean.getExpire());
			
			service.insert(bean);
		
			
			
		}else if(prodaction.equals("Delete")){
			
			if(temp1!=null&&temp1.trim().length()!=0){
				 id=Integer.parseInt(temp1);
			}else{
				error.put("id","必須是整數");
			}
			bean.setId(id);
			service.delete(bean);
			
			
		
		}else if(prodaction.equals("Update")){
			
			if(temp1!=null&&temp1.trim().length()!=0){
				 id=Integer.parseInt(temp1);
			}else{
				error.put("id","必須是整數");
			}
			bean.setId(id);
			service.update(bean);
			
			
			
		}
		
//		ean.setId(id);
//		List<ProductBean> list=service.select(bean);
//
//		HttpSession session =request.getSession();
//		session.setAttribute("product",list);
		String path=getServletContext().getContextPath();
//		System.out.println("path:"+path);
		response.sendRedirect(path+"/pages/product.jsp");
		
		/*
		
		String a=request.getQueryString();
		System.out.println(a);
		
		
		ServletContext context=getServletContext();
		System.out.println("Email:"+context.getInitParameter("adminEmail"));
		
		String contextPath = getServletContext().getContextPath();
		System.out.println("contextPath:"+contextPath);
		
		String buttom = request.getParameter("prodaction");

		String id = request.getParameter("id");

		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String make = request.getParameter("make");
		Date makeDate = null;

		String expire = request.getParameter("expire");

		switch (buttom) {
		case "Select":
			if (id.equals("")) {
				System.out.println("selectAll");
				list = pd.select();

				//
				// HttpSession session =request.getSession();
				// session.setAttribute("selectList",list);
				// String contextPath=getServletContext().getContextPath();
				// response.sendRedirect(contextPath+"/pages/display.jsp");
				int count = list.size();

				request.setAttribute("selectList", list);
				request.setAttribute("count", count);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/display.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("select");
				int idNum = Integer.valueOf(id).intValue();
				bean = pd.select(idNum);

				// HttpSession session =request.getSession();
				// session.setAttribute("product", bean);
				// String contextPath=getServletContext().getContextPath();
				// response.sendRedirect(contextPath+"/pages/display.jsp");

				request.setAttribute("product", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/display.jsp");
				rd.forward(request, response);

			}

			break;
		case "Insert":
			int idNum2 = Integer.valueOf(id).intValue();
			double priceDou2 = Double.valueOf(price).doubleValue();
			int expireNum2 = Integer.valueOf(expire).intValue();

			try {
				if (make != null) {
					makeDate = sd.parse(make);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			bean.setId(idNum2);
			bean.setName(name);
			bean.setPrice(priceDou2);
			bean.setMake(makeDate);
			bean.setExpire(expireNum2);
			pd.insert(bean);

			HttpSession session = request.getSession();
			session.setAttribute("product", bean);

			response.sendRedirect("/MaTeacher/pages/product.jsp");
			break;
		case "Delete":
			int idNum3 = Integer.valueOf(id).intValue();
			boolean result = pd.delete(idNum3);
			
			if (result) {
				response.sendRedirect(contextPath + "/pages/product.jsp");
			}
			break;

		case "Update":
			int idNum4 = Integer.valueOf(id).intValue();
			bean = pd.select(idNum4);
			String newName;
			double newPrice;
			Date newDate;
			int newExpire;
			if (bean != null) {

				if (!name.equals("")) {
					newName = name;
				} else {
					newName = bean.getName();
				}

				if (!price.equals("")) {
					newPrice = Double.valueOf(price).doubleValue();
				} else {
					newPrice = bean.getPrice();
				}

				if (!make.equals("")) {
					try {
						if (make != null) {
							makeDate = sd.parse(make);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					newDate = makeDate;

				} else {
					newDate = bean.getMake();
				}

				if (!expire.equals("")) {
					newExpire = Integer.valueOf(expire).intValue();
				} else {
					newExpire = bean.getExpire();
				}

				pd.update(newName, newPrice, newDate, newExpire,idNum4);
				
				
				response.sendRedirect(contextPath+"/pages/product.jsp");
				
			} else {
				
				response.sendRedirect(contextPath + "/pages/product.jsp");

			}

			break;

		}
*/
	}
	

}
