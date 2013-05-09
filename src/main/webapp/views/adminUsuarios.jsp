<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants"%>

<%

	String changePwdPage = Constants.REST_PATH_ADMIN_SETTING_UPDATE;
	
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
	 
	 	function cambio(id, pos){
	 	
	 		var confirm_box = confirm('Confirma el cambio de la nueva clave?');

    		if (confirm_box) {
		
		
	 			var domId="pwd_input_"+pos;		
				
				var newPwd = document.getElementById(domId);

            	if(newPwd.value == null || trim(newPwd.value) == "" || !isAllowedPwd(newPwd.value))
            	{
            		alert("La nueva clave no cumple los requerimientos. Debe tener entre 6 y 20 caracteres. ");       
            		
            	}else{
        	        
					$('<form action="${pageContext.request.contextPath}/<%=changePwdPage%>" method="POST">' + 
				    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_USUARIO_ID%>" value="' + id + '">' +
				    '<input type="hidden" name="<%=Constants.PARAMETER_KEY_USUARIO_PWD%>" value="' + newPwd.value + '">' +
				    
				    '</form>').appendTo($(document.body)).submit();
            	}

				
			}
		}
		
		
			
		function trim(str) {
        		return str.replace(/^\s+|\s+$/g,"");
			}
			
			
		function isAllowedPwd(value){
			
	//Password supports special characters and here min length 6 max 20 charters.
			
				return /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/.test(value);
			

    		}	
			
    		
    </script>
	
</head>

<body>
	
	<%@ include file="header.jsp" %>

<div class="wrap">
  <h1>Admin Settings</h1>

	<table width="100%">
	
	<tbody>

	    	<%if(userList!=null){

				for(int i = 0; i < userList.length(); i++){

		        	String idUser = userList.getJSONObject(i).getString(Constants.USER_LIST_JSON_KEY_ID);
	
		        	String name = userList.getJSONObject(i).getString(Constants.USER_LIST_JSON_KEY_UNAME);
					
			%>

  
	<tr  bgcolor="#f2f2f2" class="edit_tr">

		<td id="<%=i+1%>"  width="50%" class="edit_td">
			<%=name%>
		</td>
	
		 <td>
		 	<input type="password" id="pwd_input_<%=i+1%>">
		 </td>

		 <td>
		 		<a id="btn_<%=i+1%>" href="#" onclick="cambio('<%=idUser%>','<%=i+1%>');" >
		 			<img src="${pageContext.request.contextPath}/resources/img/edit.png" alt="Cambiar">
		 		</a>
		 </td>
  	

<%}}%>



</tbody></table>

 	</div> 
</body>
</html>