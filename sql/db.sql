# 创建表
create table `study-app`.s_image
(
  id         int auto_increment
  comment '主键ID'
    primary key,
  name       varchar(120) null
  comment '图片名称',
  content    longblob     null
  comment '图片内容',
  type       char         null
  comment '图片类型: 1 调整答题 2 每周答题 3 专项答题',
  status     char         null
  comment '图片状态 0 未处理 1 已处理',
  ocr        varchar(500) null
  comment '图片进行ocr解析后结果',
  createTime datetime     null
  comment '创建时间',
  new_column int          null,
  constraint s_images_id_uindex
  unique (id),
  constraint s_image_ocr_uindex
  unique (ocr)
)
  comment '图片'
  engine = InnoDB;


create table `study-app`.s_question
(
  id         int auto_increment
  comment '主键ID'
    primary key,
  title      varchar(300) null,
  answer     varchar(120) null,
  imageId    int          not null,
  createTime datetime     not null,
  constraint id_UNIQUE
  unique (id)
)
  engine = InnoDB;

