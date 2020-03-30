<table id="listTable" border="">
	<?php 
		foreach($_COOKIE as $cookie => $value){
			echo "<tr> <td> $cookie </td> <td> $value </td> <td> <a href=\"delete.php?name=$cookie\" > Löschen </a> </td> </tr>";
		}	
	?>
</table>