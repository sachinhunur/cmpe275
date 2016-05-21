package com.project.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.User;


@Repository
public class UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void addUser(User user)
	{
		System.out.println("inside user dao:"+user);
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		 
	}
	
	public User getUser(User user)
	{
		System.out.println("inside user dao:"+user);
		//sessionFactory.getCurrentSession().save(user);
		SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=  session.createQuery("from User where email=?");
		user=(User)query.setString(0,user.getEmail()).uniqueResult();
		//user = (User) session.get(User.class, user.getVerification());
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return user;
		 
	}

}
