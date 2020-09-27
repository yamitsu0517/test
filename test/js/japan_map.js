$(function() {
    var areaContent = $('[data-id="areaMap_areaContent"]');
    var areaFloat = $('#areaMap_floating');
    $('[data-id="areaMap_btn"]').on("click", function() {
        var index = $(this).data("index");
        areaFloat.addClass("is_show");
        areaContent.removeClass("is_active");
        areaContent.eq(index).addClass("is_active")
    });
    $('[data-id="areaMap_close"]').on("click", function() {
        areaFloat.removeClass("is_show")
    })
    var isSp = isSp();
    if (isSp) {
        var wsize = $(window).width();

        $('.areaMap_map').css("width", wsize + "px");
        $('.map_search').css("width", wsize + "px");
        $('areaFloat').css("width", wsize + "px");
    }

    function isSp() {
      if (navigator.userAgent.match(/iPhone|Android.+Mobile/)) {
        return true;
      } else {
        return false;
      }
    }
});
