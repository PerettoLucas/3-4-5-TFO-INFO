<?php
	$date = new DateTime();
	setcookie("LETZTERZUGRIFF",$date->format("d.m.Y H:i:s"), time() + 3600 * 24 * 7);
	
	if(!isset($_COOKIE["LETZTERZUGRIFF"]))
	echo "Zum ersten Mal auf der Seite";
	else 
	echo "Ihr letzter Zugriff erfolgte :" . $_COOKIE["LETZTERZUGRIFF"];

?>