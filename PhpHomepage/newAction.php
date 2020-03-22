<?php
if (!isset($_POST["firstname"])|| !isset($_POST["surname"])||!isset($_POST["email"])){
  ?>
  <h2>Fehler</h2>
  <p>Vor-, Nachname und E-Mail müssen … </p>
  <p><a href="index.php?id=2">Zurück</a>
<?php 
} else { 
  header("Location:index.php");
}
?>
