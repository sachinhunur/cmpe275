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
<h1>SIGN UP</h1>
<body>
<div class="bs-example" align="center">
    <form align:"center" role="form" method="POST" action="signup.html">
    	<div class="form-group">
            <label for="inputFirstName">First Name</label>
            <input type="text" class="form-control resize" id="inputFirstName" name="first_name" placeholder="First Name">
        </div>
        
        <div class="form-group">
            <label for="inputLastName">Last Name</label>
            <input type="text" class="form-control resize" id="inputLastName" name="last_name" placeholder="Last Name">
        </div>
    
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="email" class="form-control resize" id="inputEmail" name="email" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control resize" id="inputPassword" name="password" placeholder="Password">
        </div>
        
         <div class="form-group">
            <label for="inputPhoneNumber">Phone Number</label>
            <input type="tel" class="form-control resize" id="inputPhoneNumber" name="phone_no" placeholder="Phone Number">
        </div>
        
         <div class="form-group">
            <label for="inputVerificationCode">Verification Code</label>
            <input type="number" class="form-control resize" id="inputVerificationCode" name="verification_code" placeholder="Verification Code">
        </div>
      
        <button type="submit" class="btn btn-primary">Sign Up</button>
    </form>
</div>


	
</body>
</html>