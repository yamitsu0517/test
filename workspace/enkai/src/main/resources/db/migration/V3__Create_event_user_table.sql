CREATE TABLE event_users (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  event_id int(11) NOT NULL COMMENT 'イベントID',
  user_id int(11) NOT NULL COMMENT 'イベント参加者',
  created_at datetime NOT NULL DEFAULT current_timestamp() COMMENT '作成日時',
  modified_at datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日時',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='イベント参加者';

