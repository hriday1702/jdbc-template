create table student (id int,
            first_name varchar(50),
            last_name varchar (50),
            course varchar (50),
            create_date DATE,
            update_date DATE
    );

insert into student values(1, 'Akash','Sharma','Java',SYSDATE(),SYSDATE());
insert into student values(2, 'Aman','Singh','Java',SYSDATE(),SYSDATE());