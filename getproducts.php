<?php

include 'connection.php';

$stmt = $con->prepare("SELECT  p_id,p_image , p_name, p_price,p_details,other_details,min_quantity,delivery_days,address,state,storename
 FROM seller_catalogue, sellers   WHERE seller_catalogue.s_id = sellers.s_id");

$stmt ->execute();
$stmt -> bind_result($p_id,$p_image,$p_name, $p_price,$p_details,$other_details,$min_quantity,$delivery_days,$address,$state,$storename);

$seller_catalogue = array();

while($stmt ->fetch()){

    $temp = array();
		 
		   
		   $temp['p_id'] = $p_id;
		   $temp['p_image'] = $p_image;
           $temp['p_name'] = $p_name;
      	   $temp['p_price'] = $p_price;
		   $temp['p_details'] = $p_details;  
           $temp['other_details'] = $other_details;
      	   $temp['min_quantity'] = $min_quantity;
		   $temp['delivery_days'] = $delivery_days ;
		   $temp['address'] = $address;
		   $temp['state'] = $state;
           $temp['storename'] = $storename;


	array_push($seller_catalogue,$temp);
	}
 header('Content-Type: application/json');
	echo json_encode($seller_catalogue);

?>

 