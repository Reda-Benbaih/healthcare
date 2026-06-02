create table user(
    id int auto_increment primary key,
    username varchar(255) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    user_roles varchar(255) not null
);

ALTER TABLE patient ADD CONSTRAINT fk_patient_user FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE doctor ADD CONSTRAINT fk_doctor_user FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE;