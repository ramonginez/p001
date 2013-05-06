<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants"%>

<%
	org.json.JSONObject activo = (JSONObject)request.getAttribute("activo");
	org.json.JSONArray photos = (JSONArray)request.getAttribute("photos");
	org.json.JSONArray audios = (JSONArray)request.getAttribute("audios");
	String invId = (String)request.getAttribute("id");
	
		
	String photosPage=Constants.REST_PATH_MEDIA_IMG;

	String audiosPage=Constants.REST_PATH_MEDIA_AUDIO;
	

	String linktoSave="#";

	String linktoBack="#";
	
	if(activo != null && activo.has("proyecto")){
		
		String p = (String) activo.get("proyecto");
		
		linktoBack=Constants.REST_PATH_LIST_INVENTARIO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", p);
		
		linktoSave=Constants.REST_PATH_SAVE_ACTIVO.replace("{"+Constants.PARAMETER_KEY_PROYECTO_NAME+"}", p);
		
	}
	
	
%>

<%!
public String getJsonVal(String key, JSONObject activo)throws Exception{
	
	String result = "";
	
	if(key != null && activo.has(key)){
		
		result = (String) activo.get(key);
	}
	
	return result;
}

%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>VaSa</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mediaelementplayer.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/carousel.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>          
	<script src="${pageContext.request.contextPath}/resources/js/mediaelement-and-player.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.jcarousel.min.js"></script>
	
		
	<script>
	 $(document).ready(function(){
	    
		$('#f1').submit(function() {
		
		var $form = $(this);
		
		var data = $form.serializeFormJSON();
		
	    var name = $("#nombre").val();
	    
	    if(name.length==0){
	    	
	    	alert("El campo \"Nombre\" es obligatorio ");
	    	
	    }else{
	    
	    $('#tosend').append(
	        $('<input/>')
	            .attr('type', 'hidden')
	            .attr('name', 'data')
	            .val(JSON.stringify(data))
	    );
	
		$('#tosend').submit();
	    }

		//alert(JSON.stringify(data));
				
		
		
		return false;	
		
		});
	});
	
	
	
	(function($) {
	$.fn.serializeFormJSON = function() {

	   var o = {};
	   var a = this.serializeArray();
	   $.each(a, function() {
	       if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	           o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       }
	   });
	   return o;
	};
	})(jQuery);
	
	
	jQuery(document).ready(function() {
	    jQuery('#mycarousel').jcarousel(
		{
			
		        scroll: 1,
		visible:3
		}
		);
	
	});
	
	</script>
		
	</head>
	<body>

		<%@ include file="header.jsp" %>
	
		<div class="wrap">
	
		  <h1>Activo</h1>
		
		  <%if(activo!=null){
		  %>
			
		  <h2><a href="${pageContext.request.contextPath}/<%=linktoBack%>"><%=getJsonVal("proyecto",activo)%>/</a><%=getJsonVal("nombre",activo)%></h2>
	
			
		  <form name="tosend" id="tosend" action="${pageContext.request.contextPath}/<%=linktoSave%>" method="post"></form>
		
		  <form name="f1" id="f1" action="" method="post"> 
		
			<input type="hidden" value="<%=invId%>" name="id" />
			<input type="hidden" value="<%=getJsonVal("proyecto",activo)%>" name="proyecto" />
		

			<fieldset>
				<legend>Fotos:</legend>
		
		
				
				<ul id="mycarousel" class="jcarousel-skin-tango">
				   
				<%if(photos!=null){
					
					for(int i = 0; i < photos.length(); i++){
					
			        	String id = photos.getString(i);
			        	String imgId= id+".png";
			        	
			        	String name ="photos"+i+".png"; 
			        					        	
						String link = photosPage.replace("{"+Constants.PARAMETER_KEY_ID+"}", imgId);
						
					%>
			
				 	<li><img src="${pageContext.request.contextPath}/<%=link%>"  alt="<%=name%>"></li>

			
				<%}}%>
				</ul>
				
			</fieldset>
		
		
				<fieldset>
					<legend>Observaciones Grabadas:</legend>

					<ul class="media">

					<%if(audios!=null){

						for(int i = 0; i < audios.length(); i++){

				        	String id = audios.getString(i)+".amr"; 

				        	String name ="audio"+i+".amr"; 

							
							String link = audiosPage.replace("{"+Constants.PARAMETER_KEY_ID+"}", id);
							
						%>

					 	<li>
							<a href="${pageContext.request.contextPath}/<%=link%>"><%=name%></a>
							<!--<div>
								<audio id="player2" src="${pageContext.request.contextPath}/<%=link%>" type="audio/amr" controls="controls"></audio>	
							</div>-->
						</li>

					<%}}%>
					</ul>

				</fieldset>
		
			
			<fieldset id="activo" class="container-text">
				<legend>Activo</legend>
				<div>
					<label for="nombre">Nombre de Activo:</label><br>
					<input type="text" id="nombre" name="nombre" value="<%=getJsonVal("nombre",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="codigoVasa">Codigo VASA:</label><br>
					<input type="text" name="codigoVasa" value="<%=getJsonVal("codigoVasa",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="codigoCliente">Codigo cliente:</label><br>
					<input type="text" name="codigoCliente" value="<%=getJsonVal("codigoCliente",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="marca">Marca:</label><br>
					<input type="text" name="marca" value="<%=getJsonVal("marca",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="modelo">Modelo:</label><br>
					<input type="text" name="modelo" value="<%=getJsonVal("modelo",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="serial">Serial #:</label><br>
					<input type="text" name="serial" value="<%=getJsonVal("serial",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="tipo">Tipo:</label><br>
					<input type="text" name="tipo" value="<%=getJsonVal("tipo",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="fabricante">Fabricante:</label><br>
					<input type="text" name="fabricante" value="<%=getJsonVal("fabricante",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="paisOrigen">Pais de origen:</label><br>
					<input type="text" name="paisOrigen" value="<%=getJsonVal("paisOrigen",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="codigoPlanta">Codigo de planta:</label><br>
					<input type="text" name="codigoPlanta" value="<%=getJsonVal("codigoPlanta",activo)%>" maxlength="100" /><br>
				</div>
				<div>
					<label for="capacidad">Capacidad:</label><br>
					<input type="text" name="capacidad" value="<%=getJsonVal("capacidad",activo)%>" maxlength="100" /><br>
				</div>
				</fieldset>
					<!-- ######################################################################################################### -->

				 <fieldset class="container-text">
					<legend>Ubicacion</legend>
					<div>
						<label for="area">Area:</label><br>
						<input type="text" name="area" value="<%=getJsonVal("area",activo)%>" maxlength="100" /><br>
					</div>
					<div>
						<label for="edificio">Edificio:</label><br>
						<input type="text" name="edificio" value="<%=getJsonVal("edificio",activo)%>" maxlength="100" /><br>
					</div>
					<div>
						<label for="departamento">Departamento:</label><br>
						<input type="text" name="departamento" value="<%=getJsonVal("departamento",activo)%>" maxlength="100" /><br>
					</div>
					<div>
						<label for="piso">Piso:</label><br>
						<input type="text" name="piso" value="<%=getJsonVal("piso",activo)%>" maxlength="100" /><br>
					</div>
				</fieldset>

				<!-- ######################################################################################################### -->
				
				<fieldset class="container-text">
					<legend>Dimensiones</legend>
					<div>
						<label for="dimensiones">Long:</label><br>
						<input type="text" name="dimensiones" value="<%=getJsonVal("dimensiones",activo)%>" maxlength="100" /><br>
					</div>
					<div>
						<label for="dimensionesL">L:</label><br>
						<input type="text" name="dimensionesL" value="<%=getJsonVal("dimensionesL",activo)%>" maxlength="100" /><br>
					</div>
					<div>
						<label for="dimensionesA">A:</label><br>
						<input type="text" name="dimensionesA" value="<%=getJsonVal("dimensionesA",activo)%>" maxlength="100" /><br>
					</div>
					<div>
						<label for="dimensionesH">H:</label><br>
						<input type="text" name="dimensionesH" value="<%=getJsonVal("dimensionesH",activo)%>" maxlength="100" /><br>
					</div>
					
				</fieldset>
				<fieldset class="container-text">
					<legend>Accionado(a) por:</legend>
					
					<div>
						<select name="accionadoList">
						
						<%
							String accionadoList = getJsonVal("accionadoList",activo);
							
						%>
						<option value =""></option>
				  		<option <%if(accionadoList.equals("Motor electrico")){%>selected <%}%> value ="Motor electrico">Motor electrico</option>
				  		<option <%if(accionadoList.equals("Motor reductor")){%>selected <%}%> value ="Motor reductor">Motor reductor</option>
				
					</select>
					</div>
					<div>
						<label for="accionadoDe">De:</label><br>
						<input type="text" name="accionadoDe" value="<%=getJsonVal("accionadoDe",activo)%>" maxlength="100" /><br>
					</div>	
					<div>
					
						<%
								String accionadoRadio = getJsonVal("accionadoRadio",activo);

						%>
						<input <%if(accionadoRadio.equals("HP")){%>checked <%}%> type="radio" name="accionadoRadio" value="HP" /> HP
						<input <%if(accionadoRadio.equals("KW")){%>checked <%}%> type="radio" name="accionadoRadio" value="KW" /> KW
					
					</div>	
					<div>
						<input type="text" name="accionadoPor" value="<%=getJsonVal("accionadoPor",activo)%>" maxlength="100" /><br>
					</div>								
				</fieldset>
				<fieldset class="container-text">
					<legend>Otros:</legend>
				<div>
					<label for="completo">Completo con:</label><br>
					<input type="text" name="completo" value="<%=getJsonVal("completo",activo)%>" maxlength="100" /><br>
				</div>
				
				<div>
					<label for="recubiertoCon">Recubierto con:</label><br>
					
						<%
							String recubiertoCon = getJsonVal("recubiertoCon",activo);
							
						%>
						<select name="recubiertoCon">
						<option value =""></option>
				  		<option <%if(recubiertoCon.equals("Aislante termico")){%>selected <%}%> value ="Aislante termico">Aislante termico</option>
				  		<option <%if(recubiertoCon.equals("Chaqueta aluminio")){%>selected <%}%> value ="Chaqueta aluminio">Chaqueta aluminio</option>
				  		<option <%if(recubiertoCon.equals("Doble camisa")){%>selected <%}%> value ="Doble camisa">Doble camisa</option>
					
					</select>
				</div>
				
				<div>
					<label for="construidoCon">Construido con:</label><br>
					
						<%
							String construidoCon = getJsonVal("construidoCon",activo);
							
						%>
					<select name="construidoCon">
						<option value =""></option>
				  		<option <%if(construidoCon.equals("Lamina de acero al carbono")){%>selected <%}%> 
								value ="Lamina de acero al carbono">Lamina de acero al carbono</option>
				  		<option <%if(construidoCon.equals("Lamina de acero inoxidable")){%>selected <%}%> 
								value ="Lamina de acero inoxidable">Lamina de acero inoxidable</option>
				  		<option <%if(construidoCon.equals("Concreto Armado")){%>selected <%}%> 
								value ="Concreto Armado">Concreto armado</option>
					</select>
				</div>
				
				<div>
					<label for="construidoE">e:</label><br>
					<input type="text" name="construidoE" value="<%=getJsonVal("construidoE",activo)%>" maxlength="100" /><br>
				</div>
			
				<div>
					<label for="montadoSobre">Montado sobre:</label><br>
					<%
							String montadoSobre = getJsonVal("montadoSobre",activo);
							
					%>
					<select name="montadoSobre">
						<option value =""></option>
				  		<option <%if(montadoSobre.equals("Base propia")){%>selected <%}%> 
								value ="Base propia">Base propia</option>
				  		<option <%if(montadoSobre.equals("Estructuras de soporte metalicos apoyadas al piso")){%>selected <%}%> 
								value ="Estructuras de soporte metalicos apoyadas al piso">Estructuras de soporte metalicos apoyadas al piso</option>
				  		<option <%if(montadoSobre.equals("Estructuras de soporte metalicos pernadas al piso")){%>selected <%}%> 
								value ="Estructuras de soporte metalicos pernadas al piso">Estructuras de soporte metalicos pernadas al piso</option>
					</select>
					
				</div>
			
				<div>
					<label for="condicion">Condicion:</label><br>
						<%
								String condicion = getJsonVal("condicion",activo);

						%>
					<select name="condicion">
						<option value =""></option>
				  		<option <%if(condicion.equals("0- Perfecta")){%>selected <%}%> value ="0- Perfecta">0- Perfecta</option>
				  		<option <%if(condicion.equals("5- Normal")){%>selected <%}%> value ="5- Normal">5- Normal</option>
						<option <%if(condicion.equals("10- Normal")){%>selected <%}%> value ="10- Normal">10- Normal</option>
				 		<option <%if(condicion.equals("15- Normal")){%>selected <%}%> value ="15- Normal">15- Normal</option>
				 		<option <%if(condicion.equals("20- Regular")){%>selected <%}%> value ="20- Regular">20- Regular</option>
				 		<option <%if(condicion.equals("30- Regular")){%>selected <%}%> value ="30- Regular">30- Regular</option>
				 		<option <%if(condicion.equals("40- Regular")){%>selected <%}%> value ="40- Regular">40- Regular</option>
				 		<option <%if(condicion.equals("50- Malo")){%>selected <%}%> value ="50- Malo">50- Malo</option>
				 		<option <%if(condicion.equals("55- Malo")){%>selected <%}%> value ="55- Malo">55- Malo</option>					
				 		<option <%if(condicion.equals("60- Malo")){%>selected <%}%> value ="60- Malo">60- Malo</option>					
				 		<option <%if(condicion.equals("70- Chatarra")){%>selected <%}%> value ="70- Chatarra">70- Chatarra</option>					
					</select>
				</div>
			
				<div>
					<label for="funcionando">Funcionando:</label><br>
					
						<%
								String funcionando = getJsonVal("funcionando",activo);

						%>
					<input <%if(funcionando.equals("Si")){%>checked <%}%> type="radio" name="funcionando" value="Si" /> Si
					<input <%if(funcionando.equals("No")){%>checked <%}%> type="radio" name="funcionando" value="No" /> No
				</div>	
				
				<div>
					<label for="fluido">Fluido:</label><br>
					<input type="text" name="fluido" value="<%=getJsonVal("fluido",activo)%>" maxlength="100" /><br>
				</div>

				<div>
					<label for="entrada">Entrada:</label><br>
					<input type="text" name="entrada" value="<%=getJsonVal("entrada",activo)%>" maxlength="100" /><br>
				</div>

				<div>
					<label for="salida">Salida:</label><br>
					<input type="text" name="salida" value="<%=getJsonVal("salida",activo)%>" maxlength="100" /><br>
				</div>

				<div>
					<label for="estimado">Estimado:</label><br>
					<input type="text" name="estimado" value="<%=getJsonVal("estimado",activo)%>" maxlength="100" /><br>
				</div>
				</fieldset>
				
			<div class="salvar">
				<input type="submit" value="Salvar Cambios">
				
		    </div>
		
	</form>
	<%}%>
	

</div>

<script>
$('audio,video').mediaelementplayer();
</script>	
	
</body>
</html>