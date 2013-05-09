<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants, com.nahmens.p001.model.dao.UsuarioDAO"%>

<%

	String changePwdPage = Constants.REST_PATH_ADMIN_SETTING_UPDATE;
	
	String createUserPage = Constants.REST_PATH_ADMIN_USER_CREATE;
	
	String deleteUserPage = Constants.REST_PATH_ADMIN_USER_DELETE;
	
	org.json.JSONArray userList = (JSONArray)request.getAttribute(Constants.PARAMETER_KEY_USER_LIST);	
	
%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>VaSa</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>  
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/setting.js"></script>
	
	<script type="text/javascript">
	 
	 	function cambio(name, pos){
	 	
	 		var confirm_box = confirm('Confirma el cambio de la nueva clave?');

    		if (confirm_box) {
		
		
	 			var domId="pwd_input_"+pos;		
				
				var newPwd = document.getElementById(domId);

            	if(newPwd.value == null || trim(newPwd.value) == "" || !isAllowedPwd(newPwd.value))
            	{
            		alert("La nueva clave no cumple los requerimientos. Debe tener entre 6 y 20 caracteres. ");       
            		
            	}else{
        	        
					$('<form action="${pageContext.request.contextPath}/<%=changePwdPage%>" method="POST">' + 
				    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_USUARIO_UNAME%>" value="' + name + '">' +
				    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_USUARIO_PWD%>" value="' + newPwd.value + '">' +
				    
				    '</form>').appendTo($(document.body)).submit();
            	}

				
			}
		}
		
		function deleteUser(value){
	 	
	 		var confirm_box = confirm('Confirmar eliminar?');

    		if (confirm_box) {
				$('<form action="${pageContext.request.contextPath}/<%=deleteUserPage%>" method="POST">' + 
			    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_USUARIO_ID%>" value="' + value + '">' +
			    '</form>').appendTo($(document.body)).submit();
			}
		}
	   	
		
		
			
		function trim(str) {
        		return str.replace(/^\s+|\s+$/g,"");
			}
			
			
		function isAllowedPwd(value){
			
			//Password supports special characters and here min length 6 max 20 charters.
			
				return /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/.test(value);
			

    	}
    	
    	function isAllowedPname(value){
			
				//Supports alphabets and numbers no special characters except underscore('_') min 3 and max 20 characters. 			
				return /^[A-Za-z0-9_]{3,20}$/.test(value);
			

    		}	
    		
    		function submitFormCreateUser()
            {
            	

            	var uname = document.getElementById("<%=Constants.PARAMETER_KEY_USUARIO_UNAME%>");

            	var upwd = document.getElementById("<%=Constants.PARAMETER_KEY_USUARIO_PWD%>");
            	
            	if(uname.value == null || trim(uname.value) == ""|| !isAllowedPname(uname.value))
            	{
            		alert("Nombre de usuario invalido! ");  
            		          	
            	}else if(upwd.value == null || trim(upwd.value) == "" || !isAllowedPwd(upwd.value))
            	{
            		alert("La nueva clave no cumple los requerimientos. Debe tener entre 6 y 20 caracteres. ");  
            		     
            	}else
            	{
        	        
			        document.createform.submit();
        	        

            	}

			}
    		
    		 
			
    		
    </script>
	
</head>

<body>
	
	<%@ include file="header.jsp" %>

<div class="wrap">
  <h1>Admin Settings</h1>


<div>	
		<form name="createform" id="createform" action="${pageContext.request.contextPath}/<%=createUserPage%>" method="POST"> 
		
		 <fieldset class="container-text" id="activo">
				<legend>Crear Nuevo Usuario</legend>
				<div>
					<label for="<%=Constants.PARAMETER_KEY_USUARIO_UNAME%>">Username:</label></br>
					<input type="text"  id="<%=Constants.PARAMETER_KEY_USUARIO_UNAME%>" name="<%=Constants.PARAMETER_KEY_USUARIO_UNAME%>"> </br>
				</div>
				<div>
					<label for="<%=Constants.PARAMETER_KEY_USUARIO_PWD%>"">Clave:</label></br>
					<input type="password"  id="<%=Constants.PARAMETER_KEY_USUARIO_PWD%>" name="<%=Constants.PARAMETER_KEY_USUARIO_PWD%>"> </br>
				</div>
		
			<div class="salvar">
		      <input type="button" onclick="javascript: submitFormCreateUser();" value ="Crear" name="btn-create-USER" id="btn-create-USER" value=""/>
			</div>
		</form>
	</div>
	
	<table width="100%">
	
	<tbody>
	
	
	    	<%if(userList!=null){

				for(int i = 0; i < userList.length(); i++){

		        	String idUser = userList.getJSONObject(i).getString(UsuarioDAO.JSON_KEY_ID);
	
		        	String name = userList.getJSONObject(i).getString(UsuarioDAO.JSON_KEY_USERNAME);
					
			%>

  
	<tr  bgcolor="#f2f2f2" class="edit_tr">

		<td id="<%=i+1%>"  width="50%" class="edit_td">
			<%=name%>
		</td>
	
		 <td>
		 	<input type="password" id="pwd_input_<%=i+1%>">
		 </td>

		 <td>
		 		<a id="btn_<%=i+1%>" href="#" onclick="cambio('<%=name%>','<%=i+1%>');" >
		 			<img src="${pageContext.request.contextPath}/resources/img/edit.png" alt="Cambiar clave">
		 		</a>
		 </td>
  	
  	 	<td>
		 		<a id="btn-del_<%=i+1%>" href="#" onclick="deleteUser('<%=idUser%>');" >
		 			<img src="${pageContext.request.contextPath}/resources/img/remove.png" alt="Delete">
		 		</a>
		 </td>

<%}}%>



</tbody></table>

 	</div> 
</body>
</html>