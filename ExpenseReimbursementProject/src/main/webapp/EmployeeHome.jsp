<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<style>
 @font-face {
font-family: dragonballFont;
src: url(Saiyan-Sans.ttf);
}    
	body{
	text-align:center;
	background-color:#FFD7C1;
	font-family: dragonballFont;}
	#logoutBtn{
	float:left;}
	#addReimbursementBtn,#allreimbursementsBtn{
		background-color:#E59982;
		height:60px;
		width:150px;
		border:dotted black 5px;
		margin-top:10px;
		
	}
	input{
	background-color:black;
	color:#E59982;
	height:30px;
	width:200px;
	border-radius:10%;}
	table{
	position:relative;
	left:250px;
	background-color:black;
	color:white;}
	td{
	border:solid #E59982 3px;}
		#buuRunning{
	position:relative;
	left:0;
	top:310px;
	height:25%;
	width:25%;
	float:left;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
		<%
			if(session.getAttribute("username")==null){
				response.sendRedirect("index.html");
			}
		%>
		<button id="logoutBtn" class="btn-default">Log out</button>
        <h2 id="usernameHeading"></h2>
        <input id="descInput" type="text" placeholder="Description">
		<input id="AmountInput" type="number" placeholder="Amount"><br />
		<button id="addReimbursementBtn">Add Reimbursement</button><br />
        <button id="allreimbursementsBtn">Get All Reimbursements</button>
        <table id="ReimbursementTable" class="table table-hover"style="visibility: hidden;">
</table>
<img src="buuRun.gif" alt="running"id="buuRunning">
</body>
<script>
document.getElementById("allreimbursementsBtn").addEventListener("click",getAllReimbursements);
document.getElementById("addReimbursementBtn").addEventListener("click",addReimbursement);
document.getElementById("logoutBtn").addEventListener("click",LogOut);
let tableData = document.getElementById("ReimbursementTable");

function MoveRight() {
	  var elem = document.getElementById("buuRunning");   
	  var pos = 0;
	  var left = setInterval(frame, 5);
	  function frame() {
	    if (pos == 650) {
	      clearInterval(left);
			elem.style.transform = "scaleX(-1)";
			Moveleft();
	      
	    } else {
	      pos++; 
	      elem.style.left = pos + "px"; 

	    }

	  }
	}
	function Moveleft() {
		  var elem = document.getElementById("buuRunning");   
		  var pos = 650;
		  var left = setInterval(frame, 5);
		  function frame() {
		    if (pos == 0) {
		      clearInterval(left);
		      elem.style.transform = "none";
		      MoveRight();
		      
		    } else {
		      pos--; 
		      elem.style.left = pos + "px"; 

		    }

		  }
		}
MoveRight();
async function getAllReimbursements(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/showEmployeeReimbursements`);
    let Reimbursements = await httpResponse.json();
    console.log(Reimbursements);

    tableData.innerHTML = '';
    tableData.innerHTML="<thead><tr><th>Description</th><th>Amount</th><th>Status</th></tr></thead>";

    for(reimbursement of Reimbursements){
        tableData.innerHTML = tableData.innerHTML + `<tr><td>${reimbursement.description} </td> 
        <td> ${reimbursement.amount}</td> <td> ${reimbursement.status}</td> <td>
     <button ID=${reimbursement.rId} amount=${reimbursement.amount} desc="${reimbursement.description}" status= ${reimbursement.status} onclick="deleteReimbursement(this);">Delete</button> </td> </tr>`;
    }
    tableData.style =" visibility: visible;"
}

async function addReimbursement(){
    
    let Reimbursement = {
        rId:0,
        requesterId:0
    }
    // appending the properties to the object after it is created
    
    let desc = document.getElementById("descInput");
    let amt = document.getElementById("AmountInput");
    Reimbursement.description = desc.value;
    Reimbursement.amount = Number.parseInt(amt.value);

    let settings = {
        method:'POST',
        headers:{'Content-Type':'application/json'},
        body: JSON.stringify(Reimbursement)
    }
    console.log(settings.body);

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/add`, settings); 

    console.log(httpResponse)
	if($("#ReimbursementTable").is(':visible')){
		getAllReimbursements();
	}
    desc.value = '';
    amt.value = '';
}

async function showUser(){
    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/UserServlet`);
    let body = await httpResponse.text();
    console.log(body);

    document.getElementById("usernameHeading").innerHTML = `Welcome ${body}`;
}
async function deleteReimbursement(element){
    
    let reimbursement = {};
    reimbursement.rId = element.getAttribute("ID");
    
    reimbursement.description = element.getAttribute("desc");
    
    reimbursement.status = element.getAttribute("status");
    console.log(reimbursement)
    console.log(element)
    let settings = {
        method:'POST',
        headers:{'Content-Type':'application/json'},
        body: JSON.stringify(reimbursement)
    }

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/delete`, settings); 
	if($("#ReimbursementTable").is(':visible')){
		getAllReimbursements();
	}
  

}
async function LogOut(){
	let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/logout`);
	window.location.href = "http://localhost:8080/ExpenseReimbursementProject/";
}
showUser();
</script>
</html>