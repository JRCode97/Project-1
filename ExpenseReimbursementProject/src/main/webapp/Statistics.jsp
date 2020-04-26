<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Statistics Page</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<style>
 @font-face {
font-family: dragonballFont;
src: url(Saiyan-Sans.ttf);
}    div{
text-align:center;}
	body{
		background-color: black;
		color:orange;
		font-family: dragonballFont;
	}
	h1{
	color:blue;
	}
	#idCard{
	margin-top:20px;
		left:35%;
    position: relative;
    border: blue solid 4px;
	visibility:hidden;
    width:30%;
    text-align: center;
    margin-bottom:10px;
	}
	h3{
		color:blue;
	}

	#profilePic{

    width:100%;
	}
	#gokuRun{
	position:relative;
	left:0;
	top:190px;
	}
	#showID{
	color:white;}
	.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: blue;
  -webkit-transition: .4s;
  transition: .4s;
}
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}
.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}
input:checked + .slider {
  background-color: orange;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}

#employeeList{
height:25px;
width:100px;}
b{
color:white;}
#switchtext{
color:blue;
font-size:16pt;
}

</style>
</head>
<body>
		<%
			if(session.getAttribute("username")==null){
				response.sendRedirect("index.html");
			}
		%>
		<button class="btn-default" id="goBackBtn">Back to Dashboard</button>
		<div>
		<h1>Statistics Page</h1>
		<h2>Employees in Workforce</h2>
		<select id="employeeList">
		</select>
		<button id="showID" class="btn btn-default">Display ID Card</button>
		<div id="idCard">
			<img id="profilePic" >
			<p id="characterNameP"></p>
			<p id="placeOfOriginP"></p>
			<p id="speciesP"></p>
		</div>
		<p id="switchtext">General <b>/</b> <span style="color:orange;">Workforce</span></p>
		<label class="switch">
  <input id="genWF" type="checkbox">
  <span class="slider round"></span>
</label>

		<h3 id="highestRequester"></h3>
		<h3 id="avgReimbursement"></h3>
		<h3 id="amtOfApproved"></h3>
		<h3 id="amtOfDenied"></h3>
		<h3 id="totalReimbursements"></h3>
</div>
    	<img src="gokurun.gif" href="run" id="gokuRun">

</body>
<script>
let highestRequester = document.getElementById("highestRequester");
let avgReimbursement = document.getElementById("avgReimbursement");
let amtOfApproved = document.getElementById("amtOfApproved");
let amtOfDenied = document.getElementById("amtOfDenied");
let totalReimbursements = document.getElementById("totalReimbursements");
let goBackBtn = document.getElementById("goBackBtn");
let charactername = document.getElementById("characterNameP");
let characterorigin = document.getElementById("placeOfOriginP");
let species = document.getElementById("speciesP");
let status = document.getElementById("lifeStatusP");
let charImg = document.getElementById("profilePic");
let idCard = document.getElementById("idCard");

function MoveRight() {
  var elem = document.getElementById("gokuRun");   
  var pos = 0;
  var left = setInterval(frame, 5);
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
	  var elem = document.getElementById("gokuRun");   
	  var pos = 600;
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

goBackBtn.addEventListener("click",backToHome);
let employeeList = document.getElementById("employeeList");
let showID = document.getElementById("showID");
showID.addEventListener("click",loadIdCard);

let checkbox = document.getElementById("genWF");
checkbox.addEventListener("click",switchGeneralToWF);
async function getHighestRequester(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/highestRequester`);
    let most = await httpResponse.json();
    highestRequester.style.color="blue";
    highestRequester.innerHTML ="";
    highestRequester.innerHTML = `The person with the most requests is ${most}`;

    }
async function getAvgReimbursement(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/avgReimbursement`);
    let avg = await httpResponse.json();
    avgReimbursement.style.color="blue";
    avgReimbursement.innerHTML = "";
    avgReimbursement.innerHTML = `The average Reimbursement amount: ${avg}`;

    }
async function getAmtOfApproved(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/amtOfApproved`);
    let approved = await httpResponse.json();
    amtOfApproved.style.color="blue";
    amtOfApproved.innerHTML = "";
    amtOfApproved.innerHTML = `The amount of approved reimbursements ${approved}`;
    }
async function getAmtOfDenied(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/amtOfDenied`);
    let denied = await httpResponse.json();
    amtOfDenied.style.color="blue";
    amtOfDenied.innerHTML = "";
    amtOfDenied.innerHTML = `The amount of denied reimbursements ${denied}`;
    }
async function getTotalReimbursements(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/totalReimbursements`);
    let tot = await httpResponse.json();
    totalReimbursements.style.color="blue";
    totalReimbursements.innerHTML = "";
    totalReimbursements.innerHTML = `The total amount of reimbursements ${tot}`;
    } 
async function getHighestRequesterofWorkForce(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/highestRequesterinWorkForce`);
    let most = await httpResponse.json();
    highestRequester.style.color="orange";
    highestRequester.innerHTML = "";
    highestRequester.innerHTML = `The person with the most requests in work force is ${most}`;

    }
async function getAvgReimbursementofWorkForce(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/avgReimbursementinWorkForce`);
    let avg = await httpResponse.json();
    avgReimbursement.style.color="orange";
    avgReimbursement.innerHTML = "";
    avgReimbursement.innerHTML = `The average Reimbursement amount in work force: ${avg}`;

    }
async function getAmtOfApprovedofWorkForce(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/amtOfApprovedinWorkForce`);
    let approved = await httpResponse.json();
    amtOfApproved.style.color="orange";
    amtOfApproved.innerHTML = "";
    amtOfApproved.innerHTML = `The amount of approved reimbursements in work force: ${approved}`;
    }
async function getAmtOfDeniedofWorkForce(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/amtOfDeniedinWorkForce`);
    let denied = await httpResponse.json();
    amtOfDenied.style.color="orange";
    amtOfDenied.innerHTML = "";
    amtOfDenied.innerHTML = `The amount of denied reimbursements in work force: ${denied}`;
    }
async function getTotalReimbursementsofWorkForce(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/totalReimbursementsinWorkForce`);
    let tot = await httpResponse.json();
    totalReimbursements.style.color="orange";
    totalReimbursements.innerHTML = "";
    totalReimbursements.innerHTML = `The total amount of reimbursements in work force: ${tot}`;
    } 
    
async function loadIdCard(){
	 let chosenCharacter = employeeList.options[employeeList.selectedIndex].value.replace(" ","");
	let characterapiResponse = await fetch(`https://dragon-ball-api.herokuapp.com/api/character/${chosenCharacter}`);
	let characterDetails = await characterapiResponse.json();
	if(characterDetails!=null){
	charactername.innerHTML = `Name: ${characterDetails.name}`;
	characterorigin.innerHTML = `Origin Planet: ${characterDetails.originPlanet}`;
	species.innerHTML = `Species: ${characterDetails.species}`
	charImg.src = `${characterDetails.image}`
		idCard.style.visibility = "visible";
	}

}
function switchGeneralToWF(){
	if(checkbox.checked){
		getHighestRequesterofWorkForce();
		getAvgReimbursementofWorkForce();
		getAmtOfApprovedofWorkForce();
		getAmtOfDeniedofWorkForce();
		getTotalReimbursementsofWorkForce();}
	else{
		getHighestRequester();
		getAvgReimbursement();
		getAmtOfApproved();
		getAmtOfDenied();
		getTotalReimbursements();
	}
		

}
async function backToHome(){
	window.location.href = `http://${window.location.hostname}:8080/ExpenseReimbursementProject/ManagerHome.jsp`;
}
async function populateEmployeeList(){
	let listResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/getEmployeesinWorkForce`);
	let characters = await listResponse.json();
	if(characters!=null){
		for(character of characters){
			let name = character.name;
			employeeList.innerHTML = employeeList.innerHTML+ `<option value="${name}">${name}</option>`;
		}
	}
	
}
MoveRight();
populateEmployeeList();
getHighestRequester();
getAvgReimbursement();
getAmtOfApproved();
getAmtOfDenied();
getTotalReimbursements();
</script>
</html>