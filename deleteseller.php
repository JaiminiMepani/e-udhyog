<?php

include 'connection.php';
$phn_num=$_POST['phn_num'];
$query = "DELETE  FROM sellers where phn_num = '$phn_num'";
$result= mysqli_query($con,$query);
  if($result){
	    $response['success']="1";
	$response['message']="deleted successful!";
  }
  else
  {
  	  $response['success']="0";
  	$response['message']=" Try again!";
  }

	ob_end_clean();
  header('Content-Type: application/json');
  echo json_encode($response);

?>