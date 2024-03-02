<?php

include('connection.php');

$s_id=$_POST['s_id'];

$stmt = $con->prepare("SELECT s_id,p_id,p_name,p_image, p_price,p_details,other_details,min_quantity 
FROM seller_catalogue where s_id='44'");

$stmt ->execute();
$stmt -> bind_result($s_id,$p_id,$p_name,$p_image, $p_price, $p_details,$other_details,$min_quantity);

$seller_catalogue = array();

while($stmt ->fetch()){

    $temp = array();
	$temp['s_id']= $s_id;
	
     $temp['p_id']= $p_id;
	$temp['p_name'] = $p_name;
	$temp['p_image']=$p_image;
	$temp['p_price'] = $p_price;
	$temp['p_details'] = $p_details;

	$temp['other_details'] = $other_details;

	$temp['min_quantity'] = $min_quantity;

	

	array_push($seller_catalogue,$temp);
}
	 ob_end_clean();
	    header('Content-Type: application/json');
	echo json_encode($seller_catalogue);

?>