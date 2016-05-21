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
