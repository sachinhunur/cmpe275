package com.project.DAO;



import com.project.model.Menu;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

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
}
