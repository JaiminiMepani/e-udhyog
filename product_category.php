<?php
require_once "connection.php";
if(isset($_GET['subcat_id'])){
    $sql = "select p_id, subcat_id,p_image , p_name, p_price,p_details,other_details,min_quantity
	FROM seller_catalogue where  subcat_id=(select subcat_id from sub_category where  subcat_id='".$_GET['subcat_id']."')";
    if(!$con->query($sql)){
        echo "Error in executing query.";
    }else{
        $result = $con->query($sql);
        if($result->num_rows > 0){
            $return_arr['seller_catalogue'] = array();
            while($row = $result->fetch_array()){
                array_push($return_arr['seller_catalogue'], array(
				    'p_id'=>$row['p_id'],
                    'subcat_id'=>$row['subcat_id'],
					'p_image'=>$row['p_image'],
                    'p_name'=>$row['p_name'],
					'p_price'=>$row['p_price'],
					'p_details'=>$row['p_details'],
					'other_details'=>$row['other_details'],
					'min_quantity'=>$row['min_quantity']
					
				
                ));
            }
            echo json_encode($return_arr);
        }
    }
}
?>