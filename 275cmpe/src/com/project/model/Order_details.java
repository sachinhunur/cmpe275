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
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
	private Set<Menu> menu_items = new HashSet<Menu>(0);
	public Set<Menu> getMenu_items() {
		return menu_items;
	}
	public void setMenu_items(Set<Menu> menu_items) {
		this.menu_items = menu_items;
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
	private int cook;
	
	public int getCook() {
		return this.cook;
	}
	public void setCook(int cook) {
		this.cook = cook;
	}
}
