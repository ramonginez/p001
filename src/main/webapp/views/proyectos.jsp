<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants"%>

<%
	org.json.JSONArray proyectos = (JSONArray)request.getAttribute(Constants.PARAMETER_KEY_PROYECTOS);	
	
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
	    
	<script type="text/javascript">
	 
	 	function deleteProject(value){
	 	
	 		var confirm_box = confirm('Confirmar eliminar?');

    		if (confirm_box) {
				$('<form action="<%=Constants.REST_PATH_DELETE_PROYECTO%>" method="POST">' + 
			    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_PROYECTO_ID%>" value="' + value + '">' +
			    '</form>').appendTo($(document.body)).submit();
			}
		}
	   	
	   	function renameProyecto(oldValue,newValue){
	   		
	   		if(newValue != null && newValue.replace(/^\s+|\s+$/g,"") != "" && /^[A-Za-z0-9_]{3,20}$/.test(newValue)){
	   		
	   			var confirm_box = confirm("Esta seguro de modificar el nombre del proyecto?");

	    		if (confirm_box) {
					$('<form action="<%=Constants.REST_PATH_EDIT_PROYECTO%>" method="POST">' + 
				    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_PROYECTO_NAME%>" value="' + oldValue + '">' +
				    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_PROYECTO_NEW_NAME%>" value="' + newValue + '">' +
				    '</form>').appendTo($(document.body)).submit();
				}
	   		
	   		}else{
	   		
	   			    alert("El nuevo nombre de proyecto es invalido! ");     
	   			
	   		}
	   		
	   		
	   	}	
	   	
        function submitFormCreate()
            {
            	

            	var pname = document.getElementById("<%=Constants.PARAMETER_KEY_PROYECTO_NAME%>");
            	
            	if(pname.value == null || trim(pname.value) == ""|| !isAllowedPname(pname.value))
            	{
            		alert("Nombre de proyecto invalido! ");            	
            	}else
            	{
        	        
			        document.createform.submit();
        	        pname.disabled="disabled";
        	        

            	}

			}
			
			function trim(str) {
        		return str.replace(/^\s+|\s+$/g,"");
			}
			
			
			function isAllowedPname(value){
			
				//Supports alphabets and numbers no special characters except underscore('_') min 3 and max 20 characters. 			
				return /^[A-Za-z0-9_]{3,20}$/.test(value);
			

    		}
    		
    	</script>
            		       
</head>

<body>
	
	<%@ include file="header.jsp" %>
	
	<div class="wrap">
	  
	  <h1>Proyectos</h1>
	  
	  <div class="proyectos-search-wrap">	
	  

	  <div class="search">
	      <form name="buscar-form" id="buscar-form" action="<%=Constants.REST_PATH_SEARCH_PROYECTO%>" method="get"> 
		      <label for="buscar-proyecto">Buscar:</label>
		      <input type="text" name="<%=Constants.PARAMETER_KEY_PROYECTO_SEARCH_KEY%>" id="<%=Constants.PARAMETER_KEY_PROYECTO_SEARCH_KEY%>" />
		      <input type="submit" name="btn-buscar-proyecto" id="btn-buscar-proyecto" value="Ir"/>
		  </form>
		      
	  </div>

	  
	    
   </div>
	  <div class="proyectos-list">

	<div class="proyectos-create-box">	
		<form name="createform" id="createform" action="<%=Constants.REST_PATH_CREATE_PROYECTO%>" method="POST"> 
			<input type="text"  id="<%=Constants.PARAMETER_KEY_PROYECTO_NAME%>" name="<%=Constants.PARAMETER_KEY_PROYECTO_NAME%>"> 
			<input type="button" onclick="javascript: submitFormCreate();" name="btn-create-proyecto" id="btn-create-proyecto" value=""/>
		</form>
	</div>
	
	<table width="100%">
	
	<tbody>

	    	<%if(proyectos!=null){

				for(int i = 0; i < proyectos.length(); i++){
	
		        	String name = proyectos.getJSONObject(i).getString("name");
					
					String link = Constants.REST_PATH_LIST_INVENTARIO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", name);
			%>

  
	<tr  bgcolor="#f2f2f2" class="edit_tr">

	<td id="<%=i+1%>"  width="50%" class="edit_td">

	<span id="first_<%=i+1%>" class="text" style="display: inline; "><%=name%> </span>

	<input type="text" value="<%=name%>" class="editbox" id="first_input_<%=i+1%>" style="display: none; ">

		        <td><img src="resources/img/semaforo1.gif" alt="verde"></td>
		        <td><a id="link_<%=i+1%>" href="<%=link%>"><img src="resources/img/edit.png" alt="VaSa"></a></td>
		        <td><a id="remove_<%=i+1%>" href="#" onclick="deleteProject('<%=name%>');" ><img src="resources/img/remove.png" alt="VaSa"></a></td>
  	

<%}}%>



</tbody></table>





	  </div>
	</div>
</body>
</html>
