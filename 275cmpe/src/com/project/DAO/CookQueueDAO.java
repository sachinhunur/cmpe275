package com.project.DAO;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.CookQueue;
import com.project.model.User;


@Repository
public class CookQueueDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public void resetCook()
	{
		System.out.println("inside CookQueue dao:");
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		session.createQuery("delete from CookQueue").executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}
	
	public void insertIntoCook(CookQueue cq)
	{
		System.out.println("inside cook dao:"+cq);
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(cq);
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
	
	//fetch the result based on the pickup date
	public void checkForDelivery(Date d)
	{
		System.out.println("inside check for delivery:");
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(CookQueue.class);
		criteria.add(Restrictions.eq("pickup_date", d));
		ArrayList<CookQueue> cq=(ArrayList<CookQueue>)criteria.list();
		
		System.out.println("array list is");
		for(CookQueue c: cq)
		{
			System.out.println(c);
		}
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}

}
