<?php

  require 'connection.php';
 
  $bank_name=$_POST['bank_name'];
   $branch_name=$_POST['branch_name'];
  $acc_holdername=$_POST['acc_holdername'];
  $acc_no=$_POST['acc_no'];
  $ifsc=$_POST['ifsc'];
  $upi=$_POST['upi'];



  $checkUser="SELECT * from seller_bankdetails WHERE acc_no='$acc_no'";
  $checkQuery=mysqli_query($con,$checkUser);

  if(mysqli_num_rows($checkQuery)>0)
{

  	$response['success']="3";
  	$response['message']="Account Number Is Already Registered";
  }
  else
  {
     $insertQuery="INSERT INTO seller_bankdetails(s_id,bank_name,branch_name,acc_holdername,acc_no,ifsc,upi) 
	 VALUES('44','$bank_name','$branch_name','$acc_holdername', '$acc_no','$ifsc','$upi')";
  $result=mysqli_query($con,$insertQuery);

  if($result){

  	$response['success']="1";
  	$response['message']="Bank Details Added";
  }
  else
  {
  	$response['success']="0";
  	$response['message']="Bank Details Not Added";
  }



  }

  
  echo json_encode($response);

?>