<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants"%>

<%
	org.json.JSONArray activos = (JSONArray)request.getAttribute(Constants.PARAMETER_KEY_ACTIVOS);
	
	String invId = (String)request.getAttribute(Constants.PARAMETER_KEY_ID);
	String filename = "activo-"+invId+".xls";
	
	response.setContentType("application/vnd.ms-excel");
 	response.setHeader("Content-Disposition", "inline; filename=" + filename);
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
	<head></head>

	<body>
	
		<%if(activos!=null){%>
		
		    <table align="left" border="2">
        	<thead>
            	<tr bgcolor="lightgreen">
		                <th align="center">Proyecto</th>
		                <th align="center">Nombre</th>
		                <th align="center">Codigo VASA</th>
		                <th align="center">Codigo cliente</th>
		                <th align="center">Marca</th>
		                <th align="center">Modelo</th>
		                <th align="center">Serial</th>
		                <th align="center">Tipo</th>
		                <th align="center">Fabricante</th>
		                <th align="center">Pais origen</th>
		                <th align="center">Codigo planta</th>
		                <th align="center">Capacidad</th>
		                <th align="center">Area</th>
		                <th align="center">Edificio</th>
		                <th align="center">Departamento</th>
		                <th align="center">Piso</th>
		                <th align="center">Long</th>
		                <th align="center">L</th>
		                <th align="center">A</th>
		                <th align="center">H</th>
		                <th align="center">Accionado(a) por</th>
		                <th align="center">De</th>
		                <th align="center">Completo con</th>
		                <th align="center">Recubierto con</th>
		                <th align="center">Construido con</th>
		                <th align="center">e</th>
		                <th align="center">Montado sobre</th>
		                <th align="center">Condicion</th>
		                <th align="center">Funcionando</th>
		                <th align="center">Fluido</th>
		                <th align="center">Entrada</th>
		                <th align="center">Salida</th>
		                <th align="center">Estimado</th>
		           	</tr>
			
        	</thead>
		 	<tbody>

			<%for(int i = 0; i < activos.length(); i++){
				
				org.json.JSONObject activo = activos.getJSONObject(i);%>
				
           <tr >
                <td align="center"><%=invId%></td>
                <td align="center"><%=getJsonVal("nombre",activo)%></td>
                <td align="center"><%=getJsonVal("codigoVasa",activo)%></td>
                <td align="center"><%=getJsonVal("codigoCliente",activo)%></td>
                <td align="center"><%=getJsonVal("marca",activo)%></td>
                <td align="center"><%=getJsonVal("modelo",activo)%></td>
                <td align="center"><%=getJsonVal("serial",activo)%></td>
                <td align="center"><%=getJsonVal("tipo",activo)%></td>
                <td align="center"><%=getJsonVal("fabricante",activo)%></td>
                <td align="center"><%=getJsonVal("paisOrigen",activo)%></td>
                <td align="center"><%=getJsonVal("codigoPlanta",activo)%></td>
                <td align="center"><%=getJsonVal("capacidad",activo)%></td>
                <td align="center"><%=getJsonVal("area",activo)%></td>
                <td align="center"><%=getJsonVal("edificio",activo)%></td>
                <td align="center"><%=getJsonVal("departamento",activo)%></td>
                <td align="center"><%=getJsonVal("piso",activo)%></td>
                <td align="center"><%=getJsonVal("dimensiones",activo)%></td>
                <td align="center"><%=getJsonVal("dimensionesL",activo)%></td>
                <td align="center"><%=getJsonVal("dimensionesA",activo)%></td>
                <td align="center"><%=getJsonVal("dimensionesH",activo)%></td>
                <td align="center"><%=getJsonVal("accionadoList",activo)%></td>
                <td align="center"><%=getJsonVal("accionadoDe",activo)%></td>
                <td align="center"><%=getJsonVal("completo",activo)%></td>
                <td align="center"><%=getJsonVal("recubiertoCon",activo)%></td>
                <td align="center"><%=getJsonVal("construidoCon",activo)%></td>
                <td align="center"><%=getJsonVal("construidoE",activo)%></td>
                <td align="center"><%=getJsonVal("montadoSobre",activo)%></td>
                <td align="center"><%=getJsonVal("condicion",activo)%></td>
                <td align="center"><%=getJsonVal("funcionando",activo)%></td>
                <td align="center"><%=getJsonVal("fluido",activo)%></td>
                <td align="center"><%=getJsonVal("entrada",activo)%></td>
                <td align="center"><%=getJsonVal("salida",activo)%></td>
                <td align="center"><%=getJsonVal("estimado",activo)%></td>
            </tr>
			
			<%}%>
			</tbody>
		
		 </table>
		<%}%>               
	               
    
       
   


 
	</body>

</html>