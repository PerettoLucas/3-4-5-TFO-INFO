<?php
require_once 'inc/classes/class.ValidableUser.php';
require_once 'inc/classes/class.UserList.php';

const MAX_INACTIVITY_SECONDS = 10*60;

session_start();

if (isset($_SESSION["lastActivity"]) && (time() - $_SESSION["lastActivity"] > MAX_INACTIVITY_SECONDS)) {
    session_destroy();
} else {
    session_regenerate_id(true);
    $_SESSION["lastActivity"] = time();
}
?>
<!DOCTYPE html>
<html>
<head>
<title>PHP_Sessionmanagment</title>
<link rel="stylesheet" type="text/css" href="inc/css/usermanagement.css" />
</head>
<body>
	<a href="index.php?id=1">Benutzerliste</a>
	<a href="index.php?id=2">Neuer Benutzer</a>
<?php
if (! isset($_SESSION["userList"]))
  $_SESSION["userList"] = new UserList();
$userList = $_SESSION["userList"];

if (! isset($_SESSION["user"]))
  $_SESSION["user"] = new ValidableUser();
$user = $_SESSION["user"];

if (! isset($_GET["id"]) || $_GET["id"] == "1") {
  require_once 'scripts/scr.userList.php';
  
} elseif ($_GET["id"] == "2") {
  $user = new ValidableUser();
  $_SESSION["user"] = $user;
  require_once 'scripts/scr.userForm.php';
  
} elseif ($_GET["id"] == "20") {
  $user->setUsername(filter_input(INPUT_POST, "username", FILTER_SANITIZE_STRING));
  $user->setPassword(filter_input(INPUT_POST, "password", FILTER_SANITIZE_STRING));
  $user->setPasswordRepeat(filter_input(INPUT_POST, "passwordRepeat", FILTER_SANITIZE_STRING));
  $user->setMale(filter_input(INPUT_POST, "male", FILTER_VALIDATE_BOOLEAN));
  $user->setBirthDate(filter_input(INPUT_POST, "birthDate", FILTER_SANITIZE_STRING));
  $user->setRating(filter_input(INPUT_POST, "rating", FILTER_VALIDATE_FLOAT));
  $user->setImageFromSuperglobal($_FILES["image"]);
  $user->validate();
  
  if ($user->getErrors() != null || ! $userList->addUser($user))
    require_once 'scripts/scr.userForm.php';
  else
    require_once 'scripts/scr.userList.php';
} elseif ($_GET["id"] == "3") {
  if (! isset($_GET["username"]) || strlen($_GET["username"]) == 0) {
    require_once 'scripts/scr.userList.php';
  } else {
    $user = $userList->getUser($_GET["username"]);
    if (! $user)
      require_once 'scripts/scr.userList.php';
    else {
      $_SESSION["user"] = $user;
      $_SESSION["oldUsername"] = $user->getUsername();
      require_once 'scripts/scr.userForm.php';
    }
  }
  
} elseif ($_GET["id"] == "30") {
    if (isset($_POST["deleteImage"])) {
        $user->setUsername(filter_input(INPUT_POST, "username", FILTER_SANITIZE_STRING));
        $user->setPassword(filter_input(INPUT_POST, "password", FILTER_SANITIZE_STRING));
        $user->setPasswordRepeat(filter_input(INPUT_POST, "passwordRepeat", FILTER_SANITIZE_STRING));
        $user->setMale(filter_input(INPUT_POST, "male", FILTER_VALIDATE_BOOLEAN));
        $user->setBirthDate(filter_input(INPUT_POST, "birthDate", FILTER_SANITIZE_STRING));
        $user->setRating(filter_input(INPUT_POST, "rating", FILTER_VALIDATE_FLOAT));
        $user->deleteImage();
    } else {
        $user->setUsername(filter_input(INPUT_POST, "username", FILTER_SANITIZE_STRING));
        $user->setPassword(filter_input(INPUT_POST, "password", FILTER_SANITIZE_STRING));
        $user->setPasswordRepeat(filter_input(INPUT_POST, "passwordRepeat", FILTER_SANITIZE_STRING));
        $user->setMale(filter_input(INPUT_POST, "male", FILTER_VALIDATE_BOOLEAN));
        $user->setBirthDate(filter_input(INPUT_POST, "birthDate", FILTER_SANITIZE_STRING));
        $user->setRating(filter_input(INPUT_POST, "rating", FILTER_VALIDATE_FLOAT));
        $user->setImageFromSuperglobal($_FILES["image"]);
    }
  if ($userList->updateUser($_SESSION["oldUsername"], $user)) {
    unset($_SESSION["oldUsername"]);
    unset($_SESSION["user"]);
    unset($user);
    require_once 'scripts/scr.userList.php';
  } else {
    require_once 'scripts/scr.userForm.php';
  }
} elseif ($_GET["id"] = "4") {
  if (isset($_GET["username"]) && strlen($_GET["username"]) > 0) {
    $username = filter_input(INPUT_GET, "username", FILTER_SANITIZE_STRING);
    if ($userList->deleteUser($username)) {
      unset($_SESSION["user"]);
      unset($user);
    }
  }
  require_once 'scripts/scr.userList.php';
}
?>
</body>
</html>