<?php
require_once "connection.php";
if(isset($_GET['c_name'])){
    $sql = "select subcat_id, subcat_name from sub_category where c_id=(select c_id from category where c_name='".$_GET['c_name']."')";
    if(!$con->query($sql)){
        echo "Error in executing query.";
    }else{
        $result = $con->query($sql);
        if($result->num_rows > 0){
            $return_arr['sub_category'] = array();
            while($row = $result->fetch_array()){
                array_push($return_arr['sub_category'], array(
                    'subcat_id'=>$row['subcat_id'],
                    'subcat_name'=>$row['subcat_name']
                ));
            }
			$return_arr["success"]="1";
            echo json_encode($return_arr);
			mysqli_close($con);
        }
    }
}
?>


	