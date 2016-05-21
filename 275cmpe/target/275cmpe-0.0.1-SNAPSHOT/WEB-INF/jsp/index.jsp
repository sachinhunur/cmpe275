<<<<<<< HEAD
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
	/* background-image:url('http://farm3.static.flickr.com/2150/2176790889_68cc469171.jpg'); */
    background-repeat: no-repeat;
   background-attachment: fixed;
    background-position: center; 
	opacity:0.9;
}

 .bs-example{
    	margin: 20px;
    }
    .resize{
        width: 200px;
    }
    
</style>
<h1 align="center">LOGIN</h1>
</head>
<body>

	<!-- <form align:"center" role="form" method="POST" action="getprofilehtml.html">
	
	<div>
		<img alt="image" src="./images/party.jpg"></img>
	</div>
	
	<div class="form-group">
   <table>
    <tr>
        <td>ID: <input class="form-control" type="text" name="id" required></td>
       
    </tr>
     <tr>
        <td>Password: <input class="form-control" type="password" name="password" required></td>
       
    </tr>
    <tr>
        <td colspan="2">
            <input class="btn btn-info btn-lg" data-toggle="modal" type="submit" value="Get profile as html"/>
	   </td>
    </tr>
	</table>  
</div>
</form> -->

<div class="bs-example" align="center">
    <form align:"center" role="form" method="POST" action="getprofilehtml.html">
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="text" class="form-control resize" id="inputEmail" name="id" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control resize" id="inputPassword" name="password" placeholder="Password">
        </div>
      
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>

<div>
<form role="form" method="post" action="sendemail.html">
<button type="submit" class="btn btn-primary">Send Email</button>
</form>
</div>


<div text-align="center">
	<form align="center">
	<h2>Don't have account?
	<a  href="rendersignup.html" >Sign Up</a>
	<!-- <form method="GET" action="usersignup.html">
		<input type="submit" value="Sign Up"class="btn btn-primary"/>
	</form> -->
	</form>
</div>
</body>
=======
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
	/* background-image:url('http://farm3.static.flickr.com/2150/2176790889_68cc469171.jpg'); */
    background-repeat: no-repeat;
   background-attachment: fixed;
    background-position: center; 
	opacity:0.9;
}

 .bs-example{
    	margin: 20px;
    }
    .resize{
        width: 200px;
    }
    
</style>

</head>
<body>


<div>
<h1 align="center">LOGIN-USER</h1>
</div>
<div class="bs-example" align="center">
    <form align:"center" role="form" method="POST" action="userlogin.html">
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="text" class="form-control resize" id="inputEmail" name="email" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control resize" id="inputPassword" name="password" placeholder="Password">
        </div>
      
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div> 

<div>
	<form method="POST" action="session.html">
		<input type="text" name="uname"/>
		<input type="submit" name="remember" value="Remember me"/>
	</form>
</form> -->

<div class="bs-example" align="center">
    <form align:"center" role="form" method="POST" action="getprofilehtml.html">
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="text" class="form-control resize" id="inputEmail" name="id" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control resize" id="inputPassword" name="password" placeholder="Password">
        </div>
      
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>

<div text-align="center">
	<form align="center">
	<h2>Don't have account?</h2>
	<a  href="rendersignup.html" >Sign Up</a>
	</form>
</div>
</body>
>>>>>>> origin/master
</html>