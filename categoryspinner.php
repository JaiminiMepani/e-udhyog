<?php
require_once "connection.php";
$sql = "select * from category";
if(!$con->query($sql)){
    echo "Error in connecting to Database.";
}
else{
    $result = $con->query($sql);
    if($result->num_rows > 0){
        $return_arr['category'] = array();
        while($row = $result->fetch_array()){
            array_push($return_arr['category'], array(
                'c_id'=>$row['c_id'],
                'c_name'=>$row['c_name']
            ));
        }
        echo json_encode($return_arr);
    }
}
?>