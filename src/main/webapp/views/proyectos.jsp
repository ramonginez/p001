<%@ page import="org.json.JSONArray,org.json.JSONObject"%>

<%
	org.json.JSONArray proyectos = (JSONArray)request.getAttribute("proyectos");
	String proyectoPage="/inventario/proyectos/";
	String removePage="/inventario/remove/proyectos/";
	String createPage="/inventario/create/proyectos/";
	String buscarPage="/inventario/buscar/proyectos";
	
	
%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>VaSa</title>
        <link href="resources/css/style.css" type="text/css" rel="stylesheet">
        <link href="resources/css/editable.css" type="text/css" rel="stylesheet">
        
	<script type="text/javascript" src="resources/js/jquery.js"></script>   
    <script type="text/javascript" src="resources/js/editable.js"></script>
    <script type="text/javascript" src="resources/js/actions.js"></script>
	       
	 <script>
   		var _editLink = "<%=proyectoPage%>";
  		var _removeLink = "<%=removePage%>";
  		var _createLink = "<%=createPage%>";
	</script>
	       
</head>

<body>
	
	<%@ include file="header.jsp" %>
	
	<div class="wrap">
	  
	  <h1>Proyectos</h1>
	  
	  <div class="proyectos-search-wrap">	
	  

	  <div class="search">
	      <form name="buscar-form" id="buscar-form" action="<%=buscarPage%>" method="get"> 
		      <label for="buscar-proyecto">Buscar:</label>
		      <input type="text" name="buscar-proyecto" id="buscar-proyecto" />
		      <input type="submit" name="btn-buscar-proyecto" id="btn-buscar-proyecto" value="Aceptar"/>
		  </form>
		      
	  </div>

	  
	    
   </div>
	  <div class="proyectos-list">

<div class="proyectos-create-box">			
	<input type="text"  id="create_proyect"> 
	<a class="create" href="#"><img src="resources/img/add.png" alt="Agregar"></a>
</div>
<table width="100%">

<tbody>

	    	<%if(proyectos!=null){

				for(int i = 0; i < proyectos.length(); i++){
	
		        	String name = proyectos.getJSONObject(i).getString("name");
					String link = proyectoPage+name;
					String remove = removePage+name;
				%>

  
	<tr  bgcolor="#f2f2f2" class="edit_tr">

	<td id="<%=i+1%>"  width="50%" class="edit_td">

	<span id="first_<%=i+1%>" class="text" style="display: inline; "><%=name%> </span>

	<input type="text" value="<%=name%>" class="editbox" id="first_input_<%=i+1%>" style="display: none; ">

		        <td><img src="resources/img/semaforo1.gif" alt="verde"></td>
		        <td><a id="link_<%=i+1%>" href="<%=link%>"><img src="resources/img/edit.png" alt="VaSa"></a></td>
		        <td><a class="confirm" id="remove_<%=i+1%>" href="<%=remove%>"><img src="resources/img/remove.png" alt="VaSa"></a></td>
  	

<%}}%>



</tbody></table>





	  </div>
	</div>
</body>
</html>
