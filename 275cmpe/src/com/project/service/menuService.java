package com.project.service;

import java.io.File;
import java.io.FileInputStream;
<<<<<<< HEAD
import java.io.InputStream;

import org.hibernate.type.BlobType;
=======
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.type.BlobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
>>>>>>> origin/master

import com.project.DAO.menuDAO;
import com.project.model.Category;
import com.project.model.Menu;
<<<<<<< HEAD

public class menuService {

		Menu menu=new Menu();
		menuDAO menuData= new menuDAO();
		public static int i=1;
		
	
		
		public void addMenu(String itemName,String price,String category,String status,int prepTime,int calories,File image)
		{
			byte[] bFile = new byte[(int) image.length()];
			try {
			     FileInputStream fileInputStream = new FileInputStream(image);
=======
import com.project.model.Menu_temp;
import com.project.model.Order_details;

public class menuService {

		@Autowired
		Menu menu=new Menu();
		static menuDAO menuData= new menuDAO();
		public static int i=1;
		
	
		@Transactional
		public void addMenu(String itemName,String price,String category,String status,int prepTime,int calories,MultipartFile image, String q)
		{
			/*byte[] bFile = new byte[(int) image.length()];
			System.out.println("hereeeeeee"+bFile);
			try {
			     FileInputStream fileInputStream = new FileInputStream("/Users/sachinhunur/Downloads/"+image);
>>>>>>> origin/master
			     //convert file into array of bytes
			     fileInputStream.read(bFile);
			     fileInputStream.close();
		        } catch (Exception e) {
			     e.printStackTrace();
<<<<<<< HEAD
		        }
			System.out.println("in service"+itemName);
=======
		        }*/
			System.out.println(image.getOriginalFilename());
			System.out.println("in service"+itemName);
			try {
>>>>>>> origin/master
			menu.setCalories(calories);
			menu.setCategory(category);
			menu.setItem_name(itemName);
			menu.setMenu_id(i);
			menu.setMenu_status(status);
			menu.setPreparation_time(prepTime);
			menu.setUnitPrice(price);
<<<<<<< HEAD
			menu.setImage(bFile);
			menuData.addMenu(menu);
			return;
			
		}
		public Menu getMenu(int j)
		{
			//menu.setItem_name(itemName);
			Menu m=menuData.getMenu(j);
			return m;
		}

=======
			
				menu.setImage(image.getBytes());
				menu.setQuantity(q);
				menuData.addMenu(menu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
			
		}
		
		@Transactional
		public static Menu getMenu(String itemName)
		{
			//menu.setItem_name(itemName);
			Menu m=menuData.getMenu(itemName);
			return m;
		}

		@Transactional
>>>>>>> origin/master
		public void deactivateMenu(String itemName) {
			// TODO Auto-generated method stub
			
			menuData.deactivateMenu(itemName);
		}
<<<<<<< HEAD
=======
		
		@Transactional
>>>>>>> origin/master
		public void activateMenu(String itemName) {
			// TODO Auto-generated method stub
			menuData.activateMenu(itemName);
		}
<<<<<<< HEAD
=======
		
		@Transactional
		public Menu editMenu(int id) {
			// TODO Auto-generated method stub
			return menuData.editMenu(id);
			
		}
		
		@Transactional
		public void updateMenu(int id,String itemName, String price, String category, String status, int prepTime,
				int calories, byte[] bs, String q) {
			menu.setMenu_id(id);
			menu.setCalories(calories);
			menu.setCategory(category);
			menu.setItem_name(itemName);
			menu.setMenu_id(i);
			menu.setMenu_status(status);
			menu.setPreparation_time(prepTime);
			menu.setUnitPrice(price);
			menu.setQuantity(q);
			
				menu.setImage(bs);
				menuData.updateMenu(menu);
		}
		
		@Transactional
		public List<Menu> showAllMenu()
		{
			List<Menu> m=menuData.getAllMenu();
			return m;
		}
		
		@Transactional
		public void resetMenu()
		{
			menuData.resetMenu();
		}

		public ArrayList<Order_details> getHistory() {
			// TODO Auto-generated method stub
			menuDAO m=new menuDAO();
			return m.getHistory();
		}

		//UPDATE PLACED ORDER STATUS
		public void updateStatus() throws ParseException{
			// TODO Auto-generated method stub
			menuDAO m= new menuDAO();
			m.updateStatus();
		}
		
>>>>>>> origin/master
}
