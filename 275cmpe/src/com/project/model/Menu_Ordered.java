package com.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu_Ordered {

@Id
String menu_name;
int quantity;


public Menu_Ordered()
{

}

public Menu_Ordered(String menu_name, int quantity) {
super();
this.menu_name = menu_name;
this.quantity = quantity;
}



public String getMenu_name() {
return menu_name;
}

public void setMenu_name(String menu_name) {
this.menu_name = menu_name;
}

public int getQuantity() {
return quantity;
}
public void setQuantity(int quantity) {
this.quantity = quantity;
}
}