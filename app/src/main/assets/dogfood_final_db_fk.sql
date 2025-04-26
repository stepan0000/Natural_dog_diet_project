drop table if exists trait_nutrient_add_factor;
drop table if exists trait_nutrient_mult_factor;
drop table if exists dogfood_nutrient;
drop table if exists dog_trait;
drop table if exists breed_kind_nutrient;
drop table if exists  breed_kind;
drop table if exists  dog;
drop table if exists  nutrient;
drop table if exists  trait;
drop table if exists  dogfood;
drop table if exists  breed;

create table if not exists breed_kind (id integer primary key, name varchar, caption text);

insert into breed_kind values (1,'small','Мелкие (до 10 кг)') on conflict do nothing ;
insert into breed_kind values (2,'average','Средние (10-25 кг)') on conflict do nothing ;
insert into breed_kind values (3,'big','Крупные (более 25 кг)') on conflict do nothing ;


create table if not exists dogfood (id integer primary key, name varchar, caption varchar);

insert into dogfood values  (11, 'beef_flank','Говядина, пашина') on conflict do nothing ;
insert into dogfood values  (12, 'beef_clipping','Говядина, вырезка') on conflict do nothing ;
insert into dogfood values  (13, 'beef_bones','Говядина, кости') on conflict do nothing ;
insert into dogfood values  (14, 'beef_ofal_liver','Говядина, субпродукты, печень') on conflict do nothing ;
insert into dogfood values  (15, 'beef_ofal_brain','Говядина, субпродукты, мозги') on conflict do nothing ;
insert into dogfood values  (16, 'beef_ofal_heart','Говядина, субпродукты, сердце') on conflict do nothing ;
insert into dogfood values  (17, 'beef_ofal_lung','Говядина, субпродукты, легкое') on conflict do nothing ;
insert into dogfood values  (18, 'beef_ofal_kidneys','Говядина, субпродукты, почки') on conflict do nothing ;
insert into dogfood values  (19, 'beef_ofal_udder','Говядина, субпродукты, вымя') on conflict do nothing ;

insert into dogfood values  (21, 'lamb_flank','Ягнятина, пашина') on conflict do nothing;
insert into dogfood values  (22, 'lamb_clipping','Ягнятина, вырезка') on conflict do nothing;
insert into dogfood values  (23, 'lamb_bones','Ягнятина, кости') on conflict do nothing;
insert into dogfood values  (24, 'lamb_печень','Ягнятина, печень') on conflict do nothing;

insert into dogfood values  (31, 'fish_cod','Рыба, треска') on conflict do nothing;;
insert into dogfood values  (32, 'fish_salmon','Рыба, лосось') on conflict do nothing;;
insert into dogfood values  (33, 'fish_tuna','Рыба, тунец') on conflict do nothing;;
insert into dogfood values  (34, 'fish_herring','Рыба, сельдь') on conflict do nothing;;
insert into dogfood values  (35, 'fish_pollok','Рыба, минтай') on conflict do nothing;;

insert into dogfood values  (41, 'veg_cucumber','Овощи, огурец') on conflict do nothing;;
insert into dogfood values  (42, 'veg_tomato','Овощи, помидор') on conflict do nothing;;
insert into dogfood values  (43, 'veg_carrot','Овощи, морковь') on conflict do nothing;;
insert into dogfood values  (44, 'veg_salade','Овощи, салат') on conflict do nothing;;
insert into dogfood values  (45, 'veg_cabbage','Овощи, капуста') on conflict do nothing;;
insert into dogfood values  (46, 'veg_beet','Овощи, свекла') on conflict do nothing;;

insert into dogfood values  (51, 'fruit_banana','Фрукты, банан')on conflict do nothing;;
insert into dogfood values  (52, 'fruit_orange','Фрукты, апельсин') on conflict do nothing;;
insert into dogfood values  (53, 'fruit_tangerine','Фрукты, мандарин') on conflict do nothing;;
insert into dogfood values  (54, 'fruit_apple','Фрукты, яблоко') on conflict do nothing;;
insert into dogfood values  (55, 'fruit_pear','Фрукты, груша') on conflict do nothing;;
insert into dogfood values  (56, 'fruit_mango','Фрукты, манго') on conflict do nothing;;

insert into dogfood values  (61, 'grain_porridge','Злаки, овсянка') on conflict do nothing;;
insert into dogfood values  (62, 'grain_buckwheat','Злаки, гречка') on conflict do nothing;;
insert into dogfood values  (63, 'grain_bran','Злаки, отруби') on conflict do nothing;;
insert into dogfood values  (64, 'grain_rice','Злаки, рис') on conflict do nothing;;

insert into dogfood values  (71, 'milk_milk','Молочные продукты, молоко') on conflict do nothing;;
insert into dogfood values  (72, 'milk_kefir','Молочные продукты, кефир') on conflict do nothing;;
insert into dogfood values  (73, 'milk_yogurt','Молочные продукты, йогурт') on conflict do nothing;;
insert into dogfood values  (74, 'milk_smetana','Молочные продукты, сметана') on conflict do nothing;;

insert into dogfood values  (81, 'fowl_chicken_thigh','Птица, курица, бедро') on conflict do nothing;;
insert into dogfood values  (82, 'fowl_chicken_breast ','Птица, курица, грудная часть') on conflict do nothing;;
insert into dogfood values  (83, 'fowl_turkey_thigh','Птица, индейка, бедро') on conflict do nothing;;
insert into dogfood values  (84, 'fowl_turkey_breast ','Птица, индейка, грудная часть') on conflict do nothing;;
insert into dogfood values  (85, 'fowl_duck_breast','Птица, утка, бедро') on conflict do nothing;;
insert into dogfood values  (86, 'fowl_duck_breast','Птица, утка, грудная часть') on conflict do nothing;;


create table if not exists nutrient (id integer primary key, name varchar, caption varchar);
insert into nutrient values  (1, 'protein','Белки') on conflict do nothing ;
insert into nutrient values  (2, 'fat','Жиры') on conflict do nothing ;
insert into nutrient values  (3, 'carbohydrates','Углеводы') on conflict do nothing ;
insert into nutrient values  (4, 'fiber','Клетчатка') on conflict do nothing ;

create table if not exists dogfood_nutrient(id integer primary key  ,id_dogfood integer references  dogfood, id_nutrient integer references  nutrient, g_per_100g numeric(4,1))
create unique index if not exists uq_dogfood_nutrient on dogfood_nutrient(id_dogfood,id_nutrient);

/*Заполняем таблицы питательных веществ*/

/*говядина*/
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (11,1,18.9) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (11,2,16.6) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (12,1,18.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (12,2,16) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (13,1,15) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (13,2,5) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (14,1,15) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (14,2,5) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (15,1,9.5) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (15,2,9.5) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (16,1,15) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (16,2,3) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (17,1,12.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (17,2,13.7) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (18,1,12.5) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (18,2,1.8) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (19,1,16.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (19,2,2.5) on conflict do nothing ;


/*злаки*/
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (61,1,16.9) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (61,2,6.9) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (61,23,66.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (61,4,10.6) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (62,1,13) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (62,2,3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (62,3,68) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (62,4,20) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (63,1,15.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (63,2,4.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (63,3,64.5) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (63,4,42.8) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (64,1,6.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (64,2,0.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (64,3,79.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (64,4,1.8) on conflict do nothing ;

/*Молочные продукты*/

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,1,3.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,2,3.7) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,3,4.6) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,1,3.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,2,0.93) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,3,4.5) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,1,7.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,2,3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,3,12.2) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,1,3.5) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,2,10.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,3,7.1) on conflict do nothing ;


/*Рыба*/
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,1,16) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,2,0.6) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,1,20) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,2,8.1) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,1,19.4) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,2,1) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,1,18) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,2,9) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,1,19.4) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,2,1) on conflict do nothing ;

/*Птица*/

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (81,1,18) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (81,2,8) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (82,1,23.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (82,2,1.9) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (82,3,0.4) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (83,1,21.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (83,2,2.5) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (83,3,0.15) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (84,1,23.7) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (84,2,1.5) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (84,3,0.1) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (85,1,14) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (85,2,16) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (85,1,16) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (85,2,14.9) on conflict do nothing ;

/*Овощи*/

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (41,1,0.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (41,2,0.1) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (41,3,2.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (41,4,1) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (42,1,0.9) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (42,2,0.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (42,3,3.9) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (42,4,1.2) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (43,1,1) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (43,2,0.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (43,3,9.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (43,4,2.8) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (44,1,1.4) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (44,2,0.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (44,3,2.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (44,4,1.1) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (45,1,1.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (45,2,0.1) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (45,3,5.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (45,4,2.5) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (46,1,1.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (46,2,0.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (46,3,9.6) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (46,4,2.8) on conflict do nothing ;

/*Фрукты*/

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,1,1.1) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,2,0.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,3,22.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (51,4,2.6) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,1,0.9) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,2,0.1) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,3,11.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (52,4,2.4) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,1,0.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,2,0.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,3,13.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (53,4,1.8 ) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,1,0.3) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,2,0.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,3,13.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (54,4,2.4 ) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,1,0.4) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,2,0.1) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,3,15.2) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,4,3.1 ) on conflict do nothing ;

insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,1,0.8) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,2,0.4) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,3,15) on conflict do nothing ;
insert into  dogfood_nutrient (id_dogfood, id_nutrient, g_per_100g) values (55,4,1.6 ) on conflict do nothing ;


create table if not exists breed (id integer primary key , name varchar, caption varchar, id_breed_kind integer references  breed_kind);

insert into breed values (1,'DOG','Дворняга',2) on conflict do nothing ;
insert into breed values (2,'WCP','Вельш корги пемброк',2) on conflict do nothing ;
insert into breed values (3,'WCС','Вельш корги кардиган',3) on conflict do nothing ;
insert into breed values (4,'GS','Немецкая овчарка',3) on conflict do nothing ;


create table if not exists trait (id integer primary key , name varchar, caption varchar, mass_factor numeric(4,2), mult_factor numeric(4,2));

insert into trait  values (1, 'pregnant_5_week', 'Беременная до 5 недели',  0,  1.25) on conflict do nothing ;
insert into trait  values (2, 'pregnant_6_7_week', 'Беременная до 6-7 недели', 0, 1.25*1.25) on conflict do nothing ;
insert into trait values  (3, 'puppy', 'Щенок', 0.07, 1) on conflict do nothing ;
insert into trait values  (4, 'adult', 'Средняя', 0.03, 1) on conflict do nothing ;
insert into trait values  (5, 'old', 'Пожилая', 0.02, 1) on conflict do nothing ;
insert into trait values (6, 'male', 'Кобель', 0, 1) on conflict do nothing ;
insert into trait values (7, 'female', 'Сука',  0, 1) on conflict do nothing ;
insert into trait values (8, 'active', 'Активная (гуляет около 1 часа в день)',  0, 0.04/0.03) on conflict do nothing ;
insert into trait values (9, 'very_active', 'Очень активная (гуляет более 3 ч в день)',  0, 0.05/0.03) on conflict do nothing ;
insert into trait values (10, 'sofa', 'Диванная',  0, 1) on conflict do nothing ;

create table if not exists dog  (id integer primary key , id_breed integer references breed, kg numeric(4,1), age integer, id_breed_kind integer references breed_kind, name varchar);

create table if not exists dog_trait (id integer primary key , id_dog integer references dog, id_trait references trait);

create unique index if not exists uq_dog_trait on dog_trait(id_dog,id_trait);

/*Тестовая собака*/
insert into dog values (1, 2, 16.5, 3, 2,'Яшма') on conflict do nothing ;

insert into dog_trait (id_dog,id_trait) values (1, 4) on conflict do nothing;
insert into dog_trait (id_dog,id_trait) values (1, 7) on conflict do nothing;
insert into dog_trait (id_dog,id_trait) values (1, 9) on conflict do nothing;
insert into dog_trait (id_dog,id_trait) values (1, 2) on conflict do nothing;

create table if not exists breed_kind_nutrient  (id integer primary key,
                                               id_breed_kind integer references breed_kind,
                                               id_nutrient integer references nutrient,
                                               min_value_min numeric (4,1),
                                               min_value_max numeric (4,1),
                                               opt_value_min numeric (4,1),
                                               opt_value_max numeric (4,1),
                                               norm_value_min_g_per_kg numeric (4,1),
                                               norm_value_max_g_per_kg numeric (4,1)
                                               );

/*нормы для мелких собак*/

create unique index if not exists uq_dog_kind_nutrient on breed_kind_nutrient(id_breed_kind,id_nutrient);

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (1,1,18,22,25,30,4,6) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (1,2,5,8,10,15,1,2) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (1,3,30,50,30,50,8,12) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (1,4,1,15,2,5,0.3,0.5) on conflict do nothing;

/*нормы для средних собак*/
insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (2,1,18,22,25,30,3,5) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (2,2,5,8,10,15,1,1.5) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (2,3,30,50,30,50,6,10) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (2,4,1,15,2,5,0.2,0.4) on conflict do nothing;

/*нормы для крупных собак*/
insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (3,1,18,22,25,30,2.5,4) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (3,2,5,8,10,15,0.8,1.2) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (3,3,30,50,30,50,4,8) on conflict do nothing;

insert into breed_kind_nutrient (id_breed_kind,id_nutrient,min_value_min,min_value_max,opt_value_min,opt_value_max,norm_value_min_g_per_kg,norm_value_max_g_per_kg)
values (3,4,1,15,2,5,0.1,0.3) on conflict do nothing;

/*поправки к питательным веществам*/

create table if not exists trait_nutrient_mult_factor (id integer primary key,
                                                  id_trait integer references trait,
                                                  id_nutrient integer references nutrient,
                                                  min_mult_factor numeric(4,1),
                                                  max_mult_factor numeric (4,2));

create unique index if not exists uq_trait_nutrient_mult_factor on trait_nutrient_mult_factor(id_trait,id_nutrient);

insert into trait_nutrient_mult_factor (id_trait,id_nutrient,min_mult_factor,max_mult_factor) values (3,1,1.5,2) on conflict do nothing;
insert into trait_nutrient_mult_factor (id_trait,id_nutrient,min_mult_factor,max_mult_factor) values (1,1,1.25,1.3) on conflict do nothing;
insert into trait_nutrient_mult_factor (id_trait,id_nutrient,min_mult_factor,max_mult_factor) values (2,1,1.25,1.5) on conflict do nothing;
insert into trait_nutrient_mult_factor (id_trait,id_nutrient,min_mult_factor,max_mult_factor) values (1,2,1.25,1.3) on conflict do nothing;
insert into trait_nutrient_mult_factor (id_trait,id_nutrient,min_mult_factor,max_mult_factor) values (2,2,1.25,1.5) on conflict do nothing;

/*поправки к питательным веществам*/

create table if not exists trait_nutrient_add_factor (id integer primary key,
                                                       id_trait integer references trait,
                                                       id_nutrient integer references nutrient,
                                                       min_add_factor numeric(4,1),
                                                       max_add_factor numeric (4,2));

create unique index if not exists uq_trait_nutrient_add_factor on trait_nutrient_add_factor(id_trait,id_nutrient);

insert into trait_nutrient_add_factor (id_trait,id_nutrient,min_add_factor,max_add_factor) values (8,2,1,1.5) on conflict do nothing;
insert into trait_nutrient_add_factor (id_trait,id_nutrient,min_add_factor,max_add_factor) values (9,2,1.5,2) on conflict do nothing;

create table if not exists dog_diet (id integer primary key, id_dog integer references dog, id_dogfood integer references dogfood, mass_g numeric(4,1));

insert into dog_diet (id_dog,id_dogfood,mass_g) values (1,11,1000) on conflict do nothing;
insert into dog_diet (id_dog,id_dogfood,mass_g) values (1,54,50) on conflict do nothing;