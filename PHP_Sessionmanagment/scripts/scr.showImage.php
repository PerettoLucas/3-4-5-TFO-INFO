<?php
    
    require_once '../inc/classes/class.ValidableUser.php';
    require_once '../inc/classes/class.UserList.php';
    session_start();
    echo $_SESSION["userList"]->getImage($_GET["username"]);

?>