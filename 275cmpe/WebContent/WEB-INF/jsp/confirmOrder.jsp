<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
window.onload=function()
{
	alert("Inside onload");
	myFunction();
}
var myFunction = function()
{
	alert("Inside function");
	var today= new Date();
	var current_hours=today.getHours();
	var current_min=today.getMinutes();
	var current_sec=today.getSeconds();
	var dd=today.getDate();
	var mm=today.getMonth()+1;
	var mm1=mm+1;
	var yy=today.getFullYear();
	if(dd<10)
	{
		dd='0'+dd;	
	}
	if(mm<10)
	{
		mm='0'+mm;	
	}
	if(mm1<10)
	{
		mm1='0'+mm1;
	}
	var date = yy+'-'+mm+'-'+dd;
	var max_date=yy+'-'+mm1+'-'+dd;

	alert("Minimum function "+date);
	var n=document.getElementById("current_date");
	n.setAttribute("type","date");
	n.setAttribute("min",date);
	n.setAttribute("max",max_date);
}
/* var n = d.toDateInputValue();
document.getElementById("current_date").innerHTML = d.toDateInputValue(); */
</script>



<form role="form" action="confirmOrder.html" method="POST">
     PICK UP Date: <input type="date" id="current_date" name="pickup_date" />
      PICK UP Time: <input type="time" name="pickup_time" min="06:01:00" max="21:00:00" />
      <button type="submit" class="btn btn-primary">Check Availability</button>
     </form>
</body>
</html>