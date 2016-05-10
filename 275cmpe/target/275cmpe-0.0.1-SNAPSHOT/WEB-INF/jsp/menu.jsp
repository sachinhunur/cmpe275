<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Menu</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  
<style type="text/css">
 img{

    width: 100px;
    height:100px;
  }

</style>
  
</head>
<body>
<form role="form" action="receive_order.html" method="POST">

<div class="container">
<ul class="nav nav-pills nav-stacked col-md-4">
  <li class="active"><a href="#tab_a" data-toggle="pill">Drink</a></li>
  <li><a href="#tab_b" data-toggle="pill">Appetizer</a></li>
  <li><a href="#tab_c" data-toggle="pill">Main Course</a></li>
  <li><a href="#tab_d" data-toggle="pill">Dessert</a></li>
</ul>

<div class="tab-content col-md-8">
		
        <div class="tab-pane active" id="tab_a">
             <h4 text-align="center">Drink</h4>
   
			<table>
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Quantity</th>
		        <th>Order</th>
		        
		    </tr>
		    <c:forEach items="${drinks}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		            <td><input type="number" name="menus"></input></td>
		            <td><input type="checkbox" value="${menu.item_name}" name="menus"/></td>
		        </tr>
		    </c:forEach>
			</table>
			<h2><%= session.getAttribute("email") %></h2>
			<input type="text" value=<%= session.getAttribute("email") %> name="${session.email}" />
        </div>
        <div class="tab-pane" id="tab_b">
             <h4>Appetizer</h4>
            <table>
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Checked</th>
		        
		    </tr>
		    <c:forEach items="${appetizers}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		             <td><input type="number" name="menus"></input></td>
		            <td><input type="checkbox" value="${menu.item_name}" name="menus"/></td>
		        </tr>
		    </c:forEach>
			</table>
        </div>
        <div class="tab-pane" id="tab_c">
             <h4>Main Course</h4>
            <table>
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Checked</th>
		        
		    </tr>
		    <c:forEach items="${main_courses}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		             <td><input type="number" name="menus"></input></td>
		            <td><input type="checkbox" value="${menu.item_name}" name="menus"/></td>
		        </tr>
		    </c:forEach>
			</table>
        </div>
        <div class="tab-pane" id="tab_d">
             <h4>Dessert</h4>
         <table>
		    <tr>
		        <th>Menu Name</th>
		         <th>Calories</th>
		        <th>Image</th>
		         <th>Unit Price</th>
		        <th>Checked</th>
		        
		    </tr>
		    <c:forEach items="${desserts}" var="menu" varStatus="status">
		        <tr>
		            <td>${menu.item_name}</td>
		            <td>${menu.calories}</td>
		            <td><img src='./images/${menu.menu_id}.jpg'></img> </td>
		            <td>${menu.unitPrice}</td>
		             <td><input type="number" name="menus"></input></td>
		            <td><input type="checkbox" value="${menu.item_name}" name="menus"/></td>
		        </tr>
		    </c:forEach>
			</table>
        </div>
       
        
</div><!-- tab content -->
 
</div>
<input type="submit" name="submit_order" value="Place order"/>

</form>
<h2>${success}</h2>
<form role="form" method="POST" action="logout.html" >
	<input type="submit" name="logout" value="LOGOUT" />
</form>


</body>
</html>