/*
	
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
*/


	
	package com.project.DAO;

	import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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

		public void addOrder(Order_details od) {
			// TODO Auto-generated method stub
			System.out.println("inside user dao:"+od);
			//sessionFactory.getCurrentSession().save(user);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(od);
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		}

	

		public int getLastOrderId() {
			System.out.println("inside getLast order:");
			//sessionFactory.getCurrentSession().save(user);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria crit = session.createCriteria(Order_details.class);
		    crit.add(Restrictions.gt("order_id",new Integer(0)));
		    crit.addOrder(Order.desc("order_id"));
		    List results = crit.list();
		    
		    Order_details cd=new Order_details();
		    Iterator iter = results.iterator();
		    while (iter.hasNext()) {
		    	cd = (Order_details) iter.next();
		      System.out.println("Result Name:"+cd.getOrder_id());
		      break;
		    }
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			return cd.getOrder_id();
		}
	

		public void cancelOrder(String email, int orderId) {
			// TODO Auto-generated method stub
			System.out.println("inside cancel order:");
			//sessionFactory.getCurrentSession().save(user);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			
			Order_details od=(Order_details) session.get(Order_details.class, orderId);
			od.setStatus("cancelled");
			session.saveOrUpdate(od);
			
		
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			
		}

}
