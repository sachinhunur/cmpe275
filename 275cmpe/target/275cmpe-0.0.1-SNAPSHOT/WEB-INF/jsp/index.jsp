<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>
Group10
</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>

<div class="col-sm-4" style="text-align:center">
	<br>
	
	<form role="form" method="POST" action="getprofilehtml.html">
	<div class="form-group">
   <table>
    <tr>
        <td>ID: <input class="form-control" type="text" name="id" required></td>
       
    </tr>
     <tr>
        <td>Password: <input class="form-control" type="text" name="password" required></td>
       
    </tr>
    <tr>
        <td colspan="2">
            <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Get profile as html"/>
	   </td>
    </tr>
</table>  
</div>
</form>
</div>

</body>
</html>