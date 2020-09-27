<?php 

class Common {

  /*
   * SP判定をする関数
   * @return boolean
   *
   */ 
  public static function is_sp ()  
  {
    //ユーザーエージェントを取得
    $ua = $_SERVER['HTTP_USER_AGENT'];
    //スマホと判定する文字リスト
    $ua_list = array('iPhone','iPad','iPod','Android');
    foreach ($ua_list as $ua_smt) {
      //ユーザーエージェントに文字リストの単語を含む場合はTRUE、それ以外はFALSE
      if (strpos($ua, $ua_smt) !== false) {
        return true;
      }
    }
    return false;
  }


}
