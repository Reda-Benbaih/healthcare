create table appointment(
    id int auto_increment primary key,
    appointment_date DATE NOT NULL,
    status VARCHAR(50) not null,
    doctor_id INT NOT NULL,
    patient_id INT NOT NULL,
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);
create table doctor(
    id int auto_increment primary key,
    name varchar(255) not null ,
    speciality varchar(255) not null ,
    email varchar(255) not null unique ,
    phone bigint not null unique
);
create table patient(
    id int auto_increment primary key,
    first_name varchar(255) not null ,
    last_name varchar(255) not null,
    email varchar(255) not null ,
    phone bigint not null unique
);
create table medical_file(
    id int auto_increment primary key,
    diagnosis varchar(1024) not null ,
    observation varchar(1024) not null ,
    creation_date date not null,
    patient_id INT NOT NULL,
    CONSTRAINT fk_medical_file_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
)
