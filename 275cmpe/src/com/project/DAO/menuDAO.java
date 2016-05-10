package com.project.DAO;

import com.project.model.Menu;
import com.project.model.Menu_temp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

public class menuDAO {

		public void addMenu(Menu menu){
			System.out.println(" In DAO"+ menu);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(menu);
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		}
		
		public Menu getMenu(String itemName){
			System.out.println(" In DAO"+ itemName);
			
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Menu.class);
			criteria.add(Restrictions.eq("item_name", itemName));
			Menu men=(Menu)criteria.uniqueResult();
			System.out.println(men.getItem_name());
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			return men;
		}
		public void deactivateMenu(String itemName) {
			// TODO Auto-generated method stub
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Menu.class);
			criteria.add(Restrictions.eq("item_name", itemName));
			Menu men=(Menu)criteria.uniqueResult();
			men.setMenu_status("false");
			session.saveOrUpdate(men);
			session.getTransaction().commit();;
			session.flush();
			session.close();
			sessionFactory.close();
		}

		public void activateMenu(String itemName) {
			// TODO Auto-generated method stub
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Menu.class);
			criteria.add(Restrictions.eq("item_name", itemName));
			Menu men=(Menu)criteria.uniqueResult();
			men.setMenu_status("true");
			session.saveOrUpdate(men);
			session.getTransaction().commit();;
			session.flush();
			session.close();
			sessionFactory.close();
		}

		public Menu editMenu(int id) {
			// TODO Auto-generated method stub
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Menu men=(Menu)session.get(Menu.class, id);
			System.out.println(men.getItem_name());
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			return men;
			
		}

		public void updateMenu(Menu menu) {
			// TODO Auto-generated method stub
			System.out.println(" In DAO"+ menu);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(menu);
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		}
		
		
		public List<Menu> getAllMenu()
		{
			System.out.println("inside menu dao for getAllMenu:");
			//sessionFactory.getCurrentSession().save(user);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			List<Menu> result = session.createQuery("from Menu").list();
			for(Menu m:result)
			{
				System.out.println(m);
			}
			
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			return result;
			 
		}
		
		
		
}
