<?php

include 'connection.php';
 $subcat_id=$_POST['subcat_id'];
$stmt = $con->prepare("SELECT  subcat_id,p_image , p_name, p_price,p_details,other_details,min_quantity,delivery_days,address,state,storename
 FROM seller_catalogue,sellers   WHERE  subcat_id = $subcat_id && seller_catalogue.s_id = sellers.s_id ");

$stmt ->execute();
$stmt -> bind_result($subcat_id,$p_image,$p_name, $p_price,$p_details,$other_details,$min_quantity,$delivery_days,$address,$state,$storename);

$seller_catalogue = array();

while($stmt ->fetch()){

    $temp = array();
		 
		   
		   $temp['subcat_id'] = $subcat_id;
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
