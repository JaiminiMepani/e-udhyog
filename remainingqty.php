<?php
require 'connection.php';

/*$sql=$con->prepare("SELECT p_id,p_quantity,order_qty FROM  seller_catalogue,orders  WHERE seller_catalogue.p_id=orders.p_id");

$stmt ->execute();
$stmt -> bind_result($p_id,$p_quantity,$order_qty);

$seller_catalogue = array();

while($stmt ->fetch()){
	$temp = array();
	
     $temp['p_id']= $p_id;
	 $temp['p_quantity']= $p_quantity;
	 $temp['order_qty'] = $order_qty;

    array_push($seller_catalogue,$temp);
}*/

$sql="SELECT * FROM  orders,seller_catalogue  WHERE  p_id='74'";

$result=mysqli_query($con,$sql);
if ($result){
	while ($row=$result-> fetch_assoc()) 
	{
		  $response['success']="1";
		 $response['message']=$row['p_quantity-order_qty'];
							
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