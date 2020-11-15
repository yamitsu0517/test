<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<?php
require_once("../common.php");
$common_obj = new Common;
$is_sp = $common_obj->is_sp();
$id = $_GET['id'];

$sql_param['id'] = $id;
require_once('../sql.php');
$sql_class = new Make_Sql;

$response = $sql_class->do_sql($sql_param);
$data = [];
foreach ($response as $index) {
    $data['id'] = $index['ID'];
    $data['name'] = $index['NAME'];
    $data['location_x'] = $index['LOCATION_X'];
    $data['location_y'] = $index['LOCATION_Y'];
}
$path = '../';
require_once('../header.php');
require_once('./views/index.php');
?>
