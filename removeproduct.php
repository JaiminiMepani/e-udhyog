<?php

  require 'connection.php';
   $p_name=$_POST['p_name'];
 
$query=mysqli_query($con,"SELECT p_name FROM seller_catalogue where p_name='$p_name'");
$result=array();
if(mysqli_num_rows($query) === 1){
	$sql="DELETE FROM seller_catalogue where p_name = '$p_name'";
	if(mysqli_query($con,$sql))
	{
		$result['state'] = "delete";
         $result['message']= "Product deleted successfully";
		 ob_end_clean();
		 echo json_encode($result);
	}
	else
	{
		echo "Product already deleted";
}}
	else
	{
		echo " Nothing to delete, provide an product  name please";
	}
	


  
  


	 
  ?>