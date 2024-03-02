<?php

  require 'connection.php';
   $storename=$_POST['storename'];
 
$result=mysqli_query($con,"SELECT storename FROM sellers where s_id='44'");
while($row=mysqli_fetch_assoc($result))
{
$storename = $row['storename'];
   
}
   ob_end_clean();
   echo json_encode($storename);


	 
  ?>