<script src="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.js"></script>
<link href="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.css" rel="stylesheet" />
<?php // ルート検索用のもの  ?> 
<script src='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-directions/v3.1.1/mapbox-gl-directions.js'></script>
<link rel='stylesheet' href='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-directions/v3.1.1/mapbox-gl-directions.css' type='text/css' />
<script>
var  strage = localStorage;
localStorage.setItem('access', <?php echo $data['id'];?>);
console.log(strage);
mapboxgl.accessToken = 'pk.eyJ1IjoieWFtaXRzdSIsImEiOiJja2V6ZHFzZ2gwODR1MnFsbnU3bjBvcm9qIn0.eF2oWmGTTOjNDda4UmK_5A';
var map = new mapboxgl.Map({
  container: 'map',
//  style: 'mapbox://styles/mapbox/streets-v11',
  style: 'mapbox://styles/yamitsu/ckf6p3nou0qtj1ao17qba873b',
//  style: 'mapbox://styles/mapbox/light-v10',
 center: [<?php echo $data['location_y']?>, <?php echo $data['location_x']?>],
  zoom : 14,
  localIdeographFontFamily: "'Noto Sans', 'Noto Sans CJK SC', sans-serif"
});
map.addControl(
    new MapboxDirections({
        accessToken: mapboxgl.accessToken
    }), 'top-left');
<?php //if (!$is_sp) { ?>
<?php if (false) { ?>
// map をスクロールでズームできなくする
map.scrollZoom.disable();
<?php } ?>
$('.button').click(function() {
  var language = $(this).data('id');
  map.setLayoutProperty('country-label', 'text-field', [
    'get',
    'name_' + language
  ]);
  test();
});
var marker = new mapboxgl.Marker()
.setLngLat([139.695062, 35.667044])
.addTo(map);

var geojson = {
  type: 'FeatureCollection',
  features: [{
    type: 'Feature',
    geometry: {
      type: 'Point',
      coordinates: [139.695062, 35.667044]
    },
    properties: {
      title: 'Mapbox',
      description: '代々木公園'
    }
  } // カンマでつなげられるよ
  ]
};
// 単発で取得
/*function test() {
    navigator.geolocation.getCurrentPosition(test2);
}*/

var num = 0;
var watch_id;
function test() {
    watch_id = navigator.geolocation.watchPosition(test2, function(e) { alert(e.message); }, {"enableHighAccuracy": true, "timeout": 20000, "maximumAge": 2000});
}
function clear() {
    navigator.geolocation.clearWatch(watch_id);
}
function test2(position) {

    var geo_text = "緯度:" + position.coords.latitude + "\n";
    geo_text += "経度:" + position.coords.longitude + "\n";
    geo_text += "高度:" + position.coords.altitude + "\n";
    geo_text += "位置精度:" + position.coords.accuracy + "\n";
    geo_text += "高度精度:" + position.coords.altitudeAccuracy  + "\n";
    geo_text += "移動方向:" + position.coords.heading + "\n";
    geo_text += "速度:" + position.coords.speed + "\n";

    var date = new Date(position.timestamp);

    geo_text += "取得時刻:" + date.toLocaleString() + "\n";
    geo_text += "取得回数:" + (++num) + "\n";

    document.getElementById('position_view').innerHTML = geo_text;
}
/* 単発
function test2(position) {

    var geo_text = "緯度:" + position.coords.latitude + "\n";
    geo_text += "経度:" + position.coords.longitude + "\n";
    geo_text += "高度:" + position.coords.altitude + "\n";
    geo_text += "位置精度:" + position.coords.accuracy + "\n";
    geo_text += "高度精度:" + position.coords.altitudeAccuracy  + "\n";
    geo_text += "移動方向:" + position.coords.heading + "\n";
    geo_text += "速度:" + position.coords.speed + "\n";

    var date = new Date(position.timestamp);

    geo_text += "取得時刻:" + date.toLocaleString() + "\n";

    alert(geo_text);

}
 */
</script>
