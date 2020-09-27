<div class="content">
  <div class="content-left">
  <!--  <div class="map" id='map' style='width: 400px; height: 300px;'></div>-->
    <div class="map" id='map' style='width: 400px; height: 300px;'></div>
  </div>
  <div class="content-right">
    <ul id="buttons" class="buttons">
      <li id="button-ja" class="button" data-id="ja">日本語</li>
      <li id="button-en" class="button" data-id="en">英語</li>
      <li id="button-ko" class="button" data-id="ko">Korea</li>
    </ul>
  </div>
</div>
<?php require_once("./index_js.php"); ?>
<?php echo "<pre>";
var_dump($data);
echo "</pre>";
?>
<div id="position_view"></div>
<script>
</script>
<style>
.content {
  width : 650px;
  margin : auto;
}
.map {
  top : 20px;
  float : left;
  right : 10px;
}
.content-right {
  top : 120px;
  margin :auto;
}
.button {
  list-style : none;
  margin : auto;
  margin-top : 20px;
  margin-left : 10px;
  border : 1px solid;
  float : left;
  border-radius : 4px;
  width : 70px;
  text-align : center;
}
</style>
