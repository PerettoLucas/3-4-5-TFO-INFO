<form method="post" action="index.php?id=<?=$_GET["id"][0]?>0" enctype="multipart/form-data">

	<h1>Benutzerverwaltung</h1>
	<table>
		<tr>
			<td><label>Benutzername:</label></td>
			<td><input type="text" name="username" value="<?=$user->getUsername()?>"></td>
			<td class="error"><?=$user->getError("username")?></td>
		</tr>
		
		
		<tr>
			<td><label>Passwortd:</label></td>
			<td><input type="password" name="password" value="<?=$user->getPassword()?>"></td>
			<td class="error"><?=$user->getError("password")?></td>
		</tr>
		
		
		<tr>
			<td><label>Passwort wiederholen:</label></td>
			<td><input type="password" name="passwordRepeat" value="<?=$user->getPasswordRepeat()?>"></td>
			<td class="error"><?=$user->getError("passwordRepeat")?></td>
		</tr>
		
		
		<tr>
			<td><label>Geschlecht:</label></td>
			<td>
				<input type="radio" value="false" name="male" <?php echo($user->getMale()? "":"checked")?>> Weiblich
				<input type="radio" value="true" name="male" <?php echo($user->getMale()?"checked":"")?>> Maennlich
			</td>
			<td class="error"><?=$user->getError("male")?></td>
		</tr>
		
		
		<tr>
			<td><label>Geburtsdatum:</label></td>
			<td><input type="date" name="birthDate" value="<?=$user->getBirthDate()?>"></td>
			<td class="error"><?=$user->getError("birthDate")?></td>
		</tr>
		
		
		<tr>
			<td><label>Bewertung:</label></td>
			<td><input name="rating" type="number" min="1" max="5" step="0.5"
			 	value="<?php echo $user->getRating()?>"></td>
			<td class="error"><?=$user->getError("rating")?></td>
		</tr>
		
		
		<tr>
			<td><label>Profilbild:</label></td>
			<td>
				<?php if($user->getImage() != null) { ?>
					<img width=100% src="scripts/scr.showImage.php?username=<?= $user->getUserName()?>">
				<?php } ?> 
				<br>
				<input type="file" name="image" accept="image/png,image/jpg,image/jpeg" >
				<?php if($user->getImage() != null) { ?>
					<input type="submit" name="deleteImage" value="Bild loeschen">
				<?php } ?> 
			</td>
			 <td class="error">	<?=$user->getError("image")?></td>
		</tr>
		
		
		<tr>
			<td></td>
			<td>
				<input type="submit" name="submit" value="<?php 
				if($_GET["id"] == 2 || $_GET["id"]==20){
				    echo "Hinzufuegen";
				} elseif ($_GET["id"]==3 || $_GET["id"]==30) {
				    echo "Aendern";
				}
				?>">
				<input type="reset" name="reset" value="Zuruecksetzen" onclick="<?php $user=null?>">
			</td>
			<td></td>
		</tr>
	</table>
</form>