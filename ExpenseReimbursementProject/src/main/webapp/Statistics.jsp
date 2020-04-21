<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
			if(session.getAttribute("username")==null){
				response.sendRedirect("index.html");
			}
		%>
		
		<h2>Statistics Page</h2>
		<h3 id="highestRequester"></h3>
		<h3 id="avgReimbursement"></h3>
		<h3 id="amtOfApproved"></h3>
		<h3 id="amtOfDenied"></h3>
		<h3 id="totalReimbursements"></h3>
</body>
<script>
let highestRequester = document.getElementById("highestRequester");
let avgReimbursement = document.getElementById("avgReimbursement");
let amtOfApproved = document.getElementById("amtOfApproved");
let amtOfDenied = document.getElementById("amtOfDenied");
let totalReimbursements = document.getElementById("totalReimbursements");
async function getHighestRequester(){

    let httpResponse = await fetch("http://ec2-18-191-60-107.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementProject/api/highestRequester");
    let most = await httpResponse.json();
    highestRequester.innerHTML = `The person with the most requests is ${most}`;

    }
async function getAvgReimbursement(){

    let httpResponse = await fetch("http://ec2-18-191-60-107.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementProject/api/avgReimbursement");
    let avg = await httpResponse.json();
    avgReimbursement.innerHTML = `The average Reimbursement amount: ${avg}`;

    }
async function getAmtOfApproved(){

    let httpResponse = await fetch("http://ec2-18-191-60-107.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementProject/api/amtOfApproved");
    let approved = await httpResponse.json();
    amtOfApproved.innerHTML = `The amount of approved reimbursements ${approved}`;
    }
async function getAmtOfDenied(){

    let httpResponse = await fetch("http://ec2-18-191-60-107.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementProject/api/amtOfDenied");
    let denied = await httpResponse.json();
    amtOfDenied.innerHTML = `The amount of denied reimbursements ${denied}`;
    }
async function getTotalReimbursements(){

    let httpResponse = await fetch("http://ec2-18-191-60-107.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementProject/api/totalReimbursements");
    let tot = await httpResponse.json();
    totalReimbursements.innerHTML = `The total amount of reimbursements ${tot}`;
    }    
    
    

getHighestRequester();
getAvgReimbursement();
getAmtOfApproved();
getAmtOfDenied();
getTotalReimbursements();
</script>
</html>