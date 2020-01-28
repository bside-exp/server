CREATE TABLE IF NOT EXISTS exp_offer
(
    id           BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT(20)   NOT NULL,
    title        varchar(200) NOT NULL collate utf8_bin,
    contact      varchar(100) DEFAULT NULL collate utf8_bin,
    email        varchar(100) DEFAULT NULL collate utf8_bin,
    firm_name    varchar(100) DEFAULT NULL collate utf8_bin,
    exp_duration INTEGER(5)   DEFAULT NULL,
    industry_id  bigint(20)   not null,
    duty_id      bigint       not null,
    description  text         not null,
    constraint exp_offer_fk1 foreign key (industry_id) references industry (id),
    constraint exp_offer_fk2 foreign key (duty_id) references duty (id),
    constraint exp_offer_fk3 foreign key (user_id) references user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4,
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS exp_offer_tag
(
    id           BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    tag          varchar(50) NOT NULL,
    exp_offer_id BIGINT(20)  NOT NULL,
    constraint exp_offer_tag_fk1 foreign key (exp_offer_id) references exp_offer (id)
) engine = innodb
  default charset = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS exp_offer_comment
(
    id           BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    comment      varchar(200) NOT NULL collate utf8mb4_unicode_ci,
    user_id      BIGINT(20)   NOT NULL,
    exp_offer_id BIGINT(20)   NOT NULL,
    constraint exp_offer_comment_fk1 foreign key (user_id) references user (id),
    constraint exp_offer_comment_fk2 foreign key (exp_offer_id) references exp_offer (id)
) engine = innodb
  default charset = utf8mb4
  cOLLATE = utf8mb4_unicode_ci;

