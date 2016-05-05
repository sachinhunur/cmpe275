package com.project.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
private int user_id;
private String username;
private boolean enabled; //this enabled field will be set to false. During the account verification process -if successful it will be set to true
@Column(nullable=false)
private String password;
 // @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//private Set<Order_details> order_details = new HashSet<Order_details>(0);//0 means the initial capacity

public User(){
	
}

public User(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

/*public Set<Order_details> getOrder_details() {
	return order_details;
}
public void setOrder_details(Set<Order_details> order_details) {
	this.order_details = order_details;
}*/
public String getPassword()
{
	return this.password;
}

public void setPassword(String password)
{
	this.password=password;
}
public String getUsername() {
	return this.username;
}
public void setUsername(String username) {
	this.username = username;
}
public boolean isEnabled() {
	return this.enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public int getUser_id()
{
	return user_id;
}
public void setUser_id(int user_id)
{
	this.user_id = user_id;
}

@Override
public String toString() {
	return "User [username=" + username + ", password=" + password + "]";
}



}
