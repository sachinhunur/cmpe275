package com.project.model;

import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
@Entity
public class Menu {
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order_details order1;
	
	public Order_details getOrder() {
		return order1;
	}
	public void setOrder(Order_details order1) {
		this.order1 = order1;
	}
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Range(min=0, max=999)
	private int menu_id;
	private String menu_status;
	private String item_name;
	private String unitPrice;//eg:$5.5
	@Enumerated(EnumType.STRING)
    private Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		  this.category = category;
		 }
	@Range(min=0)
	private int calories;
	private Currency unit_price;//doubtful
	@Range(min=1, max=10)
	private int preparation_time;
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_status() {
		return menu_status;
	}
	public void setMenu_status(String menu_status) {
		this.menu_status = menu_status;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public Currency getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Currency unit_price) {
		this.unit_price = unit_price;
	}
	public int getPreparation_time() {
		return preparation_time;
	}
	public void setPreparation_time(int preparation_time) {
		this.preparation_time = preparation_time;
	}
}
