package com.project.DAO;

import com.project.model.Menu;
import com.project.model.Menu_temp;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.mock.web.MockMultipartFile;

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
		
		//delete contents from table and add only 3 menus
		public void resetMenu()
		{
			System.out.println("inside CookQueue dao:");
			//sessionFactory.getCurrentSession().save(user);
			SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			session.createQuery("delete from Menu").executeUpdate();
			
			//converting file to multipart file
			/*File file = new File("C:/Users/Anuja Asalkar/Downloads/275cmpe/275cmpe/275cmpe/WebContent/images/default1.jpg");
		    try {
				FileInputStream input = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		    //MultipartFile multipartFile = new 
		    		//new MockMultipartFile("file",file.getName(), "text/plain", IOUtils.toByteArray(input));
			
		    BufferedImage originalImage;
		    ByteArrayOutputStream baos=null;
		    byte[] imageInByte=null;
			try {
				originalImage = ImageIO.read(new File("C:/Users/Anuja Asalkar/Downloads/275cmpe/275cmpe/275cmpe/WebContent/images/default1.jpg"));
				//baos=new ByteArrayOutputStream();
				//ImageIO.write( originalImage, "jpg", baos );
				//baos.flush();
			    //imageInByte = baos.toByteArray();
			    //baos.close();
				WritableRaster r=originalImage.getRaster();
				DataBufferByte data=(DataBufferByte)r.getDataBuffer();
				imageInByte=data.getData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
			Menu m1=new Menu();
			m1.setCalories(10);
			m1.setCategory("Drink");
			m1.setImage(imageInByte);
			m1.setItem_name("Coffee");
			m1.setMenu_status("true");
			m1.setPreparation_time(1);
			m1.setUnitPrice("2.5");
			
			
			
			Menu m2=new Menu();
			m2.setCalories(100);
			m2.setCategory("Appetizer");
			m2.setImage(imageInByte);
			m2.setItem_name("Soup");
			m2.setMenu_status("true");
			m2.setPreparation_time(2);
			m2.setUnitPrice("5");
			
			Menu m3=new Menu();
			m3.setCalories(10);
			m3.setCategory("Main Course");
			m3.setImage(imageInByte);
			m3.setItem_name("Noodles");
			m3.setMenu_status("true");
			m3.setPreparation_time(3);
			m3.setUnitPrice("3");
			
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			
			addMenu(m1);
			addMenu(m2);
			addMenu(m3);	
		}
		
		
}
