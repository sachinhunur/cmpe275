
	
	package com.project.DAO;

	import java.util.List;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.AnnotationConfiguration;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;

import com.project.model.Order_details;
import com.project.model.User;


	@Repository
	public class orderDAO {
		
		@Autowired
		SessionFactory sessionFactory;
		
		public void placeOrder(Order_details order)
		{
			System.out.println("inside user dao:"+order);
			//sessionFactory.getCurrentSession().save(user);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			 
		}

		
	

}
