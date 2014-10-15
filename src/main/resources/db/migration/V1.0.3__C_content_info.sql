/**
* 创建内容表
* 201401014
* lxh
*/
CREATE TABLE
    pgcd.content_info
    (
        content_id BIGINT NOT NULL AUTO_INCREMENT,
        topic_id BIGINT NOT NULL,
        content_title VARCHAR(2000),
        content_fresh VARCHAR(2000),
        content_time VARCHAR(20),
        content_type VARCHAR(2000),
        content_url VARCHAR(2000),
        content_weights VARCHAR(2000),
        content_rowkey VARCHAR(2000),
        PRIMARY KEY (content_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8