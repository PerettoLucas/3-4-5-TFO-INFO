<?php

	if($_GET["alleLoeschen"] == 1){
        foreach ($_COOKIE as $cookie => $value) {
			unset($_COOKIE[$cookie]);
			setcookie($cookie,"",time());
		}
		header("Location:index.php");
	}else{
		unset($_COOKIE[$_GET["name"]]);
		setcookie($_GET["name"],"",time());
		header("Location:index.php");
	}

?>