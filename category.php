<?php 
  require 'connection.php';
  
  $name=$_POST['name'];
$stmt = $con->prepare("SELECT subcat_id, c_id, subcat_name FROM sub_category where c_id = $c_id;");

$stmt ->execute();
$stmt -> bind_result($subcat_id,$c_id, $subcat_name);
if()
$sub_category = array();

while($stmt ->fetch()){

    $temp = array();
          $temp['subcat_id'] = $subcat_id;
	      
           $temp['c_id'] = $c_id;
      	$temp['subcat_name'] = $subcat_name;



	array_push($sub_category,$temp);
	}

	echo json_encode($sub_category);


	/*if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$c_id = $_POST['c_id'];
		$subcat_name=$_POST['subcat_name'];	

		$sql ="SELECT * FROM sub_category ";
		
		$res = mysqli_query($con,$sql);
	
		
		$sql = "INSERT INTO sub_category (c_id,subcat_name)
		VALUES ('$c_id','$subcat_name')";
		
		if(mysqli_query($con,$sql)){
			
			echo "Successfully added";
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}*/
	?>