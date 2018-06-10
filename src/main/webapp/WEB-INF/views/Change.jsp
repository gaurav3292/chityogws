<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Reset Password</title>
<style type="text/css">
@import
	url(https://fonts.googleapis.com/css?family=Roboto:400,300,600,400italic)
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-font-smoothing: antialiased;
	-moz-font-smoothing: antialiased;
	-o-font-smoothing: antialiased;
	font-smoothing: antialiased;
	text-rendering: optimizeLegibility
}

body {
	font-family: "Roboto", Helvetica, Arial, sans-serif;
	font-weight: 100;
	font-size: 12px;
	line-height: 30px;
	color: #383838;
	background: #d9dccf
}

.container {
	max-width: 400px;
	width: 100%;
	margin: 0 auto;
	position: relative
}

#contact input[type="password"],#contact input[type="email"],#contact input[type="tel"],#contact input[type="url"],#contact textarea,#contact button[type="submit"]
	{
	font: 400 12px/16px "Roboto", Helvetica, Arial, sans-serif
}

#contact {
	background: #f0ead2;
	padding: 25px;
	margin: 150px 0;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24)
}

#contact h3 {
	display: block;
	font-size: 30px;
	font-weight: 300;
	margin-bottom: 10px
}

#contact h4 {
	margin: 5px 0 15px;
	display: block;
	font-size: 13px;
	font-weight: 400
}

fieldset {
	border: medium none !important;
	margin: 0 0 10px;
	min-width: 100%;
	padding: 0;
	width: 100%
}

#contact input[type="password"],#contact input[type="email"],#contact input[type="tel"],#contact input[type="url"],#contact textarea
	{
	width: 100%;
	border: 1px solid #ccc;
	background: #FFF;
	margin: 0 0 5px;
	padding: 10px
}

#contact input[type="password"]:hover,#contact input[type="email"]:hover,#contact input[type="tel"]:hover,#contact input[type="url"]:hover,#contact textarea:hover
	{
	-webkit-transition: border-color 0.3s ease-in-out;
	-moz-transition: border-color 0.3s ease-in-out;
	transition: border-color 0.3s ease-in-out;
	border: 1px solid #aaa
}

#contact textarea {
	height: 100px;
	max-width: 100%;
	resize: none
}

#contact button[type="submit"] {
	cursor: pointer;
	width: 100%;
	border: none;
	background: #d9dccf;
	color: #000;
	margin: 0 0 5px;
	padding: 10px;
	font-size: 15px
}

#contact button[type="submit"]:hover {
	background: #c3ce9f;
	-webkit-transition: background 0.3s ease-in-out;
	-moz-transition: background 0.3s ease-in-out;
	transition: background-color 0.3s ease-in-out
}

#contact button[type="submit"]:active {
	box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5)
}

.copyright {
	text-align: center
}

#contact input:focus,#contact textarea:focus {
	outline: 0;
	border: 1px solid #aaa
}

::-webkit-input-placeholder {
	color: #888
}

:-moz-placeholder {
	color: #888
}

::-moz-placeholder {
	color: #888
}

:-ms-input-placeholder {
	color: #888
}
</style>



</head>

<body>

	<div class="container">
		<form id="contact" action="passwordResetSuccess" method="post" name="resetPassword">
			<h3>Reset Password</h3>
			<h4>Enter New Password</h4>
			<fieldset>
				<input placeholder="New Password" type="password" name="newPassword"
					tabindex="1" required autofocus>
			</fieldset>
			<fieldset>
				<input placeholder="Confirm Password" type="password"
					name="confirmPassword" tabindex="2" required>
			</fieldset>
			<fieldset>
				<input placeholder="token" type="hidden" id="hidden"
					name="token" tabindex="2" required>
			</fieldset>
			<fieldset>
				<button name="submit" type="submit" id="contact-submit"
					data-submit="...Sending">Change Password</button>
			</fieldset>

		</form>
	</div>



</body>

<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js'></script>
<script type="text/javascript">
$(document).ready(function(){ 
						   
	var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    $('#hidden').val(vars[hash[0]]);
	
});
</script>


</html>


