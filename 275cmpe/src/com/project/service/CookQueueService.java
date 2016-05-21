/*package com.project.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.CookQueueDAO;
import com.project.model.CookQueue;

@Service
public class CookQueueService {
	
	@Autowired
	CookQueueDAO cookQueueDAO=new CookQueueDAO();
	
	public void reset()
	{
		System.out.println("inside cook service");
		try
		{
			cookQueueDAO.resetCook();
		}
		catch(Exception e)
		{
			System.out.println("inside exception:");
			e.printStackTrace();
		}
	}
	
	public void addNewCook(CookQueue cq)
	{
		System.out.println("inside addNewCook");
		cookQueueDAO.insertIntoCook(cq);
	}
	
	public void check(Date d)
	{
		System.out.println("inside check service");
		cookQueueDAO.checkForDelivery(d);
	}

}
*/


package com.project.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DAO.CookQueueDAO;
import com.project.model.CookQueue;

@Service
public class CookQueueService {
	
	@Autowired
	CookQueueDAO cookQueueDAO=new CookQueueDAO();
	
	public void reset()
	{
		System.out.println("inside cook service");
		try
		{
			cookQueueDAO.resetCook();
		}
		catch(Exception e)
		{
			System.out.println("inside exception:");
			e.printStackTrace();
		}
	}
	
	public void addNewCook(CookQueue cq)
	{
		System.out.println("inside addNewCook");
		cookQueueDAO.insertIntoCook(cq);
	}
	
	public void check(Date d)
	{
		System.out.println("inside check service");
		cookQueueDAO.checkForDelivery(d);
	}

	public String check(String date, String time, int prepTime) {
		// TODO Auto-generated method stub
		return cookQueueDAO.checkEarliest(date,time,prepTime);
	}

}
