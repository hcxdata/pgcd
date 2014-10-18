/**
* 创建事件表
* 201401014
* lxh
*/
CREATE TABLE
    pgcd.events
    (
        id INT AUTO_INCREMENT,
        description VARCHAR(2000),
        feature_word VARCHAR(2000),
        created_on DATE,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8