/**
* 创建话题表
* 201401014
* lxh
*/
CREATE TABLE
    pgcd.topic_info
    (
        topic_id BIGINT NOT NULL AUTO_INCREMENT,
        event_id BIGINT NOT NULL,
        topic_desc VARCHAR(2000),
        topic_feature_word VARCHAR(2000),
        topic_time VARCHAR(20),
        PRIMARY KEY (topic_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8