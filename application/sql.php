<?php
class Make_Sql {

  public function do_sql ($params = array())
  {
    $dsn = 'mysql:host=mysql1.php.xdomain.ne.jp;dbname=programapp_database;charset=utf8';
    $dsn = 'mysql:host=localhost;dbname=test;charset=utf8';
    
     // データベースのユーザー名
    $user = 'programapp_yako';
    $user = 'root';
     
      // データベースのパスワード
    $password = 'doraemon0517';
    $password = '';
    $conditions = ' where ';
    $cnt = 0;
    // tryにPDOの処理を記述
    try {
     
      // PDOインスタンスを生成
      $dbh = new PDO($dsn, $user, $password);
    
    
      $sql = "SELECT * FROM LOCATION";

      if (!empty($params)) { 
        // 条件があれば実行する
        foreach ($params as $key => $val) {
            if ($cnt != 0) {
                $conditions .= ' AND ';
            }
            $conditions .= $key . "='" . $val . "'";
        }
        $sql .= $conditions;
      }
      $sql .= ';';
      $stmt = $dbh->query($sql);
      $data = [];
      foreach ($stmt as $row) {
          echo "<pre>";
          $data[] = $row;
          echo "</pre>";
      }
     
    // エラー（例外）が発生した時の処理を記述
    } catch (PDOException $e) {
     
      // エラーメッセージを表示させる
      echo 'データベースにアクセスできません！' . $e->getMessage();
     
      // 強制終了
      exit;
     
    }
    return $data;
  }
  public function test($params) {
      $data = [];
      foreach ($params as $param) {
          $data[] = $param;
      }
      return $data;
  }
}
?>
