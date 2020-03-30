<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">

	<script src="script.js"></script>
	<title>New Cookie</title>
</head>
<body>
	<h1><b> Neues Cookie </b></h1>
	<form id="newForm" method="get" action="newAction.php">	
		<table id="newTable" border="">
			<tr>
				<td><label id="lableName"> Name : </label></td>
				<td><input id="nameID" type="text" name="name" style="broder: none;"></td>
				<br>
			</tr>
			<tr>
				<td><label> Wert : </label></td>
				<td><input id="wertID" type="text" name="wert"></td>
				<br>
			</tr>
			<tr>
				<td><label for="">Lebensdauer : </label></td>
				<td> 
					<SELECT id="selectMenu" name="selectMenu">
					<option value='death' selected>Stirbt beim Schließen des Browsers </option>
					<option value='30S'>30 Sekunden</option>
					<option value='1M'>1 Minute</option>
					<option value='5M'>5 Minuten</option>
					<option value='1H'>1 Stunde</option>
					<option value='7D'>7 Tage</option>
					</SELECT>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input id="erstellenBtn" type="submit" value="Erstellen">
					<input id="zuruecksetzenBtn" type="reset" value="Zurücksetzen">
				</td> 
			</tr>
		</table>
	</form>	


</body>
</html>