package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dao.PatientDAO;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.fillers.EmployeeFiller;
import com.tsystems.rehaklinik.fillers.PatientDTOFiller;
import com.tsystems.rehaklinik.fillers.PatientFiller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReceptionServiceTest {

    @Mock
    private PatientDAO patientDAO;
    @Mock
    private EmployeeDAO employeeDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Logger mockLogger;

    @InjectMocks
    private ReceptionServiceImpl receptionService; //?!?!?!?!
//    = new ReceptionServiceImpl(patientDAO, employeeDAO, passwordEncoder);


    @Test
    void addNewPatient_should_return_created_patient() {
        mockLogger.info("Logging");
        when(patientDAO.createPatient(any(Patient.class))).thenAnswer((Answer<Patient>) invocation -> {
            Patient patient = (Patient) invocation.getArguments()[0];
            patient.setPatientId(2);
            patient.setFirstName("Peter");
            patient.setSurname("Fletcher");
            return patient;
        });
        PatientDTO patientDTO = PatientDTOFiller.getPatientDTO();
        PatientDTO createdPatient = receptionService.addNewPatient(patientDTO);
        assertNotNull(createdPatient);
        assertEquals(2, createdPatient.getPatientId());
        assertEquals("Peter", createdPatient.getFirstName());
        assertEquals("Fletcher", createdPatient.getSurname());
    }

    @Test
    void editPatient() {
        mockLogger.info("Logging");
        when(employeeDAO.findEmployeeById(1)).thenReturn(EmployeeFiller.getEmployee());
        when(patientDAO.updatePatient(any(Patient.class))).thenAnswer((Answer<Patient>) invocation -> {
            Patient patient = (Patient) invocation.getArguments()[0];
            patient.setInsuranceCompany("HealthyLife");
            patient.setInsurancePolicyCode("AB886749123SG");
            return patient;
        });
        PatientDTO patientDTO = PatientDTOFiller.getPatientDTO();
        assertEquals("Viva Medicare", patientDTO.getInsuranceCompany());
        PatientDTO editedPatient = receptionService.editPatient(patientDTO);
        assertNotNull(editedPatient);
        assertEquals("HealthyLife", editedPatient.getInsuranceCompany());
    }

    @Test
    void deletePatientById_should_return_success() {
        mockLogger.info("Logging");
        given(patientDAO.deletePatient(1)).willReturn(true);
        assertEquals("patient with id = 1 deleted successfully", receptionService.deletePatientById(1));
    }

    @Test
    @DisplayName("Failed employee deleting")
    void deletePatientById_should_return_failed_attempt() {
        mockLogger.info("Logging");
        given(patientDAO.deletePatient(1)).willReturn(false);
        assertEquals("Failed attempt to delete patient with id = 1 (patient does not exist)",
                receptionService.deletePatientById(1));
    }

    @Test
    void showAllPatient_should_return_nonempty_list() {
        mockLogger.info("Logging");
        List<Patient> testPatientsList = new ArrayList<>();
        testPatientsList.add(PatientFiller.getPatient());
        given(patientDAO.findAll()).willReturn(testPatientsList);
        List<PatientShortViewDTO> returnedList = receptionService.showAllPatients();
        assertEquals(testPatientsList.size(), returnedList.size());
        assertEquals(1, testPatientsList.size());
    }

    @Test
    void showAllPatients_should_return_empty_list() {
        mockLogger.info("Logging");
        List<Patient> testPatientsList = new ArrayList<>();
        given(patientDAO.findAll()).willReturn(testPatientsList);
        List<PatientShortViewDTO> returnedList = receptionService.showAllPatients();
        assertTrue(returnedList.isEmpty());
    }

    @Test
    void findPatientBySurname_should_return_an_emptyList() {
        mockLogger.info("Logging");
        List<Patient> testPatientList = new ArrayList<>();
        given(patientDAO.findPatientBySurname("Fletcher")).willReturn(testPatientList);
        List<PatientShortViewDTO> foundPatients = receptionService.findPatientBySurname("Fletcher");
        assertTrue(foundPatients.isEmpty());
    }

    @Test
    void findPatientBySurname_should_return_found_patient() {
        mockLogger.info("Logging");
        List<Patient> testPatientsList = new ArrayList<>();
        testPatientsList.add(PatientFiller.getPatient());
        given(patientDAO.findPatientBySurname("Fletcher")).willReturn(testPatientsList);
        List<PatientShortViewDTO> foundPatients = receptionService.findPatientBySurname("Fletcher");
        assertEquals(testPatientsList.size(), foundPatients.size());
        assertFalse(foundPatients.isEmpty());
        assertEquals(testPatientsList.get(0).getPatientId(), foundPatients.get(0).getPatientId());
    }

    @Test
    void getAllDoctors_should_return_found_doctors() {
        mockLogger.info("Logging");
        List<Employee> testDoctorsList = new ArrayList<>();
        testDoctorsList.add(EmployeeFiller.getEmployee());
        given(employeeDAO.findAllDoctors()).willReturn(testDoctorsList);
        List<EmployeeShortViewDTO> returnedList = receptionService.getAllDoctors();
        assertEquals(testDoctorsList.size(), returnedList.size());
    }

    @Test
    void getAllDoctors_should_return_empty_list() {
        mockLogger.info("Logging");
        List<Employee> testDoctorsList = new ArrayList<>();
        given(employeeDAO.findAllDoctors()).willReturn(testDoctorsList);
        List<EmployeeShortViewDTO> returnedList = receptionService.getAllDoctors();
        assertTrue(returnedList.isEmpty());
    }

    @Test
    void getPatientById_should_return_found_patient() {
        mockLogger.info("Logging");
        Patient testPatient = PatientFiller.getPatient();
        given(patientDAO.findPatientById(2)).willReturn(testPatient);
        PatientDTO foundPatient = receptionService.getPatientById(2);
        assertNotNull(foundPatient);
    }

    @Test
    void getEmployeeById_should_return_null() {
        mockLogger.info("Logging");
        given(patientDAO.findPatientById(100)).willReturn(null);
        PatientDTO foundPatient = receptionService.getPatientById(100);
        assertNull(foundPatient);
    }

    @Test
    void setAttendingDoctor_should_set_doctor_to_a_patient() {
        mockLogger.info("Logging");
        Patient patient = PatientFiller.getPatient();
        patient.setAttendingDoctorId(null);
        assertNull(patient.getAttendingDoctorId());
        given(patientDAO.findPatientById(2)).willReturn(patient);
        Employee doctor = EmployeeFiller.getEmployee();
        given(employeeDAO.findEmployeeById(1)).willReturn(doctor);
        patient.setAttendingDoctorId(doctor);
        Patient patientWithDoctor = PatientFiller.getPatient();
        given(patientDAO.updatePatient(patient)).willReturn(patientWithDoctor);
        PatientDTO patientDTO = receptionService.setAttendingDoctor(1, 2);
        assertNotNull(patient.getAttendingDoctorId());
        assertEquals("Young", patient.getAttendingDoctorId().getSurname());
    }
}