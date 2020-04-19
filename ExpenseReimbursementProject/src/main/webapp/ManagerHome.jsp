<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Home</title>
</head>
<body>
		<%
			if(session.getAttribute("username")==null){
				response.sendRedirect("index.html");
			}
		%>
        <h1>Welcome</h1>
        <h2 id="usernameHeading"></h2>

</body>
<script>
async function showUser(){
    let httpResponse = await fetch("http://localhost:/ExpenseReimbursementProject/UserServlet");
    let body = await httpResponse.text();

    document.getElementById("usernameHeading").innerHTML = body;
}
showUser();
</script>
</html>