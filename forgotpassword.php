<?php
 require 'connection.php';
    $phn_num=$_POST['phn_num'];

    $password=$_POST['password'];
     
 
    $checkuser="SELECT * FROM buyers WHERE phn_num='$phn_num' ";
     
    $result=mysqli_query($con,$checkuser);
 
 
    if(mysqli_num_rows($result)>0){
 
        $updatePass=mysqli_query($con,"UPDATE buyers SET password='$password' WHERE phn_num='$phn_num'");
 
        if($updatePass > 0){
 
            $response['success']="1";
            $response['message']="password updated successfully";
 
        }
        else{
            $response['sucsess']="0";
            $response['message']="password not updated";
 
        }
 
    }
    else{
        $response['error']="400";
        $response['message']="User does not exist";
 
    }
 
    echo json_encode($response);
 
?>
