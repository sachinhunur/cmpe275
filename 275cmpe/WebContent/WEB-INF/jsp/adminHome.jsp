<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
  div.image{
  position: relative;
  left:400px;
    width: 50%;
    height:50px
  }
  body{
  background-color:black;
  color:white;
  }
  #container {
  position: relative;
  width: 450px;
  margin: 0 auto;
}

#overlay{
   position: absolute;
  left: 0;
  top: 0;
  width: 200%;
  z-index: 1;
  color: rgba(130, 130, 130, 0.5);
  font-size: 30px;
  text-align: left;
  line-height: 150px;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
  color:white;
  }
 
  </style> 
   </head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Khana Lelo</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="welcome">Home</a></li>
      <li><a href="#one">Add Item</a></li>
      <li><a href="#two">Remove Item</a></li>
      <li><a href="#three">Activate Item</a></li>
      <li><a href="#four">Edit Item</a></li>
    </ul>
  </div>
</nav>


<div id="container">
 <img src="./images/main.jpg" class="image" />
  <div id="overlay">Welcome to the Admin Page!</div>
  <svg width="620" height="150" viewBox="0 0 450 100" style="position: absolute; top: 0;">
    <defs>
      <filter id="blur">
        <feGaussianBlur in="SourceGraphic" stdDeviation="3" />
      </filter>
    </defs>
    <image filter="url(#blur)" xlink:href="./images/main.jpg" x="0" y="0" height="300px" width="450px" />
  </svg>
</div>
	<br>
	<h1> <font size="5" color="white">Add Menu Item</font></h1> 
	
	<form role="form" method="POST" action="addMenu.html" enctype="multipart/form-data" id="one">
	<div class="form-group">
   <table>
    <tr>
        <td>Name: <input class="form-control" type="text" name="itemName" required></td>
       
    </tr>
     <tr>
        <td>Price: <input class="form-control" type="text" name="price" required></td>
       
    </tr>
     <tr>
        <td>Category: <input class="form-control" type="text" name="category" required></td>
       
    </tr>
    <tr>
        <td>Image Upload: <input type="file" name="pic" accept="image/*"></td>
       
    </tr>
     <tr>
        <td>Calories: <input class="form-control" type="text" name="calories" required></td>
       
    </tr>
      <tr>
        <td>Preperation Time: <input class="form-control" type="text" name="time" required></td>
       
    </tr>
      <tr>
        <td>Status: <input class="form-control" type="text" name="status" required></td>
       
    </tr>
     
    <tr>
        <td colspan="2">
            <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Add Menu Item"/>
	   </td>
    </tr>
       
</table>  
</div>
</form>

<form role="form" method="POST" action="deactivate.html" id="two">


Name: <input class="form-control" type="text" name="itemName" required>
<input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Deactivate Item"/>
	   

</form>
<form role="form" method="POST" action="activate.html" id="three">


Name: <input class="form-control" type="text" name="itemName" required>
<input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Activate Menu Item"/>
	   

</form>
<form role="form" method="POST" action="editMenu.html" id="four">


Name: <input class="form-control" type="text" name="id" required>
<input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Edit Menu Item"/>
	   

</form>
</body></html>