package com.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.hibernate.type.BlobType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Category;
import com.project.model.CookQueue;
import com.project.model.Menu;
import com.project.model.Order_details;
import com.project.model.User;
import com.project.service.menuService;
import com.project.service.CookQueueService;
import com.project.service.UserService;


@Controller
public class controller {

	UserService userService=new UserService();
	
	menuService menuService= new menuService();
	
	//render admin login page
	@RequestMapping(value="/renderAdmin", method=RequestMethod.GET)
	public String showLogin()
	{
		System.out.println("inside render admin");
		//return new ModelAndView("signup.jsp");
		return "loginAdmin";
	}
	
	
	@RequestMapping(value="/getprofilehtml", method=RequestMethod.POST)
	public String adminLogin(@RequestParam("email") String id,@RequestParam("password") String password,ModelMap model) {
		 
		System.out.println(id+"wtf"+password);
		if(id.equalsIgnoreCase("sachin@gmail.com") && password.equalsIgnoreCase("sac"))
		{
			return "adminHome";
		}
		return "error";
		
	}
	//ADD ITEMS INTO MENU
	@RequestMapping(value="/addMenu", method=RequestMethod.POST)
	public String addMenu(@RequestParam("itemName") String itemName,@RequestParam("price") String price,@RequestParam("category") String category,@RequestParam("calories") int calories,@RequestParam("time") int prepTime,@RequestParam("status") String status,@RequestParam("pic") MultipartFile image,ModelMap model) {
		
		
		menuService.addMenu(itemName,price,category,status,prepTime,calories,image,"1");
		Menu menu=menuService.getMenu(itemName);
		byte [] b=menu.getImage();
		 try{
	         //for local 
			 FileOutputStream fos = new FileOutputStream("C:/Users/Anuja Asalkar/Downloads/275cmpe/275cmpe/275cmpe/WebContent/images/"+menu.getMenu_id()+".jpg"); 
			 //opt/tomcat/webapps/275cmpe/images
			 //for shikha's
	           // FileOutputStream fos = new FileOutputStream("opt/tomcat/webapps/275cmpe/images"+menu.getMenu_id()+".jpg");
	           //for sindhu's 
	           // FileOutputStream fos = new FileOutputStream("home/ubuntu/apache-tomcat-8.0.33/apache-tomcat-8.0.33/webapps/images"+menu.getMenu_id()+".jpg");
	            fos.write(b);
	            fos.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		// System.out.print(b);
		// model.addAttribute("file","./images/"+itemName+".jpg");
		return "adminHome";
		
	}

	//Deactivate MENU ITEM
	@RequestMapping(value="/deactivate", method=RequestMethod.POST)
	public String deactivateMenu(@RequestParam("itemName") String itemName,ModelMap model) {
		 
		menuService.deactivateMenu(itemName);

		return "adminHome";
		
	}
	
	//Activate MENU ITEM
	@RequestMapping(value="/activate", method=RequestMethod.POST)
	public String activateMenu(@RequestParam("itemName") String itemName,ModelMap model) {
		 
		menuService.activateMenu(itemName);
		return "adminHome";
		
	}
	//EDIT MENU ITEM
	@RequestMapping(value="/editMenu.html",method=RequestMethod.POST)
	public String addNewUser(@RequestParam("id") int id,ModelMap model)
	{
		
		Menu menu=menuService.editMenu(id);
		model.addAttribute("id", menu.getMenu_id());
		model.addAttribute("itemName", menu.getItem_name());
		model.addAttribute("price", menu.getUnitPrice());
		model.addAttribute("calories", menu.getCalories());
		model.addAttribute("category", menu.getCategory());
		model.addAttribute("time", menu.getPreparation_time());
		model.addAttribute("status", menu.getMenu_status());
		byte [] b=menu.getImage();
		 try{
			 //for local
	           FileOutputStream fos = new FileOutputStream("C:/Users/Anuja Asalkar/Downloads/275cmpe/275cmpe/275cmpe/WebContent/images/"+menu.getMenu_id()+".jpg");
			 //for shikha's
			// FileOutputStream fos = new FileOutputStream("opt/tomcat/webapps/275cmpe/images"+menu.getMenu_id()+".jpg");
			 //for sindhus
			 //FileOutputStream fos = new FileOutputStream("home/ubuntu/apache-tomcat-8.0.33/apache-tomcat-8.0.33/webapps/images"+menu.getMenu_id()+".jpg");
			 	fos.write(b);
	            
	            fos.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }

		
		model.addAttribute("file","./images/"+menu.getMenu_id()+".jpg");
		
		return "editMenu";
	}
	
	//UPDATE MENU ITEM
		@RequestMapping(value="/update", method=RequestMethod.POST)
		public String updateMenu(@RequestParam("id") int id,@RequestParam("itemName") String itemName,@RequestParam("price") String price,@RequestParam("category") String category,@RequestParam("calories") int calories,@RequestParam("time") int prepTime,@RequestParam("status") String status,@RequestParam("pic") MultipartFile image,ModelMap model) throws IOException {
			
			if(image.getSize() == 0)
			 {
				 Menu menu=menuService.editMenu(id);
				 menuService.updateMenu(id,itemName,price,category,status,prepTime,calories,menu.getImage(),"1");
			 } else
				{
		
				 menuService.updateMenu(id,itemName,price,category,status,prepTime,calories,image.getBytes(),"1");
				}
			 Menu menu=menuService.getMenu(itemName);
			//for local
			File file=new File("C:/Users/Anuja Asalkar/Downloads/275cmpe/275cmpe/275cmpe/WebContent/images/"+menu.getMenu_id()+".jpg");
			 //for shikha's
			 //File file=new File("/opt/tomcat/webapps/275cmpe/images"+menu.getMenu_id()+".jpg");
			 //for sindhu's
			// File file=new File("home/ubuntu/apache-tomcat-8.0.33/apache-tomcat-8.0.33/webapps/images"+menu.getMenu_id()+".jpg");
			 file.delete();
			 byte [] b=menu.getImage();
			 try{
				 //for local
		           FileOutputStream fos = new FileOutputStream("C:/Users/Anuja Asalkar/Downloads/275cmpe/275cmpe/275cmpe/WebContent/images/"+menu.getMenu_id()+".jpg");
		         //for shikhas  
				// FileOutputStream fos = new FileOutputStream("opt/tomcat/webapps/275cmpe/images"+menu.getMenu_id()+".jpg");
		         //for sindhus   
				// FileOutputStream fos = new FileOutputStream("home/ubuntu/apache-tomcat-8.0.33/apache-tomcat-8.0.33/webapps/images"+menu.getMenu_id()+".jpg");
		            fos.write(b);
		            
		            fos.close();
		        }catch(Exception e){
		            e.printStackTrace();
		        }
			return "adminHome";		
		}
		
		
		//for resetting order queue
		@RequestMapping(value="/resetMenu.html",method=RequestMethod.POST)
		public void resetTable()
		{
			System.out.println("inside reset table");
			CookQueueService cqs=new CookQueueService();
			cqs.reset();
			
			menuService ms=new menuService();
			ms.resetMenu();
					
		}
		
		//adding into cook
		@RequestMapping(value="/setMenu.html",method=RequestMethod.POST)
		public void setTable()
		{
			System.out.println("inside reset table");
			CookQueueService cqs=new CookQueueService();
			CookQueue cq=new CookQueue();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			
			try {
				Date date = dateFormat.parse("2016-10-10");
				cq.setCook_id(1);
				cq.setPickup_time(timeFormat.parse("07:00"));
				cq.setPickup_date(dateFormat.parse("2016-10-10"));
				cq.setStart_time(timeFormat.parse("06:00"));
				cq.setEnd_time(timeFormat.parse("06:10"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			cqs.addNewCook(cq);
		}
		
		
		//ORDER HISTORYYYYYYYYYYYYYYYY
		@RequestMapping(value="/orderHistory.html",method=RequestMethod.POST)
		public ModelAndView orderHistory()
		{
			ModelAndView model=new ModelAndView();
			System.out.println("inside order history");
			menuService ms=new menuService();
			
			ArrayList<Order_details> od= ms.getHistory();
			List <Order_details> frontList=new ArrayList<Order_details>();
			for(Order_details o : od)
			{
				Order_details test= new Order_details();
				test.setOrder_id(o.getOrder_id());
				test.setPickDate(o.getPickDate());
				test.setPickTime(o.getPickTime());
				test.setStatus(o.getStatus().toString());
				test.setUserId(o.getUserId().toString());
				System.out.println(o);
				frontList.add(test);
			}
			
			model.addObject("orderHistory",frontList);
			model.setViewName("orderHistory");
			return model;
			
		
		}
  }
