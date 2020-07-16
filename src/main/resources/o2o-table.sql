create database if not exists o2o;
use o2o;

create table tb_area(
	area_id int(2) not null auto_increment, -- 区域不超过一百个，id自增
    area_name varchar(200) not null, -- 区域名
    priority int(2) not null default 0, -- 区域优先级
    create_time datetime default null, -- 创建时间
    last_edit_time datetime default null, -- 最后修改时间
    primary key(area_id),  -- 设置主键
    unique key UK_AREA(area_name) -- 设置区域名唯一
)ENGINE=InnoDB auto_increment=1 default charset=utf8; -- 设置引擎为InnoDB（行级锁）

create table tb_person_info(
	user_id int(10) not null auto_increment,
    name varchar(32) default null, -- 用户姓名
    profile_img varchar(1024) default null, -- 用户头像路径
    email varchar(1024) default null, -- 用户邮箱
    gendar varchar(2) default null, -- 用户性别
    enable_status int(2) not null default 0 comment '0表示禁用，1表示激活',
    user_type int(2) not null default 1 comment '1.顾客 2.店家 3.超级管理员',
    create_time datetime default null,
    last_edit_time datetime default null,
    primary key(user_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8; -- 设置引擎为InnoDB（行级锁）

create table tb_wechat_auth(
	wechat_auth_id int(10) not null auto_increment,
    user_id int(10) not null, -- 关联用户id
    open_id varchar(1024) not null, -- 开放ID
    create_time datetime default null, -- 创建时间
    primary key(wechat_auth_id),
    unique key uk_wechat_profile(open_id), -- open_id唯一
    constraint fk_wechatauth_profile foreign key(user_id) references tb_person_info(user_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8; -- 设置引擎为InnoDB（行级锁）

create table tb_local_auth(
	local_auth_id int(10) not null auto_increment,
    user_id int(10) not null, -- 关联用户id
    username varchar(128) not null, -- 用户名
    password  varchar(128) not null, -- 密码
    create_time datetime default null, -- 创建时间
    last_edit_time datetime default null, -- 最后修改时间
    primary key(local_auth_id),
    unique key uk_local_profile(username), -- 用户名唯一
    constraint fk_localauth_profile foreign key(user_id) references tb_person_info(user_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8; -- 设置引擎为InnoDB（行级锁）

create table tb_head_line(
	line_id int(100) not null auto_increment,
    line_name varchar(1000) default null, -- 头条名
    line_link  varchar(2000) not null, -- 头条链接
    line_img  varchar(2000) not null, -- 头条图片
	priority int(2) default null, -- 头条优先级
    enable_status int(2) not null default 0, -- 
    create_time datetime default null, -- 创建时间
    last_edit_time datetime default null, -- 最后修改时间
    primary key(line_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8; -- 设置引擎为InnoDB（行级锁）

create table tb_shop_category(
	shop_category_id int(11) not null auto_increment,
    shop_category_name varchar(100) not null default '', -- 商铺分类名
    shop_category_desc varchar(1000) default '', -- 商铺分类描述
    shop_category_img varchar(2000) default null, -- 商铺分类描述图片
	priority int(2) not null default 0, -- 优先级
    create_time datetime default null, -- 创建时间
    last_edit_time datetime default null, -- 最后修改时间
    parent_id int(11) default null, -- 商铺分类的父级分类，如果为null则为最顶级分类
    primary key(shop_category_id),
    constraint fk_shop_category_self foreign key(parent_id) references tb_shop_category(shop_category_id) -- 自链接外键
)ENGINE=InnoDB auto_increment=1 default charset=utf8; -- 设置引擎为InnoDB（行级锁）

create table tb_shop(
	shop_id int(10) not null auto_increment,
    owner_id int(10) not null comment '店铺创始人',
    area_id int(5) default null, -- 商铺所属区域id
    shop_category_id int(11) default null, -- 商铺分类
    shop_name varchar(256) not null, -- 商铺名
    shop_desc varchar(1024) default null, -- 商铺描述
    shop_addr varchar(200) default null, -- 商铺地址
	phone varchar(128) default null, -- 商铺联系方式
    shop_img varchar(1024) default null, -- 商铺图片
	priority int(3) default 0, -- 优先级
    create_time datetime default null, -- 创建时间
    last_edit_time datetime default null, -- 最后修改时间
    enable_status int(2) not null default '0', -- 状态
    advice varchar(255) default null,
    primary key(shop_id),
    constraint fk_shop_area foreign key(area_id) references tb_area(area_id), 
    constraint fk_shop_profile foreign key(owner_id) references tb_person_info(user_id), 
    constraint fk_shop_shopcate foreign key(shop_category_id) references tB_shop_category(shop_category_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8; -- 设置引擎为InnoDB（行级锁）

create table tb_product_category(
	product_category_id int(11) not null auto_increment,
    product_category_name varchar(100) not null, -- 商品名
	priority int(2) default 0, -- 优先级
    create_time datetime default null, -- 创建时间
	shop_id int(20) not null default 0, -- 关联商铺id
    primary key(product_category_id),
    constraint fk_procate_shop foreign key(shop_id) references tb_shop(shop_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8; 

create table tb_product(
	product_id int(100) not null auto_increment,
    product_name varchar(100) not null, -- 商品名
    product_desc varchar(2000) default null, -- 商品描述
    img_addr varchar(2000) default '', -- 缩略图地址
    normal_price varchar(100) default null, -- 原价
	promotion_price varchar(100) default null, -- 折扣价
	priority int(2) not null default 0, -- 优先级
	create_time datetime default null, -- 创建时间
	last_edit_time datetime default null, -- 最后修改时间
	enable_status int(2) not null default '0', -- 状态
	product_category_id int(11) default null, -- 关联商品类别
    shop_id int(20) not null default '0', -- 关联店铺
	primary key(product_id),
	constraint fk_product_procate foreign key(product_category_id) references tb_product_category(product_category_id),
    constraint fk_product_shop foreign key(shop_id) references tb_shop(shop_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8;

create table tb_product_img(
	product_img_id int(20) not null auto_increment,
    img_addr varchar(2000) not null, -- 图片地址
	img_desc varchar(2000) default null, -- 图片描述
    priority int(2) default 0, -- 优先级
    create_time datetime default null, -- 创建时间
	product_id int(20) default null, -- 关联商品id
    primary key(product_img_id),
    constraint fk_proimg_product foreign key(product_id) references tb_product(product_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8; 