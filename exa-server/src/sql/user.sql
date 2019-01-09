CREATE TABLE user_info(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL DEFAULT '',
  phone BIGINT(11) NOT NULL DEFAULT 0,
  password VARCHAR(100) NOT NULL DEFAULT '',
  acc_type INT NOT NULL DEFAULT 0,
  create_time BIGINT(20) NOT NULL DEFAULT 0,
  update_time BIGINT(20) NOT NULL DEFAULT 0,
PRIMARY KEY (id),
UNIQUE KEY (`phone`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE admin_info(
                         id INT NOT NULL AUTO_INCREMENT,
                         user_id INT NOT NULL DEFAULT 0,
                         title VARCHAR(100) NOT NULL DEFAULT '',
                         subject VARCHAR(100) NOT NULL DEFAULT '',
                         teach_year INT NOT NULL DEFAULT 0,
                         create_time BIGINT(20) NOT NULL DEFAULT 0,
                         update_time BIGINT(20) NOT NULL DEFAULT 0,
                         PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE subject_tag(
                         id INT NOT NULL AUTO_INCREMENT,
                         subject_name VARCHAR(100) NOT NULL DEFAULT '',
                         create_time BIGINT(20) NOT NULL DEFAULT 0,
                         update_time BIGINT(20) NOT NULL DEFAULT 0,
                         PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
