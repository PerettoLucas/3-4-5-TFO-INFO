<table border="" style="width: 100%;"> 
	<tr> <td> <b>Parametro</b> </td> <td> <b> Valore </b> </td> </tr>
		<?php
			foreach($_REQUEST as $key2 => $value2){
				echo "<tr><td>$key2</td> <td>$value2</td> </tr>";
			}
		?>
</table>
