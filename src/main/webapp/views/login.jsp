<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>VaSa</title>
        <link href="resources/css/style.css" type="text/css" rel="stylesheet">
</head>

<body onload='document.f.j_username.focus();'>
<div class="login-wrapper">
<div class="login-logo"> <img src="resources/img/logo.png" alt="VaSa"> </div>
<div class="login-form">
  <c:if test="${not empty error}">
    <div class="errorblock"> Intento fallido, tratar nuevamente.<br />
      Caused :
      ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </div>
  </c:if>
  <form name='f' action="<c:url value='j_spring_security_check' />"
        method='POST'>
    <div class="label">Usuario:
      </label>
    </div>
    <div>
      <input type="text" name="j_username" maxlength="100" />
    </div>
    <div class="label">Clave:</div>
    <div>
        <input type="password" name="j_password" maxlength="100" />
     </div>
    <div class="btn">
      <input name="Aceptar" type="submit" value="Aceptar" />
      <input name="Borrar" type="reset" value="Borrar" />
	</div>
  
  </form>
</div>
</div>
</body>
</html>

