insert into courses(course_name, course_description) values ('course_1','course_1');
insert into courses(course_name, course_description) values ('course_2','course_2');
insert into courses(course_name, course_description) values ('course_3','course_3');

insert into groups(group_name) values ('group1');
insert into groups(group_name) values ('group2');
insert into groups(group_name) values ('group3');

insert into students(group_id, first_name, last_name) VALUES (1,'Alex','lastname_1');
insert into students(group_id, first_name, last_name) VALUES (1,'Alex','lastname_2');
insert into students(group_id, first_name, last_name) VALUES (2,'firstname_3','lastname_2');

insert into students_courses(student_id, course_id) VALUES (1,1);
insert into students_courses(student_id, course_id) VALUES (1,2);
insert into students_courses(student_id, course_id) VALUES (1,3);
insert into students_courses(student_id, course_id) VALUES (2,1);
insert into students_courses(student_id, course_id) VALUES (2,2);
insert into students_courses(student_id, course_id) VALUES (3,1);
