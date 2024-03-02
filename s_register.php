<?php

  require 'connection.php';
    $s_id=$_POST['s_id'];
  $storename=$_POST['storename'];
  $GSTIN_no=$_POST['GSTIN_no'];
  $phn_num=$_POST['phn_num'];
  $password=$_POST['password'];
  $email=$_POST['email'];
  $address=$_POST['address'];
  $state=$_POST['state'];
  $pincode=$_POST['pincode'];
  $seller_status=$_POST['seller_status'];



 $findexist="select * from sellers where phn_num='$phn_num'";

         $resultsearch=mysqli_query($con,$findexist);
		if(mysqli_num_rows($resultsearch)>0)
		{
           while($row=mysqli_fetch_array($resultsearch))
          {
			   		  $s_id=$row['s_id'];
              $result["success"] = "0";
              $result["message"] = "user already exist";
			  
              
        
              mysqli_close($con);
          }
	}
else{
  
     $sql="INSERT INTO sellers(storename,GSTIN_no,phn_num,password,email,address,state,pincode,seller_status)
	 VALUES('$storename','$GSTIN_no','$phn_num','$password', '$email','$address','$state','$pincode','$seller_status')";
    if ( mysqli_query($con, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "Registration successful";
		  $result["s_id"]=$s_id;
      
        mysqli_close($con);

    } else {
        $result["success"] = "3";
        $result["message"] = "error in Registration";
  
        mysqli_close($con);
    }
}
   ob_end_clean();
 header('Content-Type: application/json');
      echo json_encode($result);
?>
