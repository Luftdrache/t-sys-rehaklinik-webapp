package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dao.PatientDAO;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.converters.DTOconverters.PatientDTOConverter;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service("ReceptionService")
@Transactional
public class ReceptionServiceImpl implements ReceptionService {

    private static Logger logger = LoggerFactory.getLogger(ReceptionServiceImpl.class);

    private final PatientDAO patientDAO;
    private final EmployeeDAO employeeDAO;
    private final PasswordEncoder passwordEncoder;


    @Override
    public PatientDTO getPatientById(int id) {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in getPatientById() method");
        Patient foundPatient = patientDAO.findPatientById(id);
        if (foundPatient != null) {
            return PatientDTOConverter.toDTO(foundPatient);
        }
        return null;
    }


    @Override
    public PatientDTO addNewPatient(PatientDTO patientDTO) {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in addNewPatient() method");
        patientDTO.getAuthenticationDataPatient().
                setPassword(passwordEncoder.encode(patientDTO.getAuthenticationDataPatient().getPassword()));
        Patient newPatientToSave = PatientDTOConverter.fromDTO(patientDTO);
        Patient newPatient = patientDAO.createPatient(newPatientToSave);
        PatientDTO savedPatientDTO = PatientDTOConverter.toDTO(newPatient);
        return savedPatientDTO;
    }

    @Override
    public PatientDTO editPatient(PatientDTO patientDTO) {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in editPatient() method");
        Employee employee = employeeDAO.findEmployeeById(patientDTO.getAttendingDoctorId().getEmployeeId());
        Patient patientToEdit = PatientDTOConverter.fromDTO(patientDTO);
        patientToEdit.setAttendingDoctorId(employee);
        Patient edited = patientDAO.updatePatient(patientToEdit);
        PatientDTO editedPatient = PatientDTOConverter.toDTO(edited);
        return editedPatient;
    }

    @Override
    public List<EmployeeShortViewDTO> getAllDoctors() {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in getAllDoctors() method");
        List<Employee> doctors = employeeDAO.findAllDoctors();
        List<EmployeeShortViewDTO> doctorsDTO = new ArrayList<>();
        if (!doctors.isEmpty()) {
            for (Employee doc : doctors) {
                doctorsDTO.add(new EmployeeShortViewDTO(doc));
                logger.info(doc.toString());
            }
        }
        return doctorsDTO;
    }

    @Override
    public String deletePatientById(int id) {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in deletePatientById() method");
        boolean result = patientDAO.deletePatient(id);
        if (result) {
            return "patient with id = " + id + "deleted successfully";
        }
        return "Failed attempt to delete patient with id = " + id + " (patient does not exist)";
    }


    @Override
    public List<PatientShortViewDTO> showAllPatients() {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in showAllPatients() method");
        List<Patient> allPatients = patientDAO.findAll();
        List<PatientShortViewDTO> patientsDTO = new ArrayList<>();
        if (!allPatients.isEmpty()) {
            for (Patient patient : allPatients) {
                patientsDTO.add(new PatientShortViewDTO(patient));
            }
            return patientsDTO;
        }
        return Collections.emptyList();
    }

    @Override
    public PatientDTO setAttendingDoctor(int doctorId, int patientId) {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in setAttendingDoctor() method");
        Patient patient = patientDAO.findPatientById(patientId);
        patient.setAttendingDoctorId(employeeDAO.findEmployeeById(doctorId));
        Patient patientWithDoctor = patientDAO.updatePatient(patient);
        return PatientDTOConverter.toDTO(patientWithDoctor);
    }

    @Override
    public List<PatientShortViewDTO> findPatientBySurname(String surname) {
        logger.info("MedHelper_LOGS: In ReceptionServiceImpl --> in findPatientBySurname() method");
        List<Patient> allFoundPatients = patientDAO.findPatientBySurname(surname);
        List<PatientShortViewDTO> patientShortViewDTOS = new ArrayList<>();
        if (!allFoundPatients.isEmpty()) {
            for (Patient patient : allFoundPatients) {
                patientShortViewDTOS.add(new PatientShortViewDTO(patient));
            }
            return patientShortViewDTOS;
        }
        return null;
    }

    @Autowired
    public ReceptionServiceImpl(PatientDAO patientDAO, EmployeeDAO employeeDAO, AdminService adminService, PasswordEncoder passwordEncoder) {
        this.patientDAO = patientDAO;
        this.employeeDAO = employeeDAO;
        this.passwordEncoder = passwordEncoder;
    }
}
