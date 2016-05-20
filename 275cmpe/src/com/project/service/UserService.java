/*package com.project.service;

import java.util.Date;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.DAO.UserDAO;
import com.project.DAO.orderDAO;
import com.project.model.Order_details;
import com.project.model.User;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO=new UserDAO();
	
	@Transactional
	public void addNewUser(User user)//String fname,String lname,String email, String password,String pno,Integer vcode)
	{
		System.out.println("in userservice:"+user);
		try{
			user.setFirst_name(fname);
			user.setLast_name(lname);
			user.setEmail_id(email);
			user.setPassword(password);
			user.setPhone_number(pno);
			user.setVerification_code(vcode);
			user.setEnabled(true);
		userDAO.addUser(user);
		//return;
		}
		catch (Exception e){
			System.out.println("in exception");
			e.printStackTrace();
		}
	}
	
	public int userLogin(User user) {
		int login = userDAO.searchUser(user);
		return login;
	}

	public void placeOrder(String item, String qty, Date date, float price, String pickTime) {
		// TODO Auto-generated method stub
		Order_details order=new Order_details();
		order.setMenu_items(item);
		order.setQty(qty);
		order.setOrderTime(date);
		order.setPrice(price);
		orderDAO placeOrder=new orderDAO();
		placeOrder.placeOrder(order);
		
	}
	
	
	//send email
	public int sendmail(String email, String action)
	{
		Random rn = new Random();
		String BODY=" ";
		int verification;
		SendGrid.Email emaill = new SendGrid.Email();
		emaill.addTo(email);
		emaill.setFrom("shikha.kukreja@sjsu.edu");
		if(action.equalsIgnoreCase("VERIFY"))
		{
			verification= rn.nextInt(100000);
			BODY=BODY+"Your verification code is "+verification+" please put the code to complete the signup process";//it generates random number between 0 and 100000
			SendGrid sendgrid = new SendGrid("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");
			//veremail.put(verification,email);
			emaill.setSubject("VERIFICATION CODE");
			emaill.setHtml(BODY);
			try {
				   SendGrid.Response response = sendgrid.send(emaill);
			} catch (SendGridException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			}
			System.out.println("Email Sent!");
			return verification;
		}
		else if(action.equalsIgnoreCase("CONFIRM"))
		{
			BODY=BODY+"This is confirmation mail for order";
			SendGrid sendgrid = new SendGrid("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");
			//veremail.put(verification,email);
			emaill.setSubject("CONFIRMATION MAIL");
			emaill.setHtml(BODY);
			try {
				   SendGrid.Response response = sendgrid.send(emaill);
			} catch (SendGridException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			}
			System.out.println("Email Sent!");
			return 1;
		}
		else
		{
			return 1;
		}
		
		
		  
		 
	}
	
	//check user exists
	public User getUser(User user)
	{
		User user1 = null;
		System.out.println("in userservice:"+user);
		try{
		 user1=userDAO.getUser(user);
		
		}
		catch (Exception e){
			System.out.println("in exception");
			e.printStackTrace();
		}
		return user1;
	}

}
*/

package com.project.service;

import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.DAO.UserDAO;
import com.project.DAO.orderDAO;
import com.project.model.Order_details;
import com.project.model.User;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO=new UserDAO();
	
	@Transactional
	public void addNewUser(User user)//String fname,String lname,String email, String password,String pno,Integer vcode)
	{
		System.out.println("in userservice:"+user);
		try{
			/*user.setFirst_name(fname);
			user.setLast_name(lname);
			user.setEmail_id(email);
			user.setPassword(password);
			user.setPhone_number(pno);
			user.setVerification_code(vcode);
			user.setEnabled(true);*/
		userDAO.addUser(user);
		//return;
		}
		catch (Exception e){
			System.out.println("in exception");
			e.printStackTrace();
		}
	}
	
	@Transactional
	public int userLogin(User user) {
		int login = userDAO.searchUser(user);
		return login;
	}

/*	public void placeOrder(String item, String qty, Date date, float price, String pickTime) {
		// TODO Auto-generated method stub
		Order_details order=new Order_details();
		order.setMenu_items(item);
		order.setQty(qty);
		order.setOrderTime(date);
		order.setPrice(price);
		orderDAO placeOrder=new orderDAO();
		placeOrder.placeOrder(order);
		
	}*/
	
	
	//send email
	@Transactional
	public int sendmail(String email, String action)
	{
		Random rn = new Random();
		String BODY=" ";
		int verification;
		SendGrid.Email emaill = new SendGrid.Email();
		emaill.addTo(email);
		emaill.setFrom("shikha.kukreja@sjsu.edu");
		if(action.equalsIgnoreCase("VERIFY"))
		{
			verification= rn.nextInt(100000);
			BODY=BODY+"Your verification code is "+verification+" please put the code to complete the signup process";//it generates random number between 0 and 100000
			SendGrid sendgrid = new SendGrid("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");
			//veremail.put(verification,email);
			emaill.setSubject("VERIFICATION CODE");
			emaill.setHtml(BODY);
			try {
				   SendGrid.Response response = sendgrid.send(emaill);
			} catch (SendGridException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			}
			System.out.println("Email Sent!");
			return verification;
		}
		else if(action.equalsIgnoreCase("CONFIRM"))
		{
			BODY=BODY+"This is confirmation mail for order";
			SendGrid sendgrid = new SendGrid("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");
			//veremail.put(verification,email);
			emaill.setSubject("CONFIRMATION MAIL");
			emaill.setHtml(BODY);
			try {
				   SendGrid.Response response = sendgrid.send(emaill);
			} catch (SendGridException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			}
			System.out.println("Email Sent!");
			return 1;
		}
		else
		{
			return 1;
		} 
	}
	
	//check user exists
	public User getUser(User user)
	{
		User user1 = null;
		System.out.println("in userservice:"+user);
		try{
		 user1=userDAO.getUser(user);
		
		}
		catch (Exception e){
			System.out.println("in exception");
			e.printStackTrace();
		}
		return user1;
	}

	//for adding order
	public void addOrder(String email, String itemSet, String priceSet, String qtySet, String pickDate,
			String pickTime) {
		// TODO Auto-generated method stub
		Order_details od= new Order_details();
		od.setMenu_items(itemSet);
		od.setUserId(email);
		od.setPrice(priceSet);
		od.setQty(qtySet);
		od.setOrderTime(pickTime);
		od.setOrderDate(pickDate);
		od.setStatus("placed");
		orderDAO ord=new orderDAO();
		ord.addOrder(od);
		
	}
	//for finding recent order id
	public int findRecentOrderId() {
		// TODO Auto-generated method stub
		orderDAO od=new orderDAO();
		int order_id=od.getLastOrderId();
		return order_id;
	}

	public void cancelOrder(String email, int orderId) {
		orderDAO od=new orderDAO();
		od.cancelOrder(email,orderId);
		
	}

}
