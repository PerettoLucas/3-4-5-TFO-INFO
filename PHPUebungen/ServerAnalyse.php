<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>PHP $Server</title>
</head>
<body>

	<table border="">
		<tr>
			<td><b> $_SERVER </b></td> <td> <b> Wert </b></td>
		</tr>

		<?php

			foreach($_SERVER as $key => $wert){
				if($key == "SERVER_NAME" || $key == "SERVER_PORT" || $key == "HTTP_ACCEPT_LANGUAGE" || $key == "PHP_SHELF" || $key == "QUERY_STRING" || $key == "REQUEST_URI")
					echo "<tr><td>$key</td> <td>$wert</td> </tr>";
			}	

		?>

		<tr>
			 <td> <b> $_REQUEST </b> </td>
			 <td>
				<?php include "ParamTable.php"; ?>
			 </td>
		</tr>

		<tr>
			 <td> <b> Lingue </b> </td>
			 <td>
				<?php include "Language.php"; ?>
			 </td>
		</tr>

	</table>



</body>
</html>