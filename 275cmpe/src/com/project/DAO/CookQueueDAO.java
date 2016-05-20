/*package com.project.DAO;

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
	
	//delete from cook table
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
*/

package com.project.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

	public String checkEarliest(String date, String time, int prepTime) {
		// TODO Auto-generated method stub
		System.out.println("inside check for earliest delivery:");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat form= new SimpleDateFormat("HH:mm");

			Date checkDate;
			Date checkTime;
			Date checkTime1;
			Date newTime1;
			
			try {
				checkDate = formatter.parse(date);
				checkTime = form.parse(time);
				Calendar cal = Calendar.getInstance();
				 cal.setTime(checkTime);
				 cal.add(Calendar.MINUTE, prepTime);
				 Date newTime=cal.getTime();
				 checkTime1=checkTime;
				 newTime1=newTime;
				 String finalTime = form.format(cal.getTime());
				
				SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
				Session session=sessionFactory.openSession();
				session.beginTransaction();
				
				Criteria criteria = session.createCriteria(CookQueue.class);
				criteria.add(Restrictions.eq("pickup_date", checkDate));
				ArrayList<CookQueue> cq=(ArrayList<CookQueue>)criteria.list();
				ArrayList<CookQueue> cq1= new ArrayList<CookQueue>();
				ArrayList<CookQueue> cq2= new ArrayList<CookQueue>();
				ArrayList<CookQueue> cq3= new ArrayList<CookQueue>();
				System.out.println(cq1);
				System.out.println("array list is");
				for(CookQueue c: cq)
				{
					System.out.println(c.getStart_time());
					if(c.getCook_id()==1)
					{
						cq1.add(c);
					}
					else if(c.getCook_id()==2)
					{
						cq2.add(c);
					}
					else if(c.getCook_id()==3)
					{
						cq3.add(c);
					}
				}
			
				if(cq1.isEmpty())
				{
					
					return finalTime;
				}
				else if(cq2.isEmpty())
				{
			
					return finalTime;
				}
				else if(cq3.isEmpty())
				{
					
					return finalTime;
				}
				else{
					System.out.println("nothing is empty");
			//	String minTime= new String();
			//	String minTime1= new String();
				Date minTime=new Date();
				Date minTime1=new Date();
					for(CookQueue c: cq1)
				{
					if(((checkTime.compareTo(c.getStart_time()) >0) && (c.getEnd_time().compareTo(checkTime)>0)) || ((newTime.compareTo(c.getStart_time()) >0) && (c.getEnd_time().compareTo(newTime)>0)) || ((c.getStart_time().compareTo(checkTime) >0) && (c.getEnd_time().compareTo(newTime)) >0))
					{
						if(c.getStart_time().compareTo(newTime)>0)
						{
						
							minTime1=newTime;
							break;
						}
						else
						{
						System.out.println("does not fit here");
						cal=Calendar.getInstance();
						cal.setTime(c.getEnd_time());
						cal.add(Calendar.MINUTE, 1);
						
					//	System.out.println("end"+java.sql.Time.valueOf(form.format(cal.getTime())));
						
						checkTime=form.parse(form.format(cal.getTime()));
						cal=Calendar.getInstance();
						cal.setTime(checkTime);
						cal.add(Calendar.MINUTE, prepTime);
						newTime=form.parse(form.format(cal.getTime()));
					System.out.println("cq1");
					System.out.println("start1"+checkTime);
					System.out.println("end1"+newTime);
						}
					}
					else
					{
					minTime1=newTime;
					}
				}
				minTime=minTime1;
				checkTime=checkTime1;
				newTime=newTime1;
				System.out.println(checkTime);
				for(CookQueue c: cq2)
				{
					if((checkTime.compareTo(c.getStart_time()) >0 && c.getEnd_time().compareTo(checkTime)>0) || (newTime.compareTo(c.getStart_time()) >0 && c.getEnd_time().compareTo(newTime)>0))
					{
						if(c.getStart_time().compareTo(newTime)>0)
						{
							minTime1=newTime;
							break;
						}
						else
						{
						System.out.println("does not fit here");
						cal=Calendar.getInstance();
						cal.setTime(c.getEnd_time());
						cal.add(Calendar.MINUTE, 1);
				
					//	System.out.println("end"+java.sql.Time.valueOf(form.format(cal.getTime())));
						
						checkTime=form.parse(form.format(cal.getTime()));
						cal=Calendar.getInstance();
						cal.setTime(checkTime);
						cal.add(Calendar.MINUTE, prepTime);
						newTime=form.parse(form.format(cal.getTime()));
						System.out.println("cq2");
						System.out.println("start2"+checkTime);
						System.out.println("end2"+newTime);
						}
					}
					else
					{
					minTime1=newTime;
					}
				}
				if(minTime.compareTo(minTime1)>0)
				{	
					System.out.println("its 2nd cook");
					minTime=minTime1;
				}
				checkTime=checkTime1;
				newTime=newTime1;
				System.out.println(checkTime);
				for(CookQueue c: cq3)
				{
					if((checkTime.compareTo(c.getStart_time()) >0 && c.getEnd_time().compareTo(checkTime)>0) || (newTime.compareTo(c.getStart_time()) >0 && c.getEnd_time().compareTo(newTime)>0))
					{
						if(c.getStart_time().compareTo(newTime)>0)
						{
							minTime1=newTime;
							break;
						}
						else
						{
						System.out.println("does not fit here");
						cal=Calendar.getInstance();
						cal.setTime(c.getEnd_time());
						cal.add(Calendar.MINUTE, 1);
						System.out.println("start"+cal.getTime());
						System.out.println(time);
						System.out.println("start"+form.parse(form.format(cal.getTime())));
					//	System.out.println("end"+java.sql.Time.valueOf(form.format(cal.getTime())));
						
						checkTime=form.parse(form.format(cal.getTime()));
						cal=Calendar.getInstance();
						cal.setTime(checkTime);
						cal.add(Calendar.MINUTE, prepTime);
						newTime=form.parse(form.format(cal.getTime()));
						System.out.println("start3"+checkTime);
						System.out.println("end3"+newTime);
						}
					}
					else
					{
					minTime1=newTime;
					}
					if(minTime.compareTo(minTime1)>0)
					{
						System.out.println("its 3rdcook");
						minTime=minTime1;
					}
				}
				session.getTransaction().commit();
				session.close();
				sessionFactory.close();
				
				return form.format(minTime);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return time;
			

		
		//sessionFactory.getCurrentSession().save(user);

		
	}

}

