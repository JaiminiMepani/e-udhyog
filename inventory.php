<?php
require 'connection.php';
 
$result=mysqli_query($con,"SELECT p_name,p_quantity FROM seller_catalogue");
while($row=mysqli_fetch_assoc($result))
{
$storename = $row['storename'];
   
}
   ob_end_clean();
   echo json_encode($storename);


?>