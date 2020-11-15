<!DOCTYPE html>
<html>
<script type="text/javascript" charset="utf-8" src="https://map.yahooapis.jp/js/V1/jsapi?appid=dj00aiZpPW5uUWdjTkdFc2phSCZzPWNvbnN1bWVyc2VjcmV0Jng9OTY-"></script>
<script type="text/javascript">
window.onload = function(){
    var ymap = new Y.Map("map");
    //ymap.drawMap(new Y.LatLng(35.66572, 139.73100), 17, Y.LayerSetId.NORMAL);
    ymap.drawMap(
        new Y.LatLng(35.667062, 139.695100), // 中心点緯度軽度
        18, // zoom レベル
        Y.LayerSetId.NORMAL // レイヤーセット（標準レベル)
    );

    ymap.addControl(new Y.SliderZoomControlVertical());
    ymap.addControl(new Y.LayerSetControl());
    ymap.addControl(new Y.ScaleControl());
    var myMarker = new Y.Marker(
        // マーカーを置く緯度経度
        new Y.LatLng(35.667062, 139.695100), // 中心点緯度軽度
    );
    // 吹き出しに表示する文
    myMarker.bindInfoWindow("代々木公園聖地");
    // マーカーを地図に表示
    ymap.addFeature(myMarker);
}
</script>
<body>

<h1>test</h1>

<div id="map" style="width:400px; height:300px"></div>
