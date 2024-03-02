<?php
require_once "connection.php";
 $deliverypartner_name=$_POST['deliverypartner_name'];
 $s_id=$_POST['s_id'];
			$sql1 = "INSERT INTO delivery_partner(deliverypartner_name)  VALUES('$deliverypartner_name')";
			
			 if (mysqli_query($con,$sql1)) {
            $result1["success"] = "1";
             $result1["message"] = "delivery partner name added";
			 
			 	$sql2 = "UPDATE  sellers  SET deliverypartner_name  =  '$deliverypartner_name' where s_id='44' ";
			 if (mysqli_query($con,$sql2)) {
            $result1["success"] = "3";
             $result1["message"] = "delivery partner name added in sellers ";
      
              mysqli_close($con);

    } 
			 else {
             $result1["success"] = "4";
               $result1["message"] = "delivery partner name  not added in sellers ";
			 }
			 }
			 else {
             $result1["success"] = "0";
               $result1["message"] = "delivery partner name  not added";
   
        mysqli_close($con);
    }
	
		header('Content-Type: application/json');
        echo json_encode($result1);
    ?>