<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>PHP Übungen</title>
	<link rel="stylesheet" href="css.css">
</head>
<body>
	<?php 
		$i = 88;
	?>
	<h1> Var Test <?php print $i ?> </h1>

	<br>

	<pre>
	<?php
	$leeresFeld = array();
	$einFeld = array(
	'Name' => "Emil",
	false => "Eins",
	1 => -3.0,
	2 => 0,
	true => false,
	"Rennbahn",
	'' => "Zwei",
	'Hobbies' =>
	array("Segeln","Rodeln"));


	$einFeld[] = 3.14;
	$einFeld['Name'] = "Sepp";
	$einFeld['Hobbies'][7]="Schwim-
	men";

	echo $einFeld[''];
	var_dump($einFeld);

	$nochEinFeld[4] = "Eins";
	$nochEinFeld[] = "Zwei";
	$nochEinFeld[] = "Drei";
	unset($nochEinFeld[5]);

	print_r($nochEinFeld);

	echo count($nochEinFeld);
	print "\n";
	echo "<br>";

	$einFeld += $nochEinFeld;
	print_r($einFeld);
	foreach($nochEinFeld as $wert)
	echo "$wert";

	print "\n";

	foreach($nochEinFeld as
	$schluessel => $wert){
		echo "$schluessel .... $wert";
		echo "<br>";
	}
	echo "<br>";
	echo "<br>";
	echo "<br>";
	echo "<br>";
	echo "<br>";

	$produkte = array(
	"a" => "Gurke",
	"b" => "Lauch",
	"c" => "Brokkoli",
	"d" => "Kohl");

	function ausgeben($wert,$schluessel) {
	echo "$schluessel. $wert<br>";
	}
	function formatieren
	(&$wert,$schluessel,$prefix) {
	$wert = "$prefix: $wert";
	}
	array_walk($produkte,"ausgeben");
	array_walk($produkte,"formatieren","Gemüse");
	array_walk($produkte,"ausgeben");
	
	asort($produkte);
	array_walk($produkte,"ausgeben");
	$produkt = each($produkte);

	while(list($schluessel,$wert) =
	each($produkte))
	echo "$schluessel => $wert<br>";
	
	reset($produkte);
	$produkt = each($produkte);
	echo "$produkt[0], $produkt[1]<br>";
	prev($produkte);
	$produkt = each($produkte);
	echo "$produkt[0], $produkt[1]<br>";
	if (in_array('Gemüse: Gurke',$produkte))
		echo "Gefunden";
	?>
	
	</pre>

</body>
</html>