<?php

  require 'connection.php';
  
   $s_id=$_POST['s_id'];
  $GSTIN_no=$_POST['GSTIN_no'];
  $storename=$_POST['storename'];
  $phn_num=$_POST['phn_num'];
  $email=$_POST['email'];
  $address=$_POST['address'];
  $state=$_POST['state'];
  $pincode=$_POST['pincode'];




  
     $sql="UPDATE  sellers SET  storename='$storename' , GSTIN_no = '$GSTIN_no', phn_num = '$phn_num' 
	  ,email ='$email' ,address='$address' ,state ='$state' ,pincode='$pincode' WHERE s_id = '35' ";
	

     
   
     
     $result = mysqli_query($con,$sql);
     
     if($result){
         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($con);
     
        
?>