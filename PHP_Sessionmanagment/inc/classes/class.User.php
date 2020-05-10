<?php
class User
{
  public const MAX_IMAGE_SIZE = 1048576;
  
  protected $username;
  protected $password;
  protected $passwordRepeat;
  protected $male;
  protected $birthDate;
  protected $rating;
  protected $imageType;
  protected $imageSize;
  protected $image;
  
  protected $errors;
  
  public function __construct(
   
      $username = null,
    $password = null,
    $passwordRepeat = null,
    $male = false,
    $birthDate = null,
    $rating = null,
    $imageType = null,
    $imageSize = null,
    $image = null) {
      $this->username = $username;
      $this->password = $password;
      $this->passwordRepeat = $passwordRepeat;
      $this->male = $male;
      $this->birthDate = $birthDate;
      $this->rating = $rating;
      $this->imageType = $imageType;
      $this->imageSize = $imageSize;
      $this->image = $image;
  }
  
  /**
   * @return string
   */
  public function getUsername() {
    return $this->username;
  }
  
  /**
   * @return string
   */
  public function getPassword() {
    return $this->password;
  }
  
  /**
   * @return string
   */
  public function getPasswordRepeat() {
    return $this->passwordRepeat;
  }
  
  /**
   * @return string
   */
  public function getMale() {
    return $this->male;
  }
  
  /**
   * @return string
   */
  public function getBirthDate() {
    return $this->birthDate;
  }
  
  /**
   * @return string
   */
  public function getRating() {
    return $this->rating;
  }
  
  /**
   * @return string
   */
  public function getImageType() {
    return $this->imageType;
  }
  
  /**
   * @return string
   */
  public function getImageSize() {
    return $this->imageSize;
  }
  
  /**
   * @return string
   */
  public function getImage() {
    return $this->image;
  }
  
  /**
   * @param string $username
   */
  public function setUsername($username) {
    $this->username = $username;
  }
  
  /**
   * @param string $password
   */
  public function setPassword($password) {
    $this->password = $password;
  }
  
  /**
   * @param string $passwordRepeat
   */
  public function setPasswordRepeat($passwordRepeat) {
    $this->passwordRepeat = $passwordRepeat;
  }
  
  /**
   * @param string $male
   */
  public function setMale($male) {
    $this->male = $male;
  }
  
  /**
   * @param string $birthDate
   */
  public function setBirthDate($birthDate) {
    $this->birthDate = $birthDate;
  }
  
  /**
   * @param string $rating
   */
  public function setRating($rating) {
    $this->rating = $rating;
  }
  
  /**
   * @param string $imageType
   */
  public function setImageType($imageType) {
    $this->imageType = $imageType;
  }
  
  /**
   * @param string $imageSize
   */
  public function setImageSize($imageSize) {
    $this->imageSize = $imageSize;
  }
  
  /**
   * @param string $image
   */
  public function setImage($image) {
    $this->image = $image;
  }
  
  public function deleteImage() {
    $this->image = null;
    $this->imageSize = null;
    $this->imageType = null;
  }
  
  public function setImageFromSuperglobal($image) {
    if (isset($image) && $image["error"] == UPLOAD_ERR_OK) {
      $this->imageType = $image["type"];
      $this->imageSize = $image["size"];
      $file = fopen($_FILES["image"]["tmp_name"], "rb");
      $this->image = fread($file, $_FILES["image"]["size"]);
      fclose($file);
    }
  }
}