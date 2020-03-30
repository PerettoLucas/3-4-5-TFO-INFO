<?php 
    if ($_GET["name"] == null || $_GET["wert"] == null) {
        echo "<h1><b> Neues Cookie: Fehler </b></h1>";
		echo "<p id=\"failOnCookie\" style=\"
				position: absolute;
				margin-top: 10%;
				margin-left: 37%;\">
				<a href=\"index.php?id=2&\"> Zurück </a></p>";
    }
	if($_GET["name"] == Null) 
		echo "Name des Cookies nicht gesetzt <br><br>";
	if($_GET["wert"] == Null)
		echo "Wert des Cookies nicht gesetzt<br><br>";
	else{
		$expire = 0;
		switch($_GET["selectMenu"]){
			case "7D":
				$expire = 3600*24*7;
				break;
			case "1H":
				$expire = 3600;
				break;
			case "5M":
				$expire = 300;
				break;
			case "1M":
				$expire = 60;
				break;
			case "30S":
				$expire = 30;
				break;
			case "death":
				$expire = 1;
				break;
			default: 
				break;
		}
		setcookie($_GET["name"], $_GET["wert"], time() + $expire );

		echo "<h1><b> Cookie erfolgreich hinzugefügt </b></h1>";

		echo "<a href=\"index.php?id=1&\"> Zurück </a>";

	}
?>

