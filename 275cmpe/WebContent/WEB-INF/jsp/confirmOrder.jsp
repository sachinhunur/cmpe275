<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form role="form" action="confirmOrder.html" method="POST">
      PICK UP Date: <input type="date" name="pickup_date" min="2016-05-11" max="2016-06-11"/>
      PICK UP Time: <input type="time" name="pickup_time" min="06:01:00" max="21:00:00" />
      <button type="submit" class="btn btn-primary">Check Availability</button>
     </form>
</body>
</html>