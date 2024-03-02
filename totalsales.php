<?php


include 'connection.php';
//$s_id=$_POST['s_id'];
$query ="SELECT SUM(total)from orders where s_id='44'";
$result= mysqli_query($con,$query);
if ($result-> num_rows > 0){
	while ($row=$result-> fetch_assoc()) 
	{
		  $response['success']="1";
		 $response['message']=$row['SUM(total)'];
							
	}
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