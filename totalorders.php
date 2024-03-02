<?php 

include 'connection.php';
//$s_id=$_POST['s_id'];
$sql="SELECT * from orders where s_id='44'";
                       $result=mysqli_query($con,$sql);
                       $count=0;
                       if ($result-> num_rows > 0){
                           while ($row=$result-> fetch_assoc()) {
                   
                               $count=$count+1;
                           }
						  $response['success']="1";
		                 $response['message']=$count;
		
					   }

else{
							
$response['success']="0";
$response['message']="Try again....!";
}
 ob_end_clean();
  header('Content-Type: application/json');
echo json_encode($response);

?>