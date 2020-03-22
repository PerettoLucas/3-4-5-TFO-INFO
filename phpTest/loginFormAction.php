<?php 
if($_POST["username"] == "Lucas" && $_POST["password"] == "Lucas"){
    header("Location:loginSuccess.php");
}else{
    ?>
    <p> Username oder Password falsch </p>
    <?php 
}


?>