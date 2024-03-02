<?php 
  require 'connection.php';

	
	if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$s_id = $_POST['s_id'];
	$subcat_id = $_POST['subcat_id'];
	$p_image = $_POST['p_image'];
    $p_name = $_POST['p_name'];
	$p_price=$_POST['p_price'];
	$p_details=$_POST['p_details'];
	$other_details=$_POST['other_details'];
	$p_quantity=$_POST['p_quantity'];
	$min_quantity=$_POST['min_quantity'];
	$delivery_days=$_POST['delivery_days'];  
	
		
		$sql ="SELECT p_id FROM seller_catalogue ORDER BY p_id ASC";
		
		$res = mysqli_query($con,$sql);
		
		$p_id = 0;
		
		while($row = mysqli_fetch_array($res)){
				$p_id = $row['p_id'];
		}
		
		$path = "images/$p_id.png";
		
		
		$actualpath = "http://192.168.63.108/eudhyog/$path";
		
		$sql = "INSERT INTO seller_catalogue (s_id,subcat_id,p_image,p_name,p_price, p_details,other_details,p_quantity,min_quantity,delivery_days)
		VALUES ('$s_id','$subcat_id','$actualpath','$p_name', '$p_price','$p_details', '$other_details','$p_quantity','$min_quantity','$delivery_days')";
		
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($p_image));
			
			echo "Successfully Uploaded";
		}
		
		mysqli_close($con);
	}
	else{
		echo "Error";
	}
	
	
	

 /* $p_image=$_POST['p_image']   ;
  $p_name=$_POST['p_name'];
  $p_price=$_POST['p_price'];
  $p_details=$_POST['p_details'];
  $other_details=$_POST['other_details'];
  $p_quantity=$_POST['p_quantity'];
  $min_quantity=$_POST['min_quantity'];
 $delivery_days=$_POST['delivery_days'];  

   $sql="INSERT INTO  seller_catalogue (p_name,p_price, p_details,other_details,p_quantity,min_quantity,delivery_days) 
   VALUES ('$p_name', '$p_price','$p_details', '$other_details','$p_quantity','$min_quantity','$delivery_days')";
   if (mysqli_query($con,$sql)) {
      $result["success"] = "1";
        $result["message"] = "details added";
      
        mysqli_close($con);

    } else {
        $result["success"] = "0";
        $result["message"] = "details not added";
  
        mysqli_close($con);
    }
  

 header('Content-Type: application/json');
      echo json_encode($result);*/


?>