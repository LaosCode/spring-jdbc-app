

create table groups(
    group_id   int primary key generated by default as identity,
    group_name varchar(255) not null
);

create table students
(
    student_id int generated by default as identity primary key,
    group_id   int references groups (group_id),
    first_name varchar(255) not null,
    last_name  varchar(255) not null
);


create table courses
(
    course_id          int primary key generated by default as identity,
    course_name        varchar(255) not null,
    course_description varchar(255) not null

);

create table students_courses
(
    student_id int references students (student_id) on delete cascade,
    course_id  int references courses (course_id) on delete cascade
);
