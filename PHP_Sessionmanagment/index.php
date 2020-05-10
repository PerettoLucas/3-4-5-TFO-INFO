<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PHP_SessionManagment</title>
</head>
<body>
<a href="index.php?id=1">Benutzerliste</a>
<a href="index.php?id=2">Neuer Benutzer</a>
 
 <?php 
 if(!isset($_GET["id"]) || $_GET["id"] == "1"){
     require_once '';
 }
 
 
 
 
 ?>


</body>
</html>