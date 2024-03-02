

<?php

require_once 'connection.php';

 $phn_num = ($_POST['phn_num']);
   $password = ($_POST['password']);




      $list="";

	$query="select * from buyers where phn_num='$phn_num'  AND password='$password'";

        $result=mysqli_query($con,$query);
		if(mysqli_num_rows($result)>0)
	    {
          	 while($row=mysqli_fetch_array($result))
          	{
         	   if($list=="")
                 {
           	  
		          
                  $name=$row['name'];
                 $phn_num=$row['phn_num'];

                 }


		         }
       }

       if(mysqli_num_rows($result)==0)
	     {
 		      $response["success"] = "0";
 		       $response["message"]="user is not Registered, Please Register";
 		      
 		         mysqli_close($con);
       }
       else
          {
		          $response["success"]="1";
              $response["message"]="Logged in successful";
      	 
              $response["name"]=$name;
              $response["phn_num"]=$phn_num;
		    
 		          mysqli_close($con);
	   }
                      ob_end_clean();
	    header('Content-Type: application/json');
		  echo json_encode($response);

?>




