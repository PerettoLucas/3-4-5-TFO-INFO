 
function leavePage(){
	var leave = confirm("Möchten Sie wirklich diese Seite verlassen ?");
	if(leave == true){
		window.open("https://www.google.com/search?client=firefox-b-d&q=peretto+lucas");
	}
}

function hover(element) {
	  element.setAttribute('src', 'OrdnerOffen.png');
}

function unhover(element) {
	  element.setAttribute('src', 'OrdnerGeschlossen.png');
}

function getDate(){
	var d = new Date();
	divDate.innerText = "Date : " + d.toLocaleString();
	
}

function getInfo(){
	alert("This is a Cool boy minding his own Buissnes. His name is Lucas.");
}