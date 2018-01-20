<%@ page import="javax.naming.*" %>
<html>
<head>
	<meta charset="utf-8">
	<title>SOP Demo</title>
</head>
<body>
<iframe src="http://localhost:8081/google-web" name="sop"></iframe>
<script>
	document.getElementsByName('sop')[0].onload = function() {
		try {
            var frames = window.frames;
            alert('test : '+frames[0].location.hostname);
		} catch(e) {
			console.log(e);
		}}
</script>

<%
    InitialContext ctx = new InitialContext();
    try {
        Object lookup = ctx.lookup("java:comp/env/configurationPath");
        System.out.println(lookup.toString());
    }
    catch(Exception e) {
        e.printStackTrace();

    }
%>

</body>
</html>