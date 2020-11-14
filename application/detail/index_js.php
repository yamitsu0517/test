<?php if (false) { ?>
<script src="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.js"></script>
<link href="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.css" rel="stylesheet" />
<?php // ルート検索用のもの  ?> 
<script src='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-directions/v3.1.1/mapbox-gl-directions.js'></script>
<link rel='stylesheet' href='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-directions/v3.1.1/mapbox-gl-directions.css' type='text/css' />
<?php } ?>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB9Ly55z9Cf3Vlr4dK6htrIuYX7dGrdKsI&callback=initMap&libraries=places" async defer></script>
<script>
if (<?php echo $is_sp; ?>) {
    var wsize = $(window).width()-2,
        hsize = $(window).height()
        cbtn_wsize = $(window).width()-50;
    $('#map').css("width", wsize + "px").css("height", hsize/2 + "px");
    $('#close_btn').css("margin-left", cbtn_wsize + "px");


    $('#map_btn').on('click', function (){
        console.log("入ったよ");
        $('.areamap').addClass('is_show'); 
    });
    $('#close_btn').on('click', function (){
        console.log("入ったよ");
        $('.areamap').removeClass('is_show'); 
    });
}

var locate_arr = {
  1 : {lat : 35.667062, lng : 139.695101}, // 代々木公園
  2 : {lat : 35.646284, lng : 139.704717}, // amoute w
  3 : {lat : 34.686222, lng : 135.524750}, // 大阪城

}
var map_obj = document.getElementById('map');

  var map,
      marker;
  function initMap() {
console.log(locate_arr['<?php echo $_GET["id"]; ?>']);

    var lat = locate_arr[<?php echo $data['id']?>]['lat'];
    var lng = locate_arr[<?php echo $data['id']?>]['lng'];

    var centerPosition = new google.maps.LatLng(lat, lng);

    map = new google.maps.Map(map_obj, {
      center: centerPosition,
      zoom: 15,
      mapTypeControl: false, //マップタイプ コントロール
      fullscreenControl: false, // 全画面コントロール
    });
    marker = new google.maps.Marker({ // マーカーの追加
      position: centerPosition, // マーカーを立てる位置を指定
      map: map, // マーカーを立てる地図を指定
    });
    var mapOptions = {
    fullscreenControl: true, //全画面表示コントロール
    streetViewControl: true, //ストリートビュー コントロール
    zoomControl: true, //ズーム コントロール
    };

    var service = new google.maps.places.PlacesService(map);
    service.findPlaceFromQuery({
        query: '駅',
    }, function(results, status) {
        if (status == google.maps.places.PlacesServiceStatus.OK) {
            // 配列となっていますが、1件しか返ってきません
            for (var i = 0; i < results.length; i++) {
                var place = results[i];
                console.log(place);
            }
        }
    });
  };

if (false) {
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
    // ルート検索
    map.addControl(
        new MapboxDirections({
            accessToken: mapboxgl.accessToken
        }), 'top-left');
    // 検索エリア
    map.addControl(new MapboxGeocoder({accessToken: mapboxgl.accessToken}), 'top-right');
    <?php if (!$is_sp) { ?>
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
}
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
