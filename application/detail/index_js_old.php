<?php // JS ファイル ?>
<head>
<script>
mapboxgl.accessToken = 'pk.eyJ1IjoieWFtaXRzdSIsImEiOiJja2V6ZHFzZ2gwODR1MnFsbnU3bjBvcm9qIn0.eF2oWmGTTOjNDda4UmK_5A';
var map = new mapboxgl.Map({
  container: 'map',
  style: 'mapbox://styles/mapbox/streets-v11',
//  style: 'mapbox://styles/mapbox/light-v10',
 center: [<?php echo $data['location_y']?>, <?php echo $data['location_x']?>],
  zoom : 13,
});

$('.button').click(function() {
  var language = $(this).data('id');
  map.setLayoutProperty('country-label', 'text-field', [
    'get',
    'name_' + language
  ]);
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
</script>
</head>
