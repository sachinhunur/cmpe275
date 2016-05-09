package com.project.controller;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Menu;
import com.project.model.Menu_temp;
import com.project.model.User;
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
		Integer vcode=Integer.parseInt(request.getParameter("verification_code"));
		
		User user=new User();
		UserService userService=new UserService();
		
		user.setFirst_name(fname);
		user.setLast_name(lname);
		user.setEmail_id(email);
		user.setPassword(password);
		user.setPhone_number(pno);
		user.setVerification_code(vcode);
		user.setEnabled(true);

		System.out.println("email:"+email+" password:"+password);
		userService.addNewUser(user);
		return "index";
	}
	
	//Taking order from user
	@RequestMapping(value="/receive_order",method=RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request,HttpServletResponse response)
	{
		//ModelAndView mv=new ModelAndView();
		String[] items=request.getParameterValues("menus");
		HashMap<String, Integer> order=new HashMap<String,Integer>();
		System.out.println("no. of items:"+items.length);
		int i=0;
		while(i<items.length)
		{
			System.out.println("items:"+items[i]);
			if(items[i].equals(""))
			{
				i++;
			}
			else
			{
				System.out.println("i="+items[i]+" , "+items[i+1]);
				order.put(items[i+1], Integer.parseInt(items[i]));
				i=i+2;
			}
		}
		String item=new String();
		String qty=new String();
		float price=0;
		for(Entry<String, Integer> e : order.entrySet())
		{
			System.out.println("e:"+e.getKey()+","+e.getValue());
			item+=","+e.getKey();
			qty+=" "+e.getValue();
			Menu menu=menuService.getMenu(e.getKey());
			price+=Float.parseFloat(menu.getUnitPrice())*e.getValue();
			
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//String s=date.toString();
		
		//String[] arrayOfString=s.split("\\s+");
		System.out.println(dateFormat.format(date)+" "+price +" "+ item+" "+qty);
		UserService userService=new UserService();
		String pickTime="10:00PM";
		userService.placeOrder(item,qty,date,price,pickTime);
		return new ModelAndView("placeOrders");
	}
	
	
	
}
