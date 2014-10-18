/**
* 创建话题表
* 201401014
* lxh
*/
CREATE TABLE
    pgcd.topics
    (
        id INT AUTO_INCREMENT,
        event_id INT,
        description VARCHAR(2000),
        feature_word VARCHAR(2000),
        created_on VARCHAR(20),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8