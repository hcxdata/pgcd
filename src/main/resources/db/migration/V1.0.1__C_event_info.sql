/**
* 创建事件表
* 201401014
* lxh
*/
CREATE TABLE
    pgcd.event_info
    (
        event_id BIGINT NOT NULL AUTO_INCREMENT,
        event_desc VARCHAR(2000),
        event_feature_word VARCHAR(2000),
        event_time VARCHAR(2000),
        PRIMARY KEY (event_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8