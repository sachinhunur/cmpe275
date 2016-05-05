package com.project.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.UserDAO;
import com.project.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO=new UserDAO();
	
	@Transactional
	public void addNewUser(User user)
	{
		System.out.println("in userservice:"+user);
		try{
		userDAO.addUser(user);
		return;
		}
		catch (Exception e){
			System.out.println("in exception");
			e.printStackTrace();
		}
	}

}
