<?php
class UserList
{
  protected $users = array();
  
  public function addUser($user) {
    $ret = false;
    if (isset($user)) {
      $user->validate();
      if (!$user->getError("username")) {
        if (isset($this->users[$user->getUsername()]))
          $user->setError("username", "Benutzername bereits vergeben");
      }
      if ($user->getErrors() == null) {
        $this->users[$user->getUsername()] = $user;
        $ret = true;
      }
    }
    return $ret;
  }
  
  public function updateUser($oldUsername, $user) {
    $ret = false;
    if (isset($oldUsername) && isset($this->users[$oldUsername]) && isset($user)) {
      $user->validate();
      if ($user->getErrors() == null) {
        if ($oldUsername != $user->getUserName()) {
          if (isset($this->users[$user->getUsername()])) {
            $user->setError("username", "Benutzername bereits vergeben");
          } else {
            unset($this->users[$oldUsername]);
            $this->users[$user->getUsername()] = $user;
            $ret = true;
          }
        } else {
          $this->users[$user->getUsername()] = $user;
          $ret = true;
        }
      }
    }
    return $ret;
  }
  
  public function getUsers() {
    return count($this->users) == 0 ? false : $this->users;
  }
  
  public function getUser($username) {
    $ret = false;
    if (isset($username) && isset($this->users[$username]))
      $ret = clone $this->users[$username];
    return $ret;
  }
  
  public function deleteUser($username) {
    $ret = false;
    if (isset($username) && isset($this->users[$username])) {
      unset($this->users[$username]);
      $ret = true;
    }
    return $ret;
  }
  
  public function getImage($username) {
    $ret = false;
    if (isset($username) && isset($this->users[$username])) {
      $ret = $this->users[$username]->getImage();
    }
    return $ret;
  }
}
