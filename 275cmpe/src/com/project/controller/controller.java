package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class controller {

	@RequestMapping(value="/getprofilehtml", method=RequestMethod.POST)
	public String adminLogin(@RequestParam("id") String id,@RequestParam("password") String password,ModelMap model) {
		 
		System.out.println(id+"wtf"+password);
		if(id.equalsIgnoreCase("sachin") && password.equalsIgnoreCase("sac"))
		{
			return "adminHome";
		}
		return "error";
		
	}
}
