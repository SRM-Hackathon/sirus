<?php
require "dbconnect.php";
$fetchcount=$_POST['fetchcount'];
$userid=$_POST['userid'];
$data=$conn->query("SELECT * FROM commodities ORDER BY comid DESC limit 10 OFFSET ".$fetchcount);
$i=0;
while($row=$data->fetch_assoc()){
	$arr[$i]=$row;
	$i++;
}
echo json_encode($arr);

?>