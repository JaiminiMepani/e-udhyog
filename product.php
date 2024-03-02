<?php 
 
 //Constants for database connection
 define('DB_HOST','localhost');
 define('DB_USER','root');
 define('DB_PASS','');
 define('DB_NAME','eudhyog');
 

 //We will upload files to this folder
 //So one thing don't forget, also create a folder named uploads inside your project folder i.e. MyApi folder
 define('UPLOAD_PATH', 'c:\xampp\htdocs\eudhyog\images/');
 
 
 //connecting to database 
 $conn = new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME) or die('Unable to connect');
 
 
    $p_image =$_POST['p_image'];
    $p_name=$_POST['p_name'];
    $p_price=$_POST['p_price'];
    $p_details=$_POST['p_details'];
    $other_details=$_POST['other_details'];
    $p_quantity=$_POST['p_quantity'];
    $min_quantity=$_POST['min_quantity'];
   $delivery_days=$_POST['delivery_days'];

 //An array to display the response
 $response = array();

 
 
 //first confirming that we have the image and tags in the request parameter
 if(isset($_FILES['p_image']['name']) && isset($_POST['p_name'])  && isset($_POST['p_price'])  && isset($_POST['p_details']) 
	 && isset($_POST['other_details'])  && isset($_POST['p_quantity'])   && isset($_POST['min_quantity'])  && isset($_POST['delivery_days']) ){
 
 //uploading file and storing it to database as well 
 try{
 move_uploaded_file($_FILES['p_image']['tmp_name'], UPLOAD_PATH . $_FILES['p_image']['name']);
 $stmt = $conn->prepare("INSERT INTO seller_catalogue (p_image,p_name,p_price,p_details,other_details,p_quantity,min_quantity,delivery_days)
 VALUES(?,?,?,?,?,?,?,?)");
 
 $stmt->bind_param("ssssssss", $_FILES['p_image']['name'],$_POST['p_name'],$_POST['p_price'],$_POST['p_details'],
 $_POST['other_details'],$_POST['p_quantity'],$_POST['min_quantity'],$_POST['delivery_days']);
 if($stmt->execute()){
 $response["success"] = "1";
 $response["message"] = 'File uploaded successfully';
 }else{
 throw new Exception("Could not upload file");
 }
 }catch(Exception $e){
 $response["success"] = "0";
 $response["message"] = 'Could not upload file';}
 
 }else{
 $response['error'] = true;
 $response['message'] = "Required params not available";}
 
 

 
 
 //displaying the response in json 
 header('Content-Type: application/json');
 echo json_encode($response);
 ?>