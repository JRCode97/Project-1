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
	#characterNameP,#placeOfOriginP,#speciesP{
		color:orange;
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
		<h2>Employee in Workforce</h2>
		<select id="employeeList">
		</select>
		<button id="showID" class="btn btn-default">Display ID Card</button>
		<div id="idCard">
			<img id="profilePic" >
			<p id="characterNameP"></p>
			<p id="placeOfOriginP"></p>
			<p id="speciesP"></p>
		</div>
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
	  var elem = document.getElementById("gokuRun");   
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

goBackBtn.addEventListener("click",backToHome);
let employeeList = document.getElementById("employeeList");
let showID = document.getElementById("showID");
showID.addEventListener("click",loadIdCard);
async function getHighestRequester(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/highestRequester`);
    let most = await httpResponse.json();
    highestRequester.innerHTML = `The person with the most requests is ${most}`;

    }
async function getAvgReimbursement(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/avgReimbursement`);
    let avg = await httpResponse.json();
    avgReimbursement.innerHTML = `The average Reimbursement amount: ${avg}`;

    }
async function getAmtOfApproved(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/amtOfApproved`);
    let approved = await httpResponse.json();
    amtOfApproved.innerHTML = `The amount of approved reimbursements ${approved}`;
    }
async function getAmtOfDenied(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/amtOfDenied`);
    let denied = await httpResponse.json();
    amtOfDenied.innerHTML = `The amount of denied reimbursements ${denied}`;
    }
async function getTotalReimbursements(){

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/ExpenseReimbursementProject/api/totalReimbursements`);
    let tot = await httpResponse.json();
    totalReimbursements.innerHTML = `The total amount of reimbursements ${tot}`;
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