function bestaetige(){
	var leave = confirm("Möchten Sie wirklich alle Cookies löschen ?");
	if(leave == true){
		var a = document.getElementById("a");
		a.href = "delete.php?alleLoeschen=1";
	}
}
function gesetzt(){
	alert("cookie wurde erfolgreich gesetzt");
}