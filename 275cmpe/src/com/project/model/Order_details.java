package com.project.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Range;

@Entity
public class Order_details {
/*//	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
	private String menu_items = new String();
	public String getMenu_items() {
		return menu_items;
	}
	public void setMenu_items(String item) {
		this.menu_items = item;
	}
	private String qty = new String();
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	private String status = new String();
	public String getStatus() {
		return qty;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String userId = new String();
	public String getUserId() {
		return userId;
	}
	public void setUserId(String id) {
		this.userId = id;
	}
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Range(min=0)
	private int order_id;
	public int getOrder_id() {
		return this.order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
//	@Temporal(TemporalType.TIMESTAMP)
	private String pickTime; 
	public String getPickTime() {
		return this.pickTime;
	}
	public void setOrderTime(String pickTime) {
		this.pickTime = pickTime;
	}
	
	private String pickDate; 
	public String getPickDate() {
		return this.pickDate;
	}
	public void setOrderDate(String pickDate) {
		this.pickDate = pickDate;
	}
/*//	@Temporal(TemporalType.DATE)
	private Date orderDate;
//	@Temporal(TemporalType.TIMESTAMP)
	
//	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
//	@Temporal(TemporalType.TIMESTAMP)*/
	private Date endTime;
	private int cook;
	
	public int getCook() {
		return this.cook;
	}
	public void setCook(int cook) {
		this.cook = cook;
	}
	private String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
