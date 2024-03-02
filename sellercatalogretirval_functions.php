<?php
class sellercatalogretirval_functions
{
     public $con;
    //Class constructor
    function __construct()
    {
       //Getting the DbConnect.php file
        require_once dirname(__FILE__) . '/DbConnect.php';
 
        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();
 
        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
 
       
    }
 
 function getHeroes(){
 $stmt = $this->con->prepare("SELECT  p_name, p_quantity FROM seller_catalogue");
 $stmt->execute();
 $stmt->bind_result( $p_name, $p_quantity);
 
 $seller_catalogue = array(); 
 
 while($stmt->fetch()){
 $hero  = array();
 
 $hero['p_name'] = $p_name; 
 $hero['p_quantity'] = $p_quantity; 

 
 array_push($seller_catalogue, $hero); 
 }
 
 return $seller_catalogue; 
 }
 
 /*
 * The update operation
 * When this method is called the record with the given id is updated with the new given values
 */
 function updateHero( $p_name,$p_quantity){
 $stmt = $this->con->prepare("UPDATE seller_catalogue SET  p_quantity=? WHERE p_name =? ");
 $stmt->bind_param("is",$p_quantity,$p_name);
 if($stmt->execute())
 return true; 
 return false; 
 }
 
 
 /*
 * The delete operation
 * When this method is called record is deleted for the given id 
 */
 function deleteHero($p_name){
 $stmt = $this->con->prepare("DELETE FROM seller_catalogue WHERE p_name = ?");
 $stmt->bind_param("s", $p_name);
 if($stmt->execute())
 return true; 
 
 return false; 
 }
}
 ?>