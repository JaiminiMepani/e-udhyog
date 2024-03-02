<?php

  require 'connection.php';
  
  $b_id=$_POST['b_id'];  
  $s_id=$_POST['s_id'];
  $p_id=$_POST['p_id'];

  $p_price=$_POST['p_price'];
  $buyer_address=$_POST['buyer_address'];
  $order_qty=$_POST['order_qty'];
  $total=$_POST['total'];
  $payment_option=$_POST['payment_option'];
  $delivery_option=$_POST['delivery_option'];
  $address=$_POST['address'];
  $city=$_POST['city'];
  $state=$_POST['state'];
  $pincode=$_POST['pincode'];
  $phn_num=$_POST['phn_num'];
 $order_status=$_POST['order_status'];
 // $total=$order_amount*$order_qty+49;

  $insertQuery="INSERT INTO orders(b_id,s_id,p_id,order_qty,p_price,total,payment_option,delivery_option,buyer_address,order_date,order_status) 
  VALUES('34','44','76','$order_qty','$p_price','$total','$payment_option','$delivery_option','$address,$city,$state,$pincode',now(),'$order_status')";
  $result=mysqli_query($con,$insertQuery);

  if($result){

  	$response['success']="1";
  	$response['message']=" order placed successful!";
  }
  else
  {
  	$response['success']="0";
  	$response['message']=" failed!";
  }
   ob_end_clean();
	    header('Content-Type: application/json');
  echo json_encode($response);

?>