CREATE TABLE subject_tag(
                         id INT NOT NULL AUTO_INCREMENT,
                         subject_name VARCHAR(100) NOT NULL DEFAULT '',
                         create_time BIGINT(20) NOT NULL DEFAULT 0,
                         update_time BIGINT(20) NOT NULL DEFAULT 0,
                         PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE question_info(
  id INT NOT NULL AUTO_INCREMENT,
  subject_id INT NOT NULL DEFAULT 0,
  content TEXT NOT NULL DEFAULT '',
  answer TEXT NOT NULL DEFAULT '',
  correct_rate INT NOT NULL DEFAULT 0,
  counts INT NOT NULL DEFAULT 0,
  create_user INT NOT NULL DEFAULT 0,
  create_time BIGINT(20) NOT NULL DEFAULT 0,
  update_time BIGINT(20) NOT NULL DEFAULT 0,
PRIMARY KEY (id),
INDEX (subject_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE paper_info(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL DEFAULT '',
  subject_id INT NOT NULL DEFAULT 0,
  question_list TEXT NOT NULL DEFAULT '',
  avg_points INT NOT NULL DEFAULT 0,
  counts INT NOT NULL DEFAULT 0,
  create_user INT NOT NULL DEFAULT 0,
  create_time BIGINT(20) NOT NULL DEFAULT 0,
  update_time BIGINT(20) NOT NULL DEFAULT 0,
PRIMARY KEY (id),
INDEX (subject_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
