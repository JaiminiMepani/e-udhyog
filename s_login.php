<?php


require_once 'connection.php';
   $s_id=$_POST['s_id'];
 $phn_num = $_POST['phn_num'];
   $password = $_POST['password'];



      $list="";

	$query="select * from sellers where phn_num='$phn_num'  AND password='$password'";

        $result=mysqli_query($con,$query);
		if(mysqli_num_rows($result)>0)
	    {
          	 while($row=mysqli_fetch_array($result))
          	{
         	   if($list=="")
                 {
					  $s_id=$row['s_id'];
           	         $phn_num=$row['phn_num'];

                 }


		         }
       }

       if(mysqli_num_rows($result)==0)
	     {
 		      $response["success"] = 0;
 		       $response["message"]="user is not Registered, Please Register";
 		      
 		         mysqli_close($con);
       }
       else
          {                 
		          $response["success"]=1;
              $response["message"]="Logged in successful";
      	 
             
               $response["s_id"]=$s_id;
              $response["phn_num"]=$phn_num;
		    
 		        
	   }
              ob_end_clean();
	    header('Content-Type: application/json');
		  echo json_encode($response);

?>
