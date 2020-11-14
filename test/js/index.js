$(function() {
    var val = '';
    $('#btn').on('click', function() {
        val = $('#text_box').val();
        if (val == '') {
            swal({
                title: "※※注意※※",
                text: "キーワードを入力してください。",
                confirmButtonText: '了解！',
                confirmButtonColor: '#DD6B55',
                closeOnClickOutside : false,   //枠外クリックは許可しない
                icon : "info",
            });
            return false;
        }
        $.ajax({
            // リクエスト方法
            type: "GET",
            // 送信先ファイル名
            url: "search.php",
            // 受け取りデータの種類
            datatype: "json",
            // 送信データ
            data:{
                // #id_numberのvalueをセット
                "val" : val 
            },
            // 通信が成功した時
            success: function(data) {

                console.log("通信成功");
                console.log(data);
                var search_result_obj = $('#search_result');
                search_result_obj.html('');
                $('.search_result').show();
                if (data.length == 0) {
                    search_result_obj.append('<span>検索結果は<span>0件</span>でした。</span>');
                    $('#search_text').html('');
                } else {
                    $('#search_text').html(' ' + data.length + '件');
                    for (var i=0; i < data.length;i++) {
                        console.log(data[i]);
                        search_result_obj.append(
                            '<p class="res_list"><a href="detail/index.php?id=' + String(data[i]['id']) + '">' + data[i]['name'] + '</a></p>'
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
    // text field のエンターキーを無効化する
    $("input"). keydown(function(e) {
        if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
            return false;
        } else {
            return true;
        }
    });
});
