<?php

?>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.js"></script>
<link href="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.css" rel="stylesheet" />
<?php if ($is_sp) { ?>
<link rel="stylesheet" href="<?php echo $path . 'header_sp.css' ?>">
<?php } else { ?>
<link rel="stylesheet" href="<?php echo $path . 'header_pc.css' ?>">
<?php } ?>
<div class="header">
  <h1>聖地アプリ</h1>
</div>
