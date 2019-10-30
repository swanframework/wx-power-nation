create table `study-app`.s_image
(
	id int auto_increment,
	content longblob null,
	category char null,
	basicOcr varchar(3000) null,
	accurateOcr varchar(3000) null,
	status char null,
	createTime datetime null,
	constraint s_image_id_uindex
		unique (id)
);

alter table `study-app`.s_image
	add primary key (id);

create table `study-app`.s_question
(
	id int auto_increment,
	imageId int not null,
	title varchar(300) null,
	titleLines int null,
	answer varchar(100) null,
	answerIdx int null,
	createTime datetime null,
	options varchar(1000) null,
	constraint s_question_id_uindex
		unique (id)
);

alter table `study-app`.s_question
	add primary key (id);



