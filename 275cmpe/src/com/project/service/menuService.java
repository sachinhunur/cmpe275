package com.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.type.BlobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.project.DAO.menuDAO;
import com.project.model.Category;
import com.project.model.Menu;
import com.project.model.Menu_temp;

public class menuService {

		@Autowired
		Menu menu=new Menu();
		static menuDAO menuData= new menuDAO();
		public static int i=1;
		
	
		
		public void addMenu(String itemName,String price,String category,String status,int prepTime,int calories,MultipartFile image)
		{
			/*byte[] bFile = new byte[(int) image.length()];
			System.out.println("hereeeeeee"+bFile);
			try {
			     FileInputStream fileInputStream = new FileInputStream("/Users/sachinhunur/Downloads/"+image);
			     //convert file into array of bytes
			     fileInputStream.read(bFile);
			     fileInputStream.close();
		        } catch (Exception e) {
			     e.printStackTrace();
		        }*/
			System.out.println(image.getOriginalFilename());
			System.out.println("in service"+itemName);
			try {
			menu.setCalories(calories);
			menu.setCategory(category);
			menu.setItem_name(itemName);
			menu.setMenu_id(i);
			menu.setMenu_status(status);
			menu.setPreparation_time(prepTime);
			menu.setUnitPrice(price);
			
				menu.setImage(image.getBytes());
				menuData.addMenu(menu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
			
		}
		public static Menu getMenu(String itemName)
		{
			//menu.setItem_name(itemName);
			Menu m=menuData.getMenu(itemName);
			return m;
		}

		public void deactivateMenu(String itemName) {
			// TODO Auto-generated method stub
			
			menuData.deactivateMenu(itemName);
		}
		public void activateMenu(String itemName) {
			// TODO Auto-generated method stub
			menuData.activateMenu(itemName);
		}
		public Menu editMenu(int id) {
			// TODO Auto-generated method stub
			return menuData.editMenu(id);
			
		}
		public void updateMenu(int id,String itemName, String price, String category, String status, int prepTime,
				int calories, byte[] bs) {
			menu.setMenu_id(id);
			menu.setCalories(calories);
			menu.setCategory(category);
			menu.setItem_name(itemName);
			menu.setMenu_id(i);
			menu.setMenu_status(status);
			menu.setPreparation_time(prepTime);
			menu.setUnitPrice(price);
			
				menu.setImage(bs);
				menuData.updateMenu(menu);
		}
		
		@Transactional
		public List<Menu> showAllMenu()
		{
			List<Menu> m=menuData.getAllMenu();
			return m;
		}
		
		
}
