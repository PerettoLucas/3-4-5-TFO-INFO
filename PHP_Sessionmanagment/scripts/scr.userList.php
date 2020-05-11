<h1>Benutzerliste</h1>
<script type="text/javascript">
	function deleteUser() {
		return confirm("Wollen Sie den Benutzer wirklich loeschen?");	
	}
</script>
<?php $list = $userList->getUsers();?> 
<?php if (!$list) {?>
	<p>Keine Benutzer vorhanden</p>
<?php } else { ?>
	<table>
		<tr>
			<th>Benutzername</th>
			<th>Geschlecht</th>
			<th>Geburtsdatum</th>
			<th>Bewertung</th>
			<th></th>
		</tr>     <?php forEach($list as $key => $value) {?>       <tr>
			<td><?=$value->getUsername()?></td>
			<td><?=$value->getMale() ? "Maennlich" : "Weiblich"?></td>
			<td><?=$value->getBirthDate()?></td>
			<td><?= $value->getRating()?></td>
			<td><a href="index.php?id=3&username=<?=$value->getUsername()?>">Bearbeiten</a>
				<a href="index.php?id=4&username=<?=$value->getUsername()?>" onclick="return deleteUser()">Loeschen</a>
			</td>
		</tr>        
	 <?php }?>  
	</table>
<?php }?> 