<%@ page import="java.io.*"%>
<%
byte[] mediaData =  (byte[])request.getAttribute("media");

try {
	
	response.setContentType("application/force-download");


	OutputStream o = response.getOutputStream();

	o.write(mediaData);

	o.flush();

	o.close();

} catch (Exception e) {

	out.println("Unable To continue");

	out.println(e.getMessage());

	return;

}
%>