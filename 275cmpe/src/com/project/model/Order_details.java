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
<<<<<<< HEAD
	@ManyToOne
=======
/*//	@ManyToOne
>>>>>>> origin/master
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
<<<<<<< HEAD
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
	private Set<Menu> menu_items = new HashSet<Menu>(0);
	public Set<Menu> getMenu_items() {
		return menu_items;
	}
	public void setMenu_items(Set<Menu> menu_items) {
		this.menu_items = menu_items;
=======
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

		return status;

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
>>>>>>> origin/master
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
<<<<<<< HEAD
	@Temporal(TemporalType.TIMESTAMP)
	private Date pickUpTime; 
	public Date getPickUpTime() {
		return this.pickUpTime;
	}
	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
=======
//	@Temporal(TemporalType.TIMESTAMP)


	

	@Temporal(TemporalType.TIME)
	private Date pickTime; 
	public Date getPickTime() {

		return this.pickTime;
	}


	

	public void setOrderTime(Date pickTime) {
		this.pickTime = pickTime;
	}
	//@Temporal(TemporalType.TIME)

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
	//private Date endTime;
>>>>>>> origin/master
	private int cook;
	
	public int getCook() {
		return this.cook;
	}
	public void setCook(int cook) {
		this.cook = cook;
	}
<<<<<<< HEAD
=======
	private String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getCook_id() {
		return cook_id;
	}
	public void setCook_id(int cook_id) {
		this.cook_id = cook_id;
	}
	@Temporal(TemporalType.TIME)
	private Date start_time;
	@Temporal(TemporalType.TIME)
	private Date end_time;
	private int cook_id;
	
>>>>>>> origin/master
}
