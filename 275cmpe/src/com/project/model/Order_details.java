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
	private Date orderTime; 
	public Date getOrderTime() {
		return this.orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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
	private Float price;
	public Float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
