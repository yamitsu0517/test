<script src="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.css" rel="stylesheet" />
<style>
	body { margin: 0; padding: 0; }
	#map { position: absolute; top: 0; bottom: 0; width: 100%; }
</style>
</head>
<body>
<style>
#buttons {
width: 90%;
margin: 0 auto;
}
.button {
display: inline-block;
position: relative;
cursor: pointer;
width: 20%;
padding: 8px;
border-radius: 3px;
margin-top: 10px;
font-size: 12px;
text-align: center;
color: #fff;
background: #ee8a65;
font-family: sans-serif;
font-weight: bold;
}
</style>
<div id="map"></div>
<ul id="buttons">
  <li id="button-ja" class="button" data-id="ja">日本語</li>
  <li id="button-en" class="button" data-id="en">英語</li>
  <li id="button-ko" class="button" data-id="ko">Korea</li>
</ul>
<script>
	mapboxgl.accessToken = 'pk.eyJ1IjoieWFtaXRzdSIsImEiOiJja2V6ZHFzZ2gwODR1MnFsbnU3bjBvcm9qIn0.eF2oWmGTTOjNDda4UmK_5A';
var map = new mapboxgl.Map({
container: 'map',
//style: 'mapbox://styles/mapbox/light-v10',
style: 'mapbox://styles/mapbox/streets-v11',
center: [16.05, 48],
zoom: 2.9
// center: [139.695062, 35.667044],
// zoom : 13,
});
$('.button').click(function() {
  var language = $(this).data('id');
  map.setLayoutProperty('country-label', 'text-field', [
    'get',
    'name_' + language
  ]);
});
/*
document
.getElementById('buttons')
.addEventListener('click', function (event) {
var language = event.target.id.substr('button-'.length);
// Use setLayoutProperty to set the value of a layout property in a style layer.
// The three arguments are the id of the layer, the name of the layout property,
// and the new property value.
map.setLayoutProperty('country-label', 'text-field', [
'get',
'name_' + language
]);
});*/
</script>
