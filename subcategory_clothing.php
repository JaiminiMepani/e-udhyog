
	<?php 

	require_once "connection.php";
	$c_id=$_POST['c_id'];
	$result = array();
	$result['data'] = array();
	$select= "SELECT c_id,subcat_name from sub_category where c_id='1'";
	$response = mysqli_query($con,$select);

     	if($response)  {              
	
	while($row = mysqli_fetch_array($response))
		{
		
			$index['subcat_name']= $row['subcat_name'];
			
			
			array_push($result['data'], $index);
		}
			
			$result["success"]="1";
			 ob_end_clean();
	    header('Content-Type: application/json');
			echo json_encode($result);
			mysqli_close($con);
		}


 ?>


