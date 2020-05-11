<?php
require_once 'class.User.php';

class ValidableUser extends User
{
    
    protected $errors;
    
    public function getErrors() 
    {
        return $this->errors;
    }
    
    public function getError($fieldname){
        if (! empty($this->errors[$fieldname])) 
           return $this->errors[$fieldname];
        else 
           return "";
        
    }
    
    public function setError($fieldname, $errormessage) {
        $this->errors[$fieldname] = $errormessage ;
    }
    
    public function validate() {
        $this->errors = null;
        
        if (empty($this->username) || $this->username == null)
            $this->setError("username", "Kein Benutzername eingegeben");
        
        if (empty($this->password) || $this->password == null) 
            $this->setError("password", "Kein Passwort eingegeben");
        
        if (empty($this->passwordRepeat) || $this->passwordRepeat == null || $this->getPassword() != $this->getPasswordRepeat())
            $this->setError("passwordRepeat", "Passwort stimmt nicht ueberein");
        
        $dateAsObject = $this->birthDate;
        $date = DateTime::createFromFormat("Y-m-d", $dateAsObject);
        if (DateTime::getLastErrors()["warning_count"] != 0 || DateTime::getLastErrors()["error_count"] != 0) {
            $this->setError("birthDate", "Ungueltiges Datum eingegeben");
        } else {
            $dateNow = new DateTime();
            $date = DateTime::createFromFormat("Y-m-d", $this->getBirthDate());
            if (DateTime::getLastErrors()["warning_count"] == 0 || DateTime::getLastErrors()["error_count"] == 0) {
                if ($date > $dateNow) {
                    $this->setError("birthDate", "Falsche Eingabe des Datums");
                }
            } else {
                $this->setError("birthDate", "Datum ist ungueltig");
            }
        }
        
        if ((empty($this->rating)) || $this->rating == null || $this->rating < 1 || $this->rating > 5  ) {
            $this->setError("rating", "Bewertung liegt nicht zwischen 1 und 5 eingegeben");
        }
        
        if (!empty($this->getImage())){
            if($this->getImageSize()> User::MAX_IMAGE_SIZE){
                $this->setError("image",  "Das Bild ist zu gross");
                $this->setImage(null);
            }
        }
    }
}
?>