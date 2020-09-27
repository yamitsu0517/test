<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
    $data['id'] = $index['id'];
    $data['name'] = $index['name'];
    $data['location_x'] = $index['location_x'];
    $data['location_y'] = $index['location_y'];
    
}
$path = '../';
require_once('../header.php');
require_once('./views/index.php');
?>
<h1>test</h1>
