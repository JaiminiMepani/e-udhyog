<?php
include 'connection.php';
//$p_id=$_POST['p_id'];
$stmt = $con->prepare("SELECT b_id,p_image,p_name,p_details, order_qty,total,order_status FROM orders,seller_catalogue ");
$stmt ->execute();
$stmt -> bind_result($b_id,$p_image,$p_name,$p_details,$order_qty,$total,$order_status);

$orders = array();

while($stmt ->fetch()){

    $temp = array();

    $temp['b_id'] = $b_id;
	$temp['p_name'] = $p_name;
	$temp['order_qty'] = $order_qty;
	$temp['total'] = $total;
	$temp['p_image'] = $p_image;
	$temp['p_details'] = $p_details;
	$temp['order_status']= $order_status;
	

	array_push($orders,$temp);
}
				
	
		ob_end_clean();
	    header('Content-Type: application/json');
	echo json_encode($orders);	
 

	 

?>