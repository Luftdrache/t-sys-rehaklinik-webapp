package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Transactional
public class MedicalRecordDAOImpl implements MedicalRecordDAO {

    private static Logger logger = LoggerFactory.getLogger(MedicalRecordDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public MedicalRecord findMedicalRecordById(int id) {
        logger.info("MedHelper_LOGS: MedicalRecordDAOImpl: Finds a patients medical record (by patient's id)");
        MedicalRecord medicalRecord = entityManager.find(MedicalRecord.class, id);
        return medicalRecord;
        //ВЕРНУТЬ ОШИБКУ, ЕСЛИ НЕ НАЙДЕНО
    }
}
