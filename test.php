<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <title>test.php</title>
</head>
<body>
<?php
$data = array();
for($i=0;$i>10;$i++){
  $data[]=$i;
}
foreach($data as $d){
  echo $d;
}
?>
</body>
</html>
