<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants"%>

<%
	org.json.JSONObject result = (JSONObject)request.getAttribute(Constants.PARAMETER_KEY_JSON_RESPONSE_OBJECT);
	
	out.print(result);
	
	out.flush();
%>
