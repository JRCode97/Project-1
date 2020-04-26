<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Home</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<style>
 @font-face {
font-family: dragonballFont;
src: url(Saiyan-Sans.ttf);
}    
#statisticsBtn{
float:right;}
.tableTitle{
text-align:center;
font-family:dragonballFont;
color:purple;}
body{
font-family: dragonballFont;
background-color:#00022E;}
table{
background-color:green;
border:solid purple 2px; 
}
button{
background-color:grey;}
		#cellWalking{
	position:relative;
	left:0;
	top:305px;
	height:25%;
	width:25%;
	float:left;
	}
</style>
</head>
<body>
		<%
			if(session.getAttribute("username")==null){
				response.sendRedirect("index.html");
			}
		%>
		<button id="logoutBtn" class="btn-default">Log out</button>
		<button id="statisticsBtn" class="btn-default">Statistics</button>
		<h2 class="tableTitle">Pending Reimbursemenents</h2>
        <table id="pendingReimbursementTable" class="table table-hover"></table>
        <h2 class="tableTitle">Approved Reimbursemenents</h2>
        <table id="aprovedReimbursementTable" class="table table-hover"></table>
        <h2 class="tableTitle">Denied Reimbursemenents</h2>
        <table id="deniedReimbursementTable" class="table table-hover"></table>
<img src="cellWalking.gif" alt="cell walking"id="cellWalking">
</body>
<script>
let pendingTable = document.getElementById("pendingReimbursementTable");
let approvedTable = document.getElementById("aprovedReimbursementTable");
let deniedTable = document.getElementById("deniedReimbursementTable");
document.getElementById("logoutBtn").addEventListener("click",LogOut);
document.getElementById("statisticsBtn").addEventListener("click",statistics);


function MoveRight() {
	  var elem = document.getElementById("cellWalking");   
	  var pos = 0;
	  var left = setInterval(frame, 8);
	  function frame() {
	    if (pos == 600) {
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
		  var elem = document.getElementById("cellWalking");   
		  var pos = 600;
		  var left = setInterval(frame, 8);
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

    let httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementProject/api/showPending");
    let Reimbursements = await httpResponse.json();
    console.log(Reimbursements);
    if(Reimbursements!=""){
    pendingTable.innerHTML = '';
    pendingTable.innerHTML="<thead><tr><th>Description</th><th>Amount</th><th>Requester ID</th></tr></thead>";

    for(reimbursement of Reimbursements){
        pendingTable.innerHTML = pendingTable.innerHTML + `<tr><td>${reimbursement.description} </td> 
        <td> ${reimbursement.amount}</td> <td> ${reimbursement.requesterId}</td> <td>
     <button class="btn btn-success" rID=${reimbursement.rId} amount=${reimbursement.amount} desc="${reimbursement.description}" Status="${reimbursement.status}" requesterId=${reimbursement.requesterId} onclick="approveReimbursement(this);">Approve</button> </td>
     <td>
     <button class="btn btn-danger" rID=${reimbursement.rId} amount=${reimbursement.amount} desc="${reimbursement.description}" Status="${reimbursement.status}" requesterId=${reimbursement.requesterId} onclick="denyReimbursement(this);">Deny</button> </td></tr>`;
    }
    }

}
async function getDeniedReimbursements(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/showDenied`);
    let Reimbursements = await httpResponse.json();
    console.log(Reimbursements);
	if(Reimbursements!=""){
    deniedTable.innerHTML = '';
    deniedTable.innerHTML="<thead><tr><th>Description</th><th>Amount</th><th>Requester ID</th></tr></thead>";

    for(reimbursement of Reimbursements){
        deniedTable.innerHTML = deniedTable.innerHTML + `<tr><td>${reimbursement.description} </td> 
        <td> ${reimbursement.amount}</td> <td> ${reimbursement.requesterId}</td> 
</tr>`;
    }}

}
async function getApprovedReimbursements(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/showApproved`);
    let Reimbursements = await httpResponse.json();
    console.log(Reimbursements);
    if(Reimbursements!=""){
	approvedTable.innerHTML = '';
   	approvedTable.innerHTML="<thead><tr><th>Description</th><th>Amount</th><th>Requester ID</th></tr></thead>";

    for(reimbursement of Reimbursements){
        approvedTable.innerHTML = approvedTable.innerHTML + `<tr><td>${reimbursement.description} </td> 
        <td> ${reimbursement.amount}</td> <td> ${reimbursement.requesterId}</td> </tr>`;
    }
    }
}
async function approveReimbursement(element){
    
    let reimbursement = {};
    reimbursement.rId = element.getAttribute("rID");
    reimbursement.amount = element.getAttribute("amount");
    reimbursement.description = element.getAttribute("desc");
    reimbursement.status = element.getAttribute("Status");
    reimbursement.requesterId = element.getAttribute("requesterId");

    
    console.log(reimbursement)
    console.log(element)
    let settings = {
        method:'POST',
        headers:{'Content-Type':'application/json'},
        body: JSON.stringify(reimbursement)
    }

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/approve`, settings); 
    getAllReimbursements();
    getDeniedReimbursements();
    getApprovedReimbursements();
  

}
async function denyReimbursement(element){
    
    let reimbursement = {};
    reimbursement.rId = element.getAttribute("rID");
    reimbursement.amount = element.getAttribute("amount");
    reimbursement.description = element.getAttribute("desc");
    reimbursement.status = element.getAttribute("Status");
    reimbursement.requesterId = element.getAttribute("requesterId");
    console.log(reimbursement)
    console.log(element)
    let settings = {
        method:'POST',
        headers:{'Content-Type':'application/json'},
        body: JSON.stringify(reimbursement)
    }

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/deny`, settings); 
    getAllReimbursements();
    getDeniedReimbursements();
    getApprovedReimbursements();

}
async function LogOut(){
	let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject//api/logout`);
	window.location.href = `http://${window.location.hostname}:8080/ExpenseReimbursementProject/`;
}
async function statistics(){
	window.location.href = `http://${window.location.hostname}:8080/ExpenseReimbursementProject/Statistics.jsp`;
}

getAllReimbursements();
getDeniedReimbursements();
getApprovedReimbursements();
</script>
</html>