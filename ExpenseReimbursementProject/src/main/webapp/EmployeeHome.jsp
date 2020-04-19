<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
		<%
			if(session.getAttribute("username")==null){
				response.sendRedirect("index.html");
			}
		%>

        <h2 id="usernameHeading"></h2>
        <input id="descInput" type="text" placeholder="Description">
		<input id="AmountInput" type="number" placeholder="Amount">
		<button id="addReimbursementBtn">Add Reimbursement</button>
        <button id="allreimbursementsBtn">Get All reimbursements</button>
        <table id="ReimbursementTable" style="visibility: hidden;">
    
    

</table>

</body>
<script>
document.getElementById("allreimbursementsBtn").addEventListener("click",getAllReimbursements);
document.getElementById("addReimbursementBtn").addEventListener("click",addReimbursement);
let tableData = document.getElementById("ReimbursementTable");
async function getAllReimbursements(){

    let httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementProject/api/showEmployeeReimbursements");
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
    Reimbursement.description = document.getElementById("descInput").value;
    Reimbursement.amount = Number.parseInt(document.getElementById("AmountInput").value);

    let settings = {
        method:'POST',
        headers:{'Content-Type':'application/json'},
        body: JSON.stringify(Reimbursement)
    }
    console.log(settings.body);

    let httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementProject/api/add", settings); 

    console.log(httpResponse)
	if($("#ReimbursementTable").is(':visible')){
		getAllReimbursements();
	}
}

async function showUser(){
    let httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementProject/UserServlet");
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

    let httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementProject/api/delete", settings); 
	if($("#ReimbursementTable").is(':visible')){
		getAllReimbursements();
	}
  

}
showUser();
</script>
</html>