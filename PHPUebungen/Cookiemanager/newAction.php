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
				echo"7D";
				break;
			case "1H":
				echo"1H";
				$expire = 3600;
				break;
			case "5M":
				$expire = 300;
				echo "5M";
				break;
			case "1M":
				$expire = 60;
				echo "1M";
				break;
			case "30S":
				$expire = 30;
				echo "30S";
				break;
			case "death":
				$expire = 1;
				echo "death";
				break;
			default: 
				break;
		}
		setcookie($_GET["name"], $_GET["wert"], time() + $expire );
		header("Location:index.php?id=1");
	}
	




?>