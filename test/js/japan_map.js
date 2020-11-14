$(function() {
    // 地図エリアが押下された時に、押下されたものを格納する
    var val = '';

    var areaContent = $('[data-id="areaMap_areaContent"]');
    var areaFloat = $('#areaMap_floating');
    var isSp = isSp();
    if (isSp) {
        var wsize = $(window).width() - 2;

        $('.areaMap_map').css("width", wsize + "px");
        $('.map_search').css("width", wsize + "px");
        $('.areaFloat').css("width", wsize + "px");
        $('.areaMap_floating').css("width", wsize + "px");
    }

    function isSp() {
      if (navigator.userAgent.match(/iPhone|Android.+Mobile/)) {
        return true;
      } else {
        return false;
      }
    }

    $('[data-id="areaMap_btn"]').on('click', function() {
       
        val =  ($(this).data('index'));
        $.ajax({
            // リクエスト方法
            type: "POST",
            // 送信先ファイル名
            url: "map_ajax.php",
            // 受け取りデータの種類
            datatype: "json",
            // 送信データ
            data:{
                // #id_numberのvalueをセット
                "val" : val 
            },
            // 通信が成功した時
            success: function(data) {

                var area_name = $('#area_name');
                var area_list = $('#area_list');

                // リセット
                area_name.html('');
                area_list.html('');
                var temp_start = '<li class="areaMap_areaItem"><span class="ArrowRight"></span>';
                var temp_end   = '</li>';
                console.log("通信成功");
                console.log(data);
                // 取得データがなかった時
                if (data.length < 1) {
                    swal({
                        title: "申し訳ございません",
                        text: "データを取得できませんでした。"
                               + "時間を置いてから再度お試しください。それでも接続できない場合、お手数ですが管理者にご連絡お願いいたします。",
                        confirmButtonText: '了解！',
                        confirmButtonColor: '#DD6B55',
                        closeOnClickOutside : false,   //枠外クリックは許可しない
                        icon : "error",
                    });
                    return false;

                } else {

                    areaFloat.addClass("is_show");
                    areaContent.removeClass("is_active");
                    areaContent.addClass("is_active")
                    for (var i=0; i < data.length;i++) {
                        if (i === 0) area_name.html(data[i]['area']);
                        console.log(data[i]);
                        area_list.append(
                           temp_start + '<a href="detail/index.php?id=' + String(data[i]['id']) + '">' + data[i]['name'] + '</a>' + temp_end
                        );
                    }
                }
            },

            // 通信が失敗した時
            error: function(data) {
                console.log("通信失敗");
                console.log(data);
            }
        });
    });
    $('[data-id="areaMap_close"]').on("click", function() {
        areaFloat.removeClass("is_show")
    })
 
});
