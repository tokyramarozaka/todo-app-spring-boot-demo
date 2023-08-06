create table todo
(
    id          serial
        primary key,
    title       varchar(200) not null,
    description text,
    deadline    timestamp
        constraint todo_deadline_check
            check (deadline > CURRENT_DATE),
    priority    integer
        constraint todo_priority_check
            check ((priority >= 0) AND (priority <= 10)),
    done        boolean default false
);