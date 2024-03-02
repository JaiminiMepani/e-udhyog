<?php
require_once "connection.php";

$sql = "select * from delivery_partner";
if(!$con->query($sql)){
    echo "Error in connecting to Database.";
}
else{
    $result = $con->query($sql);
    if($result->num_rows > 0){
        $return_arr['delivery_partner'] = array();
        while($row = $result->fetch_array()){
            array_push($return_arr['delivery_partner'], array(
                'deliverypartner_id'=>$row['deliverypartner_id'],
                'deliverypartner_name'=>$row['deliverypartner_name']
            ));
			 
        }
	}
	 echo json_encode($return_arr);
		
}

?>