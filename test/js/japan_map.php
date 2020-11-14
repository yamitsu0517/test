<link rel="stylesheet" href="./japan_map_<?php echo $is_sp ? 'sp':'pc';?>.css">

<script type="text/javascript" src="japan_map.js"></script>
<div class="map_search">
  <div class="areaMap_map">
    <p class="areaMap_btn areaMap_btn-hokkaido" data-id="areaMap_btn" data-index="北海道">
      <a href="javascript:void(0)">北海道</a>
    </p>
    <p class="areaMap_btn areaMap_btn-tohoku" data-id="areaMap_btn" data-index="東北">
      <a href="javascript:void(0)">東北</a>
    </p>
    <p class="areaMap_btn areaMap_btn-kanto" data-id="areaMap_btn" data-index="関東">
       <a href="javascript:void(0)">関東</a>
    </p>
    <p class="areaMap_btn areaMap_btn-shinetsu" data-id="areaMap_btn" data-index="信越">
      <a href="javascript:void(0)">信越</a>
    </p>
    <p class="areaMap_btn areaMap_btn-hokuriku" data-id="areaMap_btn" data-index="北陸">
      <a href="javascript:void(0)">北陸</a>
    </p>
    <p class="areaMap_btn areaMap_btn-tokai" data-id="areaMap_btn" data-index="東海">
      <a href="javascript:void(0)">東海</a>
    </p>
    <p class="areaMap_btn areaMap_btn-kinki" data-id="areaMap_btn" data-index="近畿">
      <a href="javascript:void(0)">近畿</a>
    </p>
    <p class="areaMap_btn areaMap_btn-chugoku" data-id="areaMap_btn" data-index="中国">
      <a href="javascript:void(0)">中国</a>
    </p>
    <p class="areaMap_btn areaMap_btn-shikoku" data-id="areaMap_btn" data-index="四国">
      <a href="javascript:void(0)">四国</a>
    </p>
    <p class="areaMap_btn areaMap_btn-kyushu" data-id="areaMap_btn" data-index="九州">
      <a href="javascript:void(0)">九州</a>
    </p>
    <p class="areaMap_btn areaMap_btn-okinawa" data-id="areaMap_btn" data-index="沖縄">
      <a href="javascript:void(0)">沖縄</a>
    </p>
  </div>
  <div class="areaMap_floating" id="areaMap_floating">
    <div class="areaMap_area">
      <div class="areaMap_areaContent" data-id="areaMap_areaContent">
        <h3 class="areaMap_areaTtl" id="area_name"></h3>
        <div class="areaMap_areaList">
          <ul class="areaMap_areaList" id="area_list"></ul>
        </div>
      </div>
    </div>
    <p class="areaMap_close" data-id="areaMap_close"><a href="javascript:void(0)" data-rapid_p="87"><span class="icrClose"></span>閉じる</a></p>
  </div>
</div>
