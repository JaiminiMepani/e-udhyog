<?php 
 
 //getting the dboperation class
 require_once 'sellercatalogretirval_functions.php';
 
 //function validating all the paramters are available
 //we will pass the required parameters to this function 
 function isTheseParametersAvailable($params){
 //assuming all parameters are available 
 $available = true; 
 $missingparams = ""; 
 
 foreach($params as $param){
 if(!isset($_POST[$param]) || strlen($_POST[$param])<=0){
 $available = false; 
 $missingparams = $missingparams . ", " . $param; 
 }
 }
 
 //if parameters are missing 
 if(!$available){
 $response = array(); 
 $response['error'] = true; 
 $response['message'] = 'Parameters ' . substr($missingparams, 1, strlen($missingparams)) . ' missing';
 
 //displaying error
 echo json_encode($response);
 
 //stopping further execution
 die();
 }
 }
 
 //an array to display response
 $response = array();
 
 //if it is an api call 
 //that means a get parameter named api call is set in the URL 
 //and with this parameter we are concluding that it is an api call
 if(isset($_GET['apicall'])){
 
 switch($_GET['apicall']){

 
 //the READ operation
 //if the call is getheroes
 case 'getheroes':
 $db = new sellercatalogretirval_functions();
 $response['error'] = false; 
 $response['message'] = 'Request successfully completed';
 $response['seller_catalogue'] = $db->getHeroes();
 break; 
 
 
 //the UPDATE operation
 case 'updatehero':
 isTheseParametersAvailable(array('p_name','p_quantity'));
 $db = new sellercatalogretirval_functions();
 $result = $db->updateHero(

 $_POST['p_name'],
 $_POST['p_quantity']
 
 );
 
 if($result){
 $response['error'] = "false"; 
 $response['message'] = 'Product Quantity updated successfully';
 $response['seller_catalogue'] = $db->getHeroes();
 }else{
 $response['error'] = "true"; 
 $response['message'] = 'Some error occurred please try again';
 }
 break; 
 
 //the delete operation
 case 'deletehero':
 
 //for the delete operation we are getting a GET parameter from the url having the id of the record to be deleted
 if(isset($_GET['p_name'])){
 $db = new sellercatalogretirval_functions();
 if($db->deleteHero($_POST['p_name'])){
 $response['error'] = false; 
 $response['message'] = 'Product deleted successfully';
 $response['seller_catalogue'] = $db->getHeroes();
 }else{
 $response['error'] = true; 
 $response['message'] = 'Some error occurred please try again';
 }
 }else{
 $response['error'] = true; 
 $response['message'] = 'Nothing to delete, provide an name please';
 }
 break; 
 }
 
 }else{
 //if it is not api call 
 //pushing appropriate values to response array 
 $response['error'] = true; 
 $response['message'] = 'Invalid API Call';
 }
 
 //displaying the response in json structure 
 	 ob_end_clean();
	    header('Content-Type: application/json');
 echo json_encode($response);
  
  
 ?>