<?php if ($is_sp) {?>
<link rel="stylesheet" href="./index_sp.css">
<?php } else { ?>
<link rel="stylesheet" href="./index_sp.css">
<?php } ?>
<div class="content">
  <div class="place_name">
  <h2><?php echo $data['name']; ?></h2><h2><a id="map_btn" href="javascript:void(0)">マップで確認する</a> </h2>
  </div>
  <div class="areamap">
    <span class="close_btn" id="close_btn">×</span>
    <div class="map" id='map' style='width: 700px; height: 300px;'></div>
    <div class="remove"></div>
  </div>
  <div class="content-right">
  </div>
</div>
<?php require_once("./index_js.php"); ?>

<div id="position_view"></div>
<script>
</script>
