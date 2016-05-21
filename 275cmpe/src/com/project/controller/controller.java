<<<<<<< HEAD
package com.project.controller;

import java.io.File;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.AccessException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;

import org.hibernate.MappingException;
import org.hibernate.type.BlobType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Category;
import com.project.model.Menu;
import com.project.model.User;
import com.project.service.menuService;
import com.project.service.UserService;


@Controller
public class controller {

	UserService userService=new UserService();
	menuService menuService= new menuService();
	User user = new User();
	public static final String FROM = "mohnishkukreja@gmail.com";   // Replace with your "From" address. This address must be verified.
    public static String TO = "shikha.kukreja@sjsu.edu"; 
    public static String BODY = " ";
    public static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";
    public static final String SMTP_USERNAME = "AKIAIEPQQOP74H7MTJOQ";  // Replace with your SMTP username.
    public static final String SMTP_PASSWORD = "AlhwII4UOx92NHY6ESPbWCiRWS27vmcEqynKyQjy6TqI";  // Replace with your SMTP password.
    public static final String URL = "http://localhost:8080/275cmpe/verifyemail.html";
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    public static final String HOST = "email-smtp.us-east-1.amazonaws.com";    
    public static final int PORT = 465;
    public static int verification_code=1000;
    public static Map<Integer,String> veremail = new HashMap<Integer, String>(); 
	
	@RequestMapping(value="/getprofilehtml", method=RequestMethod.POST)
	public String adminLogin(@RequestParam("id") String id,@RequestParam("password") String password,ModelMap model) {
		 
		System.out.println(id+"wtf"+password);
		if(id.equalsIgnoreCase("sachin") && password.equalsIgnoreCase("sac"))
		{
			return "adminHome";
		}
		return "error";
		
	}
	@RequestMapping(value="/verifyemail", method=RequestMethod.GET)
	public String verifyPage(ModelMap model)
	{
		return "verifyEmail";
	}
	
	@RequestMapping(value="/sendemail", method=RequestMethod.POST)
	  public String printHello(ModelMap model, @RequestParam("email") String TO, @RequestParam("password") String password) {
	      System.out.println("inside");
	      BODY=" ";
	      //String recipient = "sagaranil.manglani@sjsu.edu ,sarika.padmashali@sjsu.edu,shikha.kukreja@ sjsu.edu";
	      //String[] recipientList = recipient.split(",");
			model.addAttribute("message", "Hello Spring MVC Framework!");
	      verification_code+=1;
	      System.out.println("Vericode_code"+verification_code);
	        veremail.put(verification_code,TO);
	       /* veremail.put(1002,"sagaranil.manglani@sjsu.edu");
	        veremail.put(1003,"sarika.padmashali@sjsu.edu");
	        user.setEmail("sagaranil.manglani@sjsu.edu");
	        user.setEmail("sarika.padmashali@sjsu.edu");*/
	        user.setEmail(TO);
	        user.setPassword(password);
	        user.setVerification(verification_code);
	        /*user.setVerification(1002);
	        user.setVerification(1003);*/
			Properties props = System.getProperties();
	    	props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.port", 465); 
	    	
	    	// Set properties indicating that we want to use STARTTLS to encrypt the connection.
	    	// The SMTP session will begin on an unencrypted connection, and then the client
	        // will issue a STARTTLS command to upgrade to an encrypted connection.
	    	props.put("mail.smtp.auth", "true");
	    	props.put("mail.smtp.ssl.enable", "true");
	    	props.put("mail.smtp.starttls.required", "true");

	        // Create a Session object to represent a mail session with the specified properties. 
	    	Session session = Session.getDefaultInstance(props);

	        // Create a message with the specified information. 
	        MimeMessage msg = new MimeMessage(session);
	        try {
	        	BODY=BODY+" Verification code is "+verification_code+". Click on this link to get verified "+URL;
				msg.setFrom(new InternetAddress(FROM));
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
				/*msg.setRecipient(Message.RecipientType.TO, new InternetAddress("sarika.padmashali@sjsu.edu"));
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress("sagaranil.manglani@sjsu.edu"));*/
		        msg.setSubject(SUBJECT);
		        msg.setContent(BODY,"text/plain");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        Transport transport = null;
	        // Create a transport.        
	        
	        // Send the message.
	        try
	        {
	         transport = session.getTransport();
	            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
	            
	            // Connect to Amazon SES using the SMTP username and password you specified above.
	            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
	            // Send the email.
	            transport.sendMessage(msg, msg.getAllRecipients());
	            System.out.println("Email sent!");
	        }
	        catch (Exception ex) {
	        	ex.printStackTrace();
	            /*System.out.println("The email was not sent.");
	            System.out.println("Error message: " + ex.getMessage());*/
	        }
	        finally
	        {
	            // Close and terminate the connection.
	            try {
					transport.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        	
	        }
	      return "hello";
	}
	
	
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	public void addUser(@RequestParam("email") String email, @RequestParam("verification") int verification){
		System.out.println("Size of the hashmap is "+veremail.size());
		System.out.println("Verification code is "+verification_code);
		for ( Integer key : veremail.keySet() ) {
		    System.out.println("keys are"+ key );
		}
		System.out.println(veremail.get(verification));
		System.out.println("Email is "+email);
		User user2 = userService.getUser(user);
		if(user2==null){
			   //Do whatever you want to do
		if(email.equalsIgnoreCase(veremail.get(verification)))
				{
			userService.addNewUser(user);
		}
		}
			else
			{
				System.out.println("User is not added to the database");
			}
		}
	
	//ADD ITEMS INTO MENU
	@RequestMapping(value="/addMenu", method=RequestMethod.POST)
	public String addMenu(@RequestParam("itemName") String itemName,@RequestParam("price") String price,@RequestParam("category") String category,@RequestParam("calories") int calories,@RequestParam("time") int prepTime,@RequestParam("status") String status,@RequestParam("pic") File image,ModelMap model) {
		
		
		menuService.addMenu(itemName,price,category,status,prepTime,calories,image);
		Menu menu=menuService.getMenu(1);
		byte [] b=menu.getImage();
		
		 try{
	            FileOutputStream fos = new FileOutputStream("/Users/sachinhunur/git/CMPE275/275cmpe/WebContent/images/"+itemName+".jpg"); 
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
	@RequestMapping(value="/rendersignup", method=RequestMethod.GET)
	public String showSignUp()
	{
		System.out.println("inside add new user");
		//return new ModelAndView("signup.jsp");
		return "signup";
	}
	
	@RequestMapping(value="/signup.html",method=RequestMethod.POST)
	public String addNewUser(@RequestParam("email") String email,@RequestParam("password") String password)
	{
		System.out.println("email:"+email+" password:"+password);
		User user=new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setEnabled(true);
		System.out.println("user:"+user);
		userService.addNewUser(user);
		return "index";
	}
	
}
=======
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
				test.setOrderDate(o.getPickDate());
				test.setOrderTime(o.getPickTime());
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
>>>>>>> origin/master
