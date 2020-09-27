マップにピンを立てる
<script type="text/javascript" src="https://map.yahooapis.jp/js/V1/jsapi?appid=アプリケーションID"></script>
<div id="map" style="width: 280px; height: 280px;"></div>
<script type="text/javascript">
window.onload() = function() {
  var myMap = new Y.Map("map");
  myMap.drawMap(
    // 中心点緯度経度
    new Y.LatLng(35.701306, 139.700044),
    // ZOOMレベル
    14,
    // レイヤーセット（標準地図）
    Y.LayerSetId.NORMAL
  );

  var myMarker = new Y.Marker(
    // マーカーを置く緯度経度
    new Y.LatLng(35.690921, 139.700258),
  );
  // 吹き出しに表示する文
  myMarker.bindInfoWindow("新宿駅で～す");

  // マーカーを地図に表示
  myMap.addFeature(myMarker);

  // マーカーをクリックしたことにして、吹き出しを表示
  Y.Event.trigger(myMarker, "click");
}
</script>
