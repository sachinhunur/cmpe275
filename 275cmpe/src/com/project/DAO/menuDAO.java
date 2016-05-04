package com.project.DAO;



import com.project.model.Menu;

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
		
		public Menu getMenu(int j){
			System.out.println(" In DAO"+ j);
			
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Menu men=(Menu)session.get(Menu.class,j);
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
}
