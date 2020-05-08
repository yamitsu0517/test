<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
    $(function() {
        $('div').click(function() {
            console.log($(this).text)
        });

    });
</script>
<?php

echo "<div>Hello World</div>";

?>

<div class="div-color">
  <ul class="color">
    <li class="slide"><div class="red" data-index="1"></div></li>
    <li class="slide"><div class="blue" data-index="2"></div></li>
    <li class="slide"><div class="yellow" data-index="3"></div></li>
    <li class="slide"><div class="pink" data-index="4"></div></li>
    <li class="slide"><div class="aqua" data-index="5"></div></li>
    <li class="slide"><div class="black" data-index="6"></div></li>
    <li class="slide"><div class="orange" data-index="7"></div></li>
    <li class="slide"><div class="green" data-index="8"></div></li>
  </ul>
  <div>  
    <a class="btn prev-btn" href="javascript:void()">◀</a>
    <a class="btn next-btn" href="javascript:void()">▶</a>
  </div>

</div>

<style>

.prev-btn {
   position : absolute;
   margin : 0 ;    
   width: 25px;
   height: 44px;
   background-position: -12px -10px;
   right: 0;
   top: 40px;
   text-indent: -9999px;
   z-index: 1;
}

.next-btn {
    position: absolute;
    width: 25px;
    height: 44px;
    background-position: -12px -10px;
    right: 0;
    top: 40px;
    text-indent: -9999px;
    z-index: 1;
}
.div-color {
    width : 170px;
    overflow : hidden;
}
.btn {
    float : left;
}
/*
ul::before {
    content : "◀";
float : left;
}
*/
ul {
    list-style : none;
    width : 500px;
}
/*
ul::after {
    content : "▶";
float : left;
}
*/
li.slide {
    position : relative;

}
.red {
    background-color : red;
    width : 50px;
    height : 50px;
    float : left;
}
.blue {
    background-color : blue;
    width : 50px;
    height : 50px;
    float : left;
}
.yellow {
    background-color : yellow;
    width : 50px;
    height : 50px;
    float : left;
}
.pink {
    background-color : pink;
    width : 50px;
    height : 50px;
    float : left;
}
.aqua {
    background-color : aqua;
    width : 50px;
    height : 50px;
    float : left;
}
.black {
    background-color : black;
    width : 50px;
    height : 50px;
    float : left;
}
.green {
    background-color : green;
    width : 50px;
    height : 50px;
    float : left;
}
.orange {
    background-color : orange;
    width : 50px;
    height : 50px;
    float : left;
}

</style>





<div id="container">
    <div id="banner">
        <ul>
            <li><a href=""><img src="img/img1.png" width="320" height="213"></a></li>
            <li><a href=""><img src="img/img2.png" width="320" height="213"></a></li>
            <li><a href=""><img src="img/img3.png" width="320" height="213"></a></li>
            <li><a href=""><img src="img/img4.png" width="320" height="213"></a></li>
        </ul>
    </div>
    <a href="javascript:void()" id="btn-prev">◀</a>
    <a href="javascript:void()" id="btn-next">▶</a>
    <ul>
        <li></li>
    </ul>
</div>

<style>

#container {
width: 326px;
margin: 50px auto;
}
#banner {
    border: 3px solid #CCCCCC;
    margin-bottom: 5px;
}
#banner ul {
    list-style: none;
    position: relative;
    margin-left: 0px;
    overflow: hidden;
    width:320px;
    height: 213px;
}
#banner ul li {
    float: left;
    width: 320px;
    position: absolute;
}
div#pagenation {
   position: relative;
   overflow: hidden;
   top: -30px;
}

div#pagenation ul {
    position:relative;
    left:50%;
    float:left;
    list-style: none;
}
div#pagenation li {
   position:relative;
   left:-50%;
   float:left;
   margin: 0 2px;

}
div#pagenation li a {
    width:12px;
    height:12px;
    overflow:hidden;
    display:block;
    background-position:0 0;
}
div#pagenation li.active a,
div#pagenation li a:hover {
    background-position:0 12px;
}
div#pagenation li a img {
    visibility:hidden;
    display: none;
}
#btn-prev {
    position: relative;
    left: -40px;
    top: -130px;
}
#btn-next {
    position: relative;
    left: 355px;
    top: -130px;
}

</style>

<script>
(function($) {
$(function(){
    var _imgNum = 0;    //画像の枚数
    var _imgSize = 0;   //画像のサイズ
    var _current = 0;   //現在の画像
    var _timer = 3000;  //タイマー時間
     
    //画像サイズ取得
    _imgSize = $('#banner ul li').width();
         
    //メイン画像の数だけ繰り返す
    $('#banner ul li').each(function(){
        //画像をずらして外に配置
        $(this).css('margin-left', -_imgSize);
        //画像の数だけページネーションボタンを作成
        if (_imgNum == _current) {
            //currentだったらアクティブ、メインの画像は表示
            $('#pagenation ul').append('<li class="active"><a href="#"><img src="img/pagenation.gif" width="12" height="24" /></a></li>');
            $(this).css('margin-left', '0px');
        } else {
            $('#pagenation ul').append('<li><a href="#"><img src="img/pagenation.gif" width="12" height="24" /></a></li>');
        }
        //ループの数をカウントして_imgNumに入れる
        _imgNum++;
    });
     
    //一定時間ごとにimageMoveを実行
    var loopSwitch = setInterval(loop, _timer);
    function loop() {
        imageMove(_current +1);
    }
     
    //ボタンをクリック
    $('#btn-next').click(function(e){
        e.preventDefault();
        imageMove(_current +1);
    });
    $('#btn-prev').click(function(e){
        e.preventDefault();
        imageMove(_current -1);
    });
    //ページネーションクリック
    $('#pagenation ul li').click(function(e) {
        e.preventDefault();
        var thisNum = $('#pagenation li').index(this);
        //押したボタンが現在の画像じゃなかったら実行
        if(thisNum  != _current) {
            imageMove(thisNum );
        }
    });
     
    function imageMove(next) {
        //ループ時間リセット
        clearInterval(loopSwitch);
        loopSwitch = setInterval(loop, _timer);
        //次の画像が次の画像より多きかったら右に配置（小さかったら左）
        var pos;
        if (_current < next) {
            pos = -_imgSize;
        } else {
            pos = _imgSize;
        }
         
        //次の画像が最後なら1枚目、１枚目なら最後
        if (next == _imgNum) {
            next = 0;
        } else if(next == -1) {
            next = (_imgNum-1);
        }
 
        //次の画像を動かす
        $("#banner li").eq(next)
        //次の画像を次の位置に配置
        .css("margin-left", pos)
        .animate({
            marginLeft: "0"
        },"fast");
         
        //現在の画像を動かす
        $("#banner li").eq(_current)
        .animate({
            marginLeft: -pos
        },"fast");
         
        //ページネーション現在のを消し次のをアクティブに
        $('#pagenation li').eq(_current).removeClass('active');
        $('#pagenation li').eq(next).addClass('active');
         
        //現在の番号を次の番号にする。
        _current = next;
    }
});
})(jQuery);

</script>

