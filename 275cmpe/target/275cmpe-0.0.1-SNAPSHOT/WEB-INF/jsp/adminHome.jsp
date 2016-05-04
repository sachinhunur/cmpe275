<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  </head>
<body>
<div class="col-sm-4" style="text-align:center">
	<br>
	<p> Add Menu Item
	<form role="form" method="POST" action="addMenu.html">
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
        <td>Calories: <input class="form-control" type="text" name="calories" required></td>
       
    </tr>
      <tr>
        <td>Preperation Time: <input class="form-control" type="text" name="time" required></td>
       
    </tr>
      <tr>
        <td>Status: <input class="form-control" type="text" name="status" required></td>
       
    </tr>
     <tr>
        <td>Image Upload: <input type="file" name="pic" accept="image/*"></td>
       
    </tr>
    <tr>
        <td colspan="2">
            <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Add Menu Item"/>
	   </td>
    </tr>
    <tr>
        <td colspan="2">
            <img src="/275cmpe/images/sachin.jpg"   style="width:304px;height:228px;">

	   </td>
    </tr>
   
    
</table>  
</div>
</form>
</div>
<div class="col-sm-4" style="text-align:center">

Name: <input class="form-control" type="text" name="itemName" required>
<input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Deactivate Item"/>
	   
</div>

<div class="col-sm-4" style="text-align:center">

Name: <input class="form-control" type="text" name="itemName" required>
<input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Activate Menu Item"/>
	   
</div>
</body></html>