package com.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.hibernate.type.BlobType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Category;
import com.project.model.Menu;
import com.project.service.menuService;


@Controller
public class controller {

	menuService menuService= new menuService();
	@RequestMapping(value="/getprofilehtml", method=RequestMethod.POST)
	public String adminLogin(@RequestParam("id") String id,@RequestParam("password") String password,ModelMap model) {
		 
		System.out.println(id+"wtf"+password);
		if(id.equalsIgnoreCase("sachin") && password.equalsIgnoreCase("sac"))
		{
			return "adminHome";
		}
		return "error";
		
	}
	@RequestMapping(value="/addMenu", method=RequestMethod.POST)
	public String addMenu(@RequestParam("itemName") String itemName,@RequestParam("price") String price,@RequestParam("category") String category,@RequestParam("calories") int calories,@RequestParam("time") int prepTime,@RequestParam("status") String status,@RequestParam("pic") File image,ModelMap model) {
		
		
		menuService.addMenu(itemName,price,category,status,prepTime,calories,image);
		Menu menu=menuService.getMenu(1);
		byte [] b=menu.getImage();
		
		 try{
	            FileOutputStream fos = new FileOutputStream("/Users/sachinhunur/Desktop/"+itemName+".jpg"); 
	            fos.write(b);
	            
	            fos.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		 System.out.print(b);
		 model.addAttribute("file","/Users/sachinhunur/Desktop/"+itemName+".jpg");
		return "adminHome";
		
	}
}
