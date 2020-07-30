package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;


@Repository
@Transactional
public class MedicalRecordDAOImpl implements MedicalRecordDAO {

    private static Logger logger = LoggerFactory.getLogger(MedicalRecordDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord editedMedicalRecord) {
        logger.info("MedHelper_LOGS: MedicalRecordDAO: Data about an medical record with the id = "
                + editedMedicalRecord.getMedicalRecordId()+ " is updated");
        if (editedMedicalRecord.getMedicalRecordId() != 0
                && entityManager.find(MedicalRecord.class, editedMedicalRecord.getMedicalRecordId()) != null) {
            try {
                MedicalRecord editedMedRec = entityManager.merge(editedMedicalRecord);
                logger.info("MedHelper_LOGS: MedicalRecordDAO: Successful attempt to edit medical record with an id = "
                        + editedMedicalRecord.getMedicalRecordId());
                return editedMedRec;
            } catch (PersistenceException exception) {
                logger.info(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS: MedicalRecordDAO: Failed attempt to edit an medical record with an id = " + editedMedicalRecord.getMedicalRecordId());
        return null;
    }



    @Override
    public MedicalRecord findMedicalRecordById(int id) {
        logger.info("MedHelper_LOGS: MedicalRecordDAOImpl: Finds a patients medical record (by patient's id)");
        MedicalRecord medicalRecord = entityManager.find(MedicalRecord.class, id);
        if(medicalRecord == null) {
           logger.info("findMedicalRecordById(int id) returned null");
        }
        return medicalRecord;
        //ВЕРНУТЬ ОШИБКУ, ЕСЛИ НЕ НАЙДЕНО
    }
}
