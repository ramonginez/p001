<%@ page import="org.json.JSONArray,org.json.JSONObject, com.nahmens.p001.utils.Constants"%>

<%

String changePwdPage = Constants.REST_PATH_UPDATE_USUARIO;

Object err = request.getAttribute(Constants.PARAMETER_KEY_ERROR);

%>

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>VaSa</title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
		<script type="text/javascript" src="resources/js/setting.js"></script>

		<script type="text/javascript">
			function cambio() {

				var confirm_box = confirm('Confirma el cambio de la nueva clave?');

				if (confirm_box) {

					var pwd = document.getElementById("<%=Constants.PARAMETER_KEY_USUARIO_OLD_PWD%>");

					var newPwd = document.getElementById("<%=Constants.PARAMETER_KEY_USUARIO_PWD%>");

					var confirmPwd = document.getElementById("confirm-new-pwd");

					if (pwd.value == null || trim(pwd.value) == "") {
						alert("Debe insertar la clave actual");

					} else if (newPwd.value == null || trim(newPwd.value) == "" || !isAllowedPwd(newPwd.value)) {
						alert("La nueva clave no cumple los requerimientos. Debe tener entre 6 y 20 caracteres. ");

					} else if (confirmPwd.value == null || confirmPwd.value != newPwd.value) {
						alert("Error al confirmar la clave");

					} else {

						document.pwdform.submit();

					}

				}
			}

			function trim(str) {
				return str.replace(/^\s+|\s+$/g, "");
			}

			function isAllowedPwd(value) {

				//Password supports special characters and here min length 6 max 20 charters.

				return /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/.test(value);

			}

			<%if(err!=null){%>
			alert('<%=(String)err%>');
			<%}%>

		</script>

	</head>

	<body>

		<%@ include file="header.jsp" %>

		<div class="wrap">
			<h1>Setting</h1>
			<form name="pwdform" id="pwdform" action="${pageContext.request.contextPath}/<%=changePwdPage%>" method="post">

				<fieldset class="container-text clave" id="activo">
					<legend>
						Cambio de clave
					</legend>
					<div>
						<label current-pwd">Clave actual:</label></br>
						<input maxlength="25" type="password" name="<%=Constants.PARAMETER_KEY_USUARIO_OLD_PWD%>" id="<%=Constants.PARAMETER_KEY_USUARIO_OLD_PWD%>" /></br>
						</div>
						<div>
						<label for="current-pwd">Clave nueva:</label></br>
						<input maxlength="25" type="password" name="<%=Constants.PARAMETER_KEY_USUARIO_PWD%>" id="<%=Constants.PARAMETER_KEY_USUARIO_PWD%>" /></br>
					</div>
					<div>
						<label for="current-pwd">Confirmar clave nueva:</label></br>
						<input maxlength="25" type="password" name="confirm-new-pwd" id ="confirm-new-pwd"/>
						</br>
					</div>

					<div>
						<input type="button" onclick="javascript: cambio();"  value="Cambiar clave"/>
					</div>
				</fieldset>

			</form>
		</div>
	</body>
</html>