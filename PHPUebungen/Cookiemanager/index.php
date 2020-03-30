<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="css.css">

	<script src="script.js"> </script>

	<title>Cookiemanager</title>
</head>
<body>

	<table>
		<tr> <?php include "Header.php"; ?> </tr>
		<tr> 
			<table>
			<tr>
				<td style="width: 20%;">
					<?php include "Menu.php"; ?>
				</td>

				<td> 
				<?php
					$selected = "List.php";
					if($_GET == null) include $selected;
					else{
					switch ($_GET["id"]) {
						case "1": {
							$selected = "List.php";
							break;
						}
						case "2": {
							$selected = "New.php";
							break;
						}
						default:{
							$selected = "List.php";
							break;
						}
					}
					include $selected;
				}
				?>
				</td>
			</tr>			
			</table>
		</tr>
	
	
	</table>


</body>
</html>