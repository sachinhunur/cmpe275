package com.project.controller;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Menu;
import com.project.model.Menu_temp;
import com.project.model.User;
import com.project.service.CookQueueService;
import com.project.service.MenuTempService;
import com.project.service.UserService;
import com.project.service.menuService;

@Controller
public class UserController {

	//User Login
	@RequestMapping(value="/userlogin", method=RequestMethod.POST)
	public ModelAndView showMenu(HttpServletRequest request, HttpServletResponse response)
	{
		//System.out.println("inside show menu:"+email+" , "+password);
		String email=request.getParameter("email");
		String password=request.getParameter("password");
	
		
		User user=new User();
		UserService userService=new UserService();
		menuService mService=new menuService();
		
		user.setEmail_id(email);
		user.setPassword(password);
		
		int success=userService.userLogin(user);
		if(success!=0)
		{
		
		List<Menu> menus= mService.showAllMenu();
		
		List<Menu> drink=new ArrayList<Menu>();
		List<Menu> appetizer=new ArrayList<Menu>();
		List<Menu> main_course=new ArrayList<Menu>();
		List<Menu> dessert=new ArrayList<Menu>();
		
		for(Menu m:menus)
		{
			if(m.getMenu_status().equalsIgnoreCase("TRUE"))
			{
				if(m.getCategory().equalsIgnoreCase("DRINK"))
					drink.add(m);
				else if(m.getCategory().equalsIgnoreCase("APPETIZER"))
					appetizer.add(m);
				else if(m.getCategory().equalsIgnoreCase("MAIN COURSE"))
					main_course.add(m);
				else
					dessert.add(m);
			}
		}
		
		HttpSession session=request.getSession();
		System.out.println("session:"+session.toString());
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		System.out.println("session:"+session.getAttribute("email")+" , "+ session.getAttribute("password"));
		
		ModelAndView mv=new ModelAndView();	
		mv.addObject("drinks", drink);
		mv.addObject("appetizers", appetizer);
		mv.addObject("main_courses", main_course);
		mv.addObject("desserts", dessert);
		mv.addObject("s",session);
		mv.setViewName("menu");	
		return mv;
		}
		else
		{
			return new ModelAndView("index");
		}
			
	}
	//User Logout
	
		@RequestMapping(value="/logout",method=RequestMethod.POST )
		public ModelAndView lout(HttpServletRequest request,HttpServletResponse response)
		{
			HttpSession s=request.getSession();
			System.out.println("before invalidating :"+s.getAttribute("email"));
			s.invalidate();
			System.out.println("after invalidating :");
			return new ModelAndView("index");
		}
	//Render SignUp page
	@RequestMapping(value="/rendersignup", method=RequestMethod.GET)
	public String showSignUp()
	{
		System.out.println("inside add new user");
		//return new ModelAndView("signup.jsp");
		return "signup";
	}
	
	//User SignUp
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String addNewUser(HttpServletRequest request , HttpServletResponse response)
	{
		String fname=request.getParameter("first_name");
		String lname=request.getParameter("last_name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String pno=request.getParameter("phone_no");
		//Integer vcode=Integer.parseInt(request.getParameter("verification_code"));
		
		HttpSession session=request.getSession();
		session.setAttribute("first_name", fname);
		session.setAttribute("last_name", lname);
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		session.setAttribute("phone_no", pno);
		
		//User user=new User();
		
		//user.setFirst_name(fname);
		///user.setLast_name(lname);
		//user.setEmail_id(email);
		//user.setPassword(password);
		//user.setPhone_number(pno);
		//user.setVerification_code(vcode);
		//user.setEnabled(true);

		UserService userService=new UserService();
		
		
		System.out.println("email:"+email+" password:"+password);
		//userService.addNewUser(user);
		
		//send email logic
		int veri_code=userService.sendmail(email,"VERIFY");
		System.out.println("vcode:"+veri_code);
		session.setAttribute("verification", veri_code);
		
		return "verificationCode";
	}
	
	
	
	//for checking verification code
	@RequestMapping(value="/signup2",method=RequestMethod.POST)
	public String sendEmail(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("inside check verification code");
		Integer vcode=Integer.parseInt(request.getParameter("verification_code"));
		HttpSession s=request.getSession();
		System.out.println("from text box:"+vcode);
		if(vcode.equals(s.getAttribute("verification")))
		{
			User user=new User();
			UserService userService=new UserService();
			
			System.out.println("insert into db");
			user.setFirst_name((s.getAttribute("first_name")).toString());
			user.setLast_name((s.getAttribute("last_name")).toString());
			user.setEmail_id((s.getAttribute("email")).toString());
			user.setPassword((s.getAttribute("password")).toString());
			user.setPhone_number((s.getAttribute("phone_no")).toString());
			user.setVerification_code(vcode);
			user.setEnabled(true);
			User u=userService.getUser(user);
			if(u==null)
			{
				userService.addNewUser(user);
				return "index";
			}
			else
			{
				System.out.println("user already exists..!!");
			}
		}
		else
		{
			System.out.println("verification code does not match");
			
		}
		return "error";
	}
	
	//Taking order from user
	@RequestMapping(value="/receive_order",method=RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request,HttpServletResponse response)
	{
		//ModelAndView mv=new ModelAndView();
		String[] items=request.getParameterValues("menus");
		
	//	String[] quantity=request.getParameterValues("quantity");
		
		//ArrayList<Integer> indices=new ArrayList<Integer>();
		HashMap<String, Integer> order=new HashMap<String,Integer>();
		System.out.println("no. of items:"+items.length);
		int i=0,j=0;;
		while(i<items.length)
		{
			//System.out.println("items:"+items[i]);
			if(items[i].equals(""))
			{
				i++;
			}
			else
			{
				if(!(i+1==items.length))
				{
				System.out.println("i="+items[i]+" , "+items[i+1]);
				order.put(items[i+1], Integer.parseInt(items[i]));
				i=i+2;
				}
			}
			//if(items[i]!="")
			/*{
			System.out.println("items:"+items[i]);
			System.out.println("quantity:"+quantity[j]);
			j++;
			indices.add(i);
			}
			i++;*/
		}
		
		/*for(Integer in:indices)
		{
			System.out.println("in:"+in);
		}*/
		
		/*while(j<quantity.length)
		{
			System.out.println("quantity:"+quantity[j]);
			j++;
		}
		*/
		String item=new String();
		String qty=new String();
		float price=0;
		int reload_menu=0;
		
		for(Entry<String, Integer> e : order.entrySet())
		{
			System.out.println("e:"+e.getKey()+","+e.getValue());
			item+=","+e.getKey();
			qty+=" "+e.getValue();
			Menu menu=menuService.getMenu(e.getKey());
			//if(!(menu.getMenu_status().equals("true")))
			{
				//System.out.println("admin has disabled one order");
				//reload_menu=1;
				//break;
				price+=Float.parseFloat(menu.getUnitPrice())*e.getValue();
			}
		}
		
		//if(reload_menu!=1)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date();
		//String s=date.toString();
		
		//String[] arrayOfString=s.split("\\s+");
			System.out.println(dateFormat.format(date)+" "+price +" "+ item+" "+qty);
			UserService userService=new UserService();
			//String pickTime="8:00PM";
		  //  userService.placeOrder(item,qty,date,price,pickTime);
			return new ModelAndView("confirmOrder");
		}
		/*else
		{
			return new ModelAndView("menu");
		}*/
		//return new ModelAndView("placeOrders");
	}
	
	//on confirming order
	@RequestMapping(value="/confirmOrder.html",method=RequestMethod.POST)
	public void sendConfirmEmail(HttpServletRequest request, HttpServletResponse response)
	{
		//UserService us=new UserService();
		CookQueueService cqs=new CookQueueService();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		String pickup_date=request.getParameter("pickup_date");
		String pickup_time=request.getParameter("pickup_time");
		
		System.out.println("date:"+pickup_date+","+pickup_time);
		
		Date d=null;
		Date t=null;
		
		try {
			 d=dateFormat.parse(pickup_date);
			 t=timeFormat.parse(pickup_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cqs.check(d);
		
		HttpSession s=request.getSession();
		String email=s.getAttribute("email").toString();
		System.out.println("email to:"+email);
		
		//us.sendmail(email, "CONFIRM");
		
	}
	
	
	
}
