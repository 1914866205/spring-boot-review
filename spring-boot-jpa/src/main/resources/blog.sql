drop table if exists blog;
create table blog
(
    blog_id      int         not null auto_increment,
    blog_text    varchar(32) not null,
    blog_summary varchar(32) default null,
    primary key (blog_id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8