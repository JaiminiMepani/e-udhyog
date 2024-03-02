
<?php

  $name=$_POST['name'];
  $phn_num=$_POST['phn_num'];
  $password=($_POST['password']);
  $email=$_POST['email'];
  $buyer_status=$_POST['buyer_status'];
    
  require 'connection.php';

$findexist="select * from buyers where phn_num='$phn_num'";

        $resultsearch=mysqli_query($con,$findexist);
		if(mysqli_num_rows($resultsearch)>0)
		{
           while($row=mysqli_fetch_array($resultsearch))
          {
              $result["success"] = "0";
              $result["message"] = "user already exist";
              
        
              mysqli_close($con);
          }
	}
else{

    $sql = "INSERT INTO buyers(name,phn_num,password,email,buyer_status) VALUES('$name','$phn_num','$password',
	 '$email','$buyer_status')";

    if ( mysqli_query($con, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "Registration successful";
      
        mysqli_close($con);

    } else {
        $result["success"] = "3";
        $result["message"] = "error in Registration";
  
        mysqli_close($con);
    }
}
 header('Content-Type: application/json');
      echo json_encode($result);
?>
