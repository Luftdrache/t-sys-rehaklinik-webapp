DROP database rehaklinik;

CREATE DATABASE IF NOT EXISTS rehaklinik CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS rehaklinik.positions (
position_id int (11) NOT NULL AUTO_INCREMENT,
position varchar (50) NOT NULL, 
PRIMARY KEY positions_id_PK (position_id), 
UNIQUE KEY position_UK (position)
);

CREATE TABLE IF NOT EXISTS rehaklinik.qualification_categories(
qualification_category_id int (11) NOT NULL AUTO_INCREMENT,
qualification_category varchar(25)  NOT NULL, 
PRIMARY KEY qualification_category_id_PK (qualification_category_id), 
UNIQUE KEY qualification_category_UK (qualification_category)
);

CREATE TABLE IF NOT EXISTS rehaklinik.roles (
role_id int (11) NOT NULL AUTO_INCREMENT,
role varchar(25) NOT NULL, 
PRIMARY KEY role_id_PK (role_id),
UNIQUE KEY role_UK (role)
);

CREATE TABLE IF NOT EXISTS rehaklinik.working_schedules (
working_schedule_id int (11) NOT NULL AUTO_INCREMENT,	
Sunday bool default false,
Monday bool default false,
Tuesday bool default false,
Wednesday bool default false,
Thursday bool default false,
Friday bool default false,
Saturday bool default false,
working_start_time time NOT NULL,
working_end_time time NOT NULL, 
PRIMARY KEY working_schedule_id_PK (working_schedule_id)
);

CREATE TABLE IF NOT EXISTS rehaklinik.employees (
employee_id int (11) NOT NULL AUTO_INCREMENT,
first_name varchar (50) NOT NULL,
second_name varchar (50), 
surname varchar (50) NOT NULL,
login varchar (35) NOT NULL,
password varchar (35) NOT NULL,
date_of_birth date NOT NULL, 
passport_id int NOT NULL, 
address varchar (100),
phone_number varchar (25) NOT NULL,
email varchar (80), 
position_id int (11) NOT NULL,
qualification_category_id int (11),
working_schedule_id int (11),
cabinet_or_ward_number int(3),
role_id int(11) NOT NULL,
PRIMARY KEY employees_id_PK (employee_id), 
FOREIGN KEY position_FK (position_id) REFERENCES rehaklinik.positions(position_id), 
FOREIGN KEY qualification_category_FK (qualification_category_id) REFERENCES rehaklinik.qualification_categories(qualification_category_id), 
FOREIGN KEY working_schedule_id_FK (working_schedule_id) REFERENCES rehaklinik.working_schedules(working_schedule_id), 
FOREIGN KEY role_FK (role_id) REFERENCES rehaklinik.roles(role_id), 
UNIQUE KEY login_UK (login), 
UNIQUE KEY password_UK (password), 
UNIQUE KEY passport_id_UK (passport_id)
);

CREATE TABLE IF NOT EXISTS rehaklinik.clinical_diagnoses(
clinical_diagnosis_id int (11) NOT NULL AUTO_INCREMENT,
main_disease varchar (50) NOT NULL, 
ICD_10_code varchar (5) NOT NULL, 
accompanying_pathology varchar(50), 
full_diagnosis_description text,
PRIMARY KEY clinical_diagnosis_id_PK (clinical_diagnosis_id) 
);

CREATE TABLE IF NOT EXISTS rehaklinik.medical_records (
medical_record_id int (11) NOT NULL AUTO_INCREMENT,
clinical_diagnosis_id int (11),
hospital_stay_status varchar (15) NOT NULL,
hospital_ward int(3) NOT NULL, 
PRIMARY KEY medical_record_id_PK (medical_record_id), 
FOREIGN KEY clinical_diagnosis_id_FK (clinical_diagnosis_id) REFERENCES rehaklinik.clinical_diagnoses(clinical_diagnosis_id), 
UNIQUE KEY clinical_diagnosis_id_UK (clinical_diagnosis_id)
);

CREATE TABLE IF NOT EXISTS rehaklinik.patients (
patient_id int (11) NOT NULL AUTO_INCREMENT,
first_name varchar (50) NOT NULL,
second_name varchar (50), 
surname varchar (50) NOT NULL,
date_of_birth Date NOT NULL,
gender varchar (6) NOT NULL, 
login varchar (35) NOT NULL,
password varchar (35) NOT NULL,
phone_number varchar (25),
email varchar (80), 
address varchar (100),
passport_id int NOT NULL, 
insurance_policy_code varchar(50) NOT NULL UNIQUE, 
insurance_company varchar(50) NOT NULL UNIQUE, 
attending_doctor_id int (11) NOT NULL, 
medical_record_id int(11),
сonsent_to_personal_data_processing bool NOT NULL,
PRIMARY KEY patient_id_PK (patient_id), 
FOREIGN KEY attending_doctor_FK (attending_doctor_id) REFERENCES rehaklinik.employees(employee_id), 
FOREIGN KEY medical_record_id_FK (medical_record_id) REFERENCES rehaklinik.medical_records(medical_record_id), 
UNIQUE KEY patient_login_UK (login), 
UNIQUE KEY patient_password_UK (password), 
UNIQUE KEY passport_id_UK (passport_id),
UNIQUE KEY medical_record_id_UK (medical_record_id )
);

CREATE TABLE IF NOT EXISTS rehaklinik.treatment_time_patterns(
treatment_time_pattern_id int (11) NOT NULL AUTO_INCREMENT,
count_per_day int (2), 
before_meals bool default false,
at_meals bool default false,
after_meals bool default false,
Sunday bool default false,
Monday bool default false,
Tuesday bool default false,
Wednesday bool default false,
Thursday bool default false,
Friday bool default false,
Saturday bool default false,
PRIMARY KEY treatment_time_pattern_id_PK (treatment_time_pattern_id) 
);

CREATE TABLE IF NOT EXISTS rehaklinik.medicines_and_procedures(
medicine_procedure_id int (11) NOT NULL AUTO_INCREMENT,
medicine_procedure_name varchar (100) NOT NULL, 
treatment_type varchar(9) NOT NULL,  
PRIMARY KEY medicine_procedure_id_PK (medicine_procedure_id), 
UNIQUE KEY medicine_procedure_name_UK (medicine_procedure_name)
);

CREATE TABLE IF NOT EXISTS rehaklinik.prescriptions(
prescription_id int (11) NOT NULL AUTO_INCREMENT,
patient_id int (11) NOT NULL,
medicine_procedure_id int (11) NOT NULL,
dose varchar (20),
administering_medication_method varchar (30),
start_treatment datetime NOT NULL, 
end_treatment datetime NOT NULL, 
treatment_time_pattern_id int (11) NOT NULL,
PRIMARY KEY prescription_id_PK (prescription_id), 
FOREIGN KEY prescriptions_patient_id_FK (patient_id) REFERENCES rehaklinik.patients(patient_id), 
FOREIGN KEY medicine_procedure_id_FK (medicine_procedure_id) REFERENCES rehaklinik.medicines_and_procedures(medicine_procedure_id), 
FOREIGN KEY treatment_time_pattern_id_FK (treatment_time_pattern_id) 
	REFERENCES rehaklinik.treatment_time_patterns(treatment_time_pattern_id) 
);

CREATE TABLE IF NOT EXISTS rehaklinik.treatment_event_statuses(
treatment_event_status_id int (11) NOT NULL AUTO_INCREMENT,
treatment_event_status varchar (100) NOT NULL, 
PRIMARY KEY treatment_event_status_id_PK (treatment_event_status_id), 
UNIQUE KEY treatment_event_status_UK (treatment_event_status)
);

CREATE TABLE IF NOT EXISTS rehaklinik.treatment_events(
treatment_event_id int (11) NOT NULL AUTO_INCREMENT,
patient_id int (11) NOT NULL,
treatment_event_date date NOT NULL, 
treatment_event_time time NOT NULL,
prescription_id int (11) NOT NULL, 
treatment_event_status_id int (11) NOT NULL,
cancel_reason varchar (100),
PRIMARY KEY treatment_event_id_PK (treatment_event_id), 
FOREIGN KEY treatment_event_patient_id_FK (patient_id) REFERENCES rehaklinik.patients(patient_id), 
FOREIGN KEY prescription_id_FK (prescription_id) REFERENCES rehaklinik.prescriptions(prescription_id), 
FOREIGN KEY treatment_event_status_id_FK (treatment_event_status_id) 
	REFERENCES rehaklinik.treatment_event_statuses(treatment_event_status_id) 
);



