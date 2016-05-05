package com.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.hibernate.type.BlobType;

import com.project.DAO.menuDAO;
import com.project.model.Category;
import com.project.model.Menu;

public class menuService {

		Menu menu=new Menu();
		menuDAO menuData= new menuDAO();
		public static int i=1;
		
	
		
		public void addMenu(String itemName,String price,String category,String status,int prepTime,int calories,File image)
		{
			byte[] bFile = new byte[(int) image.length()];
			try {
			     FileInputStream fileInputStream = new FileInputStream(image);
			     //convert file into array of bytes
			     fileInputStream.read(bFile);
			     fileInputStream.close();
		        } catch (Exception e) {
			     e.printStackTrace();
		        }
			System.out.println("in service"+itemName);
			menu.setCalories(calories);
			menu.setCategory(category);
			menu.setItem_name(itemName);
			menu.setMenu_id(i);
			menu.setMenu_status(status);
			menu.setPreparation_time(prepTime);
			menu.setUnitPrice(price);
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

		public void deactivateMenu(String itemName) {
			// TODO Auto-generated method stub
			
			menuData.deactivateMenu(itemName);
		}
		public void activateMenu(String itemName) {
			// TODO Auto-generated method stub
			menuData.activateMenu(itemName);
		}
}
