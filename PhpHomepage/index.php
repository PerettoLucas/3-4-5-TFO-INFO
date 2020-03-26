<table border="1" width="100%">
  <tr>
    <td><?php include_once "header.php"?></td>
    <td><?php include_once "menu.php"?></td>
  </tr>
  <tr height="100%">
    <td colspan="2">
      <?php
      $file = "new.php";
      switch ($_GET["id"]) {
        case 1: { 
          $file = "list.php";
          break;
        }
        case 2: { 
          $file = "new.php"; 
          break; 
        }
        // ...       
      }
      include_once $file;
      ?>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <?php include "footer.php"?>
    </td>
  </tr>
</table>
