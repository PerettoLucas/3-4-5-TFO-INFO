<table id="outerHeadTable" border="" >
	<tr>
		<td> <h1> <b> Cookiemanager </b> </h1> </td>
		<td> 
			<table id="innerHeadTable" >
				<tr><td> 
				<?php 
					$i = 0;
					while($i < sizeof($_COOKIE)){$i++;}
					echo $i . " Cookies gesetzt" . "<hr>";
				?> </td></tr>
				<tr><td> <?php include "cookies.php"; ?> </td></tr>
			</table>
		</td>
	</tr>
<table>