<table border="" style="width: 100%;"> 
	<tr> <td> <b>Indice</b> </td> <td> <b> Valore </b> </td> </tr>
		<?php
			$languageArr = array();
			$splitArr = array();
			$paramArr = array();
			$valueArr = array();

			$languageArr[] = $_SERVER['HTTP_ACCEPT_LANGUAGE'];

			$languageStr = $languageArr[0];


			$paramArr = explode(",",$languageStr);

			$i = 0;
			foreach($paramArr as $key ){
				$valueArr[] = explode(";",$paramArr[$i]);
				$i++;
			}
			
			$j = 0;
			foreach($valueArr as $key){
				echo "<tr><td> $j </td> <td> $key[0] </td>  </tr>";
				$j++;
			}

		?>
</table>