<%@ page import="org.json.JSONArray,org.json.JSONObject"%>

<%
	org.json.JSONArray checkin = (JSONArray)request.getAttribute("checkin");
	
	String consultarPage="checkin";
	
%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>VaSa</title>
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
 
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript" src="resources/js/jquery.js"></script>          
	<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
  
	<script type="text/javascript">
		var checkins=<%=checkin%>;
	</script>
    <script type="text/javascript" src="resources/js/checkin.js"></script>



</head>

<body>
	<%@ include file="header.jsp" %>


<div class="wrap">
  <h1>Checkin</h1>
  
  <div class="checkin_box">
	<div class="date-box">
	      <form name="consultar-form" id="consultar-form" action="<%=consultarPage%>" method="get"> 
			<p>Fecha: <input type="text" name="datepicker" id="datepicker" /></p>
			<input type="submit" value="Consultar">
			
		</form>
	</div>
	<div id="map_canvas"></div>

	<div class="checkin_list_box">
		<ul id="checkin_list">
			<li>Usuarios encontrados:</li>
		</ul>
	</div>
	
  </div>
</div>
</body>
</html>