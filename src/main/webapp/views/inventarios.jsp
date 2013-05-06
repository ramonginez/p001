<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants"%>

<%
	org.json.JSONArray activos = (JSONArray)request.getAttribute(Constants.PARAMETER_KEY_ACTIVOS);
	String project = (String)request.getAttribute(Constants.PARAMETER_KEY_PROYECTO_NAME);
	
	
	String excel ="/inventario/reporte/"+project;
	String removePage="/inventario/remove/activo/";
						
	String buscarActivoPage = Constants.REST_PATH_SEARCH_INVENTARIO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", project);
	String deleteInventario = Constants.REST_PATH_DELETE_INVENTARIO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", project);
	String reporteInventario = Constants.REST_PATH_REPORT_INVENTARIO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", project);
	String activosPage= Constants.REST_PATH_ACTIVO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", project);
	String createActivoPage=Constants.REST_PATH_CREATE_ACTIVO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", project);

%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>VaSa</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>          
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/actions.js"></script>

	<script type="text/javascript">
	 
	 	function deleteInventario(value){
	 	
	 		var confirm_box = confirm('Confirmar eliminar?');

    		if (confirm_box) {
				$('<form action="${pageContext.request.contextPath}/<%=deleteInventario%>" method="POST">' + 
			    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_INVENTARIO_ID%>" value="' + value + '">' +
			    '</form>').appendTo($(document.body)).submit();
			}
		}
		
		
		
    		
    </script>

</head>

<body>
	
	<%@ include file="header.jsp" %>

<div class="wrap">
  <h1>Activos</h1>
  <h2><%=project%></h2>
  <div class="proyectos-search-wrap">
    <div class="search">
    
      <form name="buscar-form" id="buscar-form" action="${pageContext.request.contextPath}/<%=buscarActivoPage%>" method="get"> 
	      <label for="buscar-proyecto">Buscar:</label>
	      <input type="text" name="<%=Constants.PARAMETER_KEY_INVENTARIO_SEARCH_KEY%>" id="<%=Constants.PARAMETER_KEY_INVENTARIO_SEARCH_KEY%>" />
	      <input type="submit" value="Ir"/>
	    </form>
    </div>
     <div>
      <a  href="${pageContext.request.contextPath}/<%=createActivoPage%>"><img src="${pageContext.request.contextPath}/resources/img/add.png" alt="Agregar"></a>
    </div>
    <div><a href="${pageContext.request.contextPath}/<%=reporteInventario%>"><img src="${pageContext.request.contextPath}/resources/img/excel.png" alt="Reporte"></a>
  </div>

  <div class="proyectos-list">
	




<table width="100%">

<tbody>




<%if(activos!=null){

	for(int i = 0; i < activos.length(); i++){
	
    	String name = activos.getJSONObject(i).getString("name");
    	
    	String id = activos.getJSONObject(i).getString("id");
    	
    	String link= activosPage.replace("{"+Constants.PARAMETER_KEY_ACTIVO_ID+"}", id);
    	
	%>
	<tr bgcolor="#f2f2f2" >

	<td  id="<%=i+1%>"  width="50%" >

	<span id="first_<%=i+1%>" class="text" style="display: inline; "><%=name%> </span>

	<input type="text" value="<%=name%>" class="editbox" id="first_input_1" style="display: none; ">

	</td>

	 <td><img src="${pageContext.request.contextPath}/resources/img/semaforo-activo1.gif" alt="verde"></td>
     <td><a href="${pageContext.request.contextPath}/<%=link%>" ><img src="${pageContext.request.contextPath}/resources/img/edit.png" alt="VaSa"></a></td>
     <td><a class="confirm" href="#" onclick="deleteInventario('<%=id%>');" ><img src="${pageContext.request.contextPath}/resources/img/remove.png" alt="VaSa"></a></td>

	</tr>
  	

<%}}%>



</tbody></table>
  </div>
</div>
</body>
</html>