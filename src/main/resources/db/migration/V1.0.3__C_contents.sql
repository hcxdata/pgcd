/**
* 创建内容表
* 201401014
* lxh
*/
CREATE TABLE
    pgcd.contents
    (
        id INT AUTO_INCREMENT,
        topic_id INT,
        title VARCHAR(2000),
        fresh DOUBLE,
        tim VARCHAR(20),
        typ VARCHAR(20),
        url TEXT,
        weights DOUBLE,
        rowkey VARCHAR(2000),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8