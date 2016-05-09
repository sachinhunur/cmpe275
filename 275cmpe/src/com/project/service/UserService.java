package com.project.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.DAO.UserDAO;
import com.project.DAO.orderDAO;
import com.project.model.Order_details;
import com.project.model.User;

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

}
