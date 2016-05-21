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
import org.springframework.ui.ModelMap;
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
	String s= new String();
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
	@RequestMapping(value="/renderSignup", method=RequestMethod.GET)
	public String showSignUp()
	{
		System.out.println("inside render signup");
		//return new ModelAndView("signup.jsp");
		return "signupUser";
	}


	//Render Login page

		@RequestMapping(value="/renderLogin", method=RequestMethod.GET)
		public String showLogin()
		{
			System.out.println("inside render login user");
			//return new ModelAndView("signup.jsp");
			return "loginUser";
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
	public ModelAndView placeOrder(HttpServletRequest request,HttpServletResponse response, ModelMap model)
	{
	
		//ModelAndView mv=new ModelAndView();
				String array=request.getParameter("array");
				if(array.length()==0)
				{
					menuService mService=new menuService();
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
				System.out.println("items is:"+array.toString());
				
				HashMap<String, Integer> order=new HashMap<String,Integer>();
				
				String[] items=array.split(",");
				String[] item_quantity;
				for(String s: items)
				{
					System.out.println(s);
					item_quantity=s.split("-");
					order.put(item_quantity[1], Integer.parseInt(item_quantity[0]));
				}
	
		String itemSet=new String();
		String qtySet=new String();
		String priceSet=new String();
		
		float price=0;
		int prepTime=0;
		int reload_menu=0;
	
		for(Entry<String, Integer> e : order.entrySet())
		{
			System.out.println("e:"+e.getKey()+","+e.getValue());
			itemSet+=","+e.getKey();
			qtySet+=","+e.getValue();
			Menu menu=menuService.getMenu(e.getKey());
			
			{
				
				priceSet+=","+menu.getUnitPrice();
				price+=Float.parseFloat(menu.getUnitPrice())*e.getValue();
				prepTime+=menu.getPreparation_time()*e.getValue();
			}
		}
		HttpSession session=request.getSession();
		session.setAttribute("item", itemSet);
		session.setAttribute("quantity", qtySet);
		session.setAttribute("price", priceSet);
			System.out.println(prepTime+"GGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			Date time=new Date();
				
			System.out.println(dateFormat.format(date)+" "+timeFormat.format(time)+""+price +" "+ itemSet+" "+qtySet);
			CookQueueService cqs=new CookQueueService();
			s=cqs.check(dateFormat.format(date),timeFormat.format(time),prepTime);
			System.out.println("back from earliest");
			System.out.println(s);
			model.addAttribute("early",s);

			model.addAttribute("date",dateFormat.format(date));

			return new ModelAndView("confirmOrder");
		
		/*else
		{
			return new ModelAndView("menu");
		}*/
		//return new ModelAndView("placeOrders");
	}
	
	//on confirming order
	@RequestMapping(value="/confirmOrder.html",method=RequestMethod.POST)

	public ModelAndView sendConfirmEmail(HttpServletRequest request, HttpServletResponse response, ModelMap model1) throws ParseException

	{
		//UserService us=new UserService();
		ModelAndView model=new ModelAndView();
		CookQueueService cqs=new CookQueueService();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		String pickup_date=request.getParameter("pickup_date");
		String pickup_time=request.getParameter("pickup_time");
		
		System.out.println("date:"+pickup_date+","+pickup_time);
		
		Date d=null;
		Date t=null;
		Date early=null;
		try {
			 d=dateFormat.parse(pickup_date);
			 early=timeFormat.parse(s);
			 t=timeFormat.parse(pickup_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date todayDate= new Date();
		System.out.println("user date"+d);
		System.out.println("todays date"+todayDate);
		if(todayDate.compareTo(d)>0)
		{
		if(early.compareTo(t)>0)
		{
			System.out.println("not proper time");
			model1.addAttribute("early",s);
			model1.addAttribute("msg","Please enter a valid time");
			return new ModelAndView("confirmOrder");
		}
	}
		//cqs.check(d);
		
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		session.setAttribute("pickDate", dateFormat.format(d));
		session.setAttribute("pickTime", timeFormat.format(t));
		System.out.println("email to:"+email);
		
		UserService user= new UserService();
		System.out.println(session.getAttribute("email").toString());
		System.out.println(session.getAttribute("item").toString());
		System.out.println(session.getAttribute("price").toString());
		System.out.println(session.getAttribute("quantity").toString());
		System.out.println(session.getAttribute("pickDate").toString());
		System.out.println(session.getAttribute("pickTime").toString() );
		
		
		String [] menuItem=session.getAttribute("item").toString().split(",");
		String [] menuQty=session.getAttribute("quantity").toString().split(",");
		String [] menuPrice=session.getAttribute("price").toString().split(",");
		
		
		List <Menu> frontList=new ArrayList<Menu>();
	//	Menu frontM=new Menu();
		System.out.println("menu");
	
		for(int i=1;i<menuItem.length;i++)
		{	Menu frontM=new Menu();
			frontM.setItem_name(menuItem[i].toString());
			frontM.setQuantity(menuQty[i].toString());
			frontM.setUnitPrice((menuPrice[i]).toString());
			frontList.add(frontM);
			System.out.println("asasa"+frontM.getItem_name());
			System.out.println(frontM.getQuantity());
			System.out.println(frontM.getUnitPrice());
		}
		
		for(Menu m: frontList)
		{
			System.out.println("list:"+m);
		}
		//session.setAttribute("menuList", menuItem);
		//session.setAttribute("qtyList", menuQty);
		//session.setAttribute("menuPrice", menuPrice);
		//("pickDate", session.getAttribute("pickDate").toString());
		model.addObject("pickDate", session.getAttribute("pickDate").toString());
		model.addObject("pickTime", session.getAttribute("pickTime").toString());
		model.addObject("orderList",frontList);
		model.setViewName("showOrder");
		//model.addAttribute("qtyList",menuQty.toString());
		//model.addAttribute("priceList",menuPrice.toString());
	//	System.out.println("menu each item"+session.getAttribute("menuList").toString());
	//	System.out.println("qty each item"+session.getAttribute("qtyList").toString());
		user.addOrder(session.getAttribute("email").toString(),session.getAttribute("item").toString(),session.getAttribute("price").toString(),session.getAttribute("quantity").toString(),session.getAttribute("pickDate").toString(),session.getAttribute("pickTime").toString() );
		int order_id=user.findRecentOrderId();
		System.out.println("Order_id is:"+order_id);
		session.setAttribute("order_id", order_id);
		return  model;
		//us.sendmail(email, "CONFIRM");
		
	}
	
	//for cancelling order
	@RequestMapping(value="/cancelOrder.html",method=RequestMethod.POST)
	public void userCancelOrder(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		int orderId=Integer.parseInt(session.getAttribute("order_id").toString());
		UserService us=new UserService();
		us.cancelOrder(email,orderId);
		
	}
	
	
	
}
