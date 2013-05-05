<%@ page import="com.nahmens.p001.utils.Constants"%>

<div class="header-container" >
	<div class="header-logo"> <img src="resources/img/logo.png" alt="VaSa"> </div>
	<div>
	  	<ul id="logout">
	    	<li><a href="<%=Constants.REST_PATH_LOGOUT%>">Logout</a></li>
		 </ul>
		 <ul>
		    <li id="proyecto-header-li" class="tab-active"><a href="<%=Constants.REST_PATH_LIST_PROYECTO%>">Proyectos</a></li>
		    <li id="checkin-header-li"><a href="<%=Constants.REST_PATH_CHECKIN%>">Check-In</a></li>
		    <li id="setting-header-li"><a href="<%=Constants.REST_PATH_SETTING%>">Setting</a></li>
	  	</ul>
  	</div>
</div>