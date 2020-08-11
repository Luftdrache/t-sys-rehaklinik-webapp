package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.TreatmentEventDAO;
import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("NurseService")
@Transactional
public class NurseServiceImpl implements NurseService {

    private static Logger logger = LoggerFactory.getLogger(NurseServiceImpl.class);

    private final TreatmentEventDAO treatmentEventDAO;


    @Override
    public List<TreatmentEventDTO> findOverdueTreatmentEvents() {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in findOverdueTreatmentEvents() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findOverdueTreatmentEvents();
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Override
    public boolean setTreatmentEventToCompleted(int treatmentEventId) {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in setTreatmentEventToCompleted() method");
        TreatmentEvent setToCompleted = treatmentEventDAO.findTreatmentEventById(treatmentEventId);
        if (setToCompleted == null) {
            return false;
        }
        setToCompleted.setTreatmentEventStatus(EventStatus.COMPLETED);
        TreatmentEvent completed = treatmentEventDAO.setCompleted(setToCompleted);
        return completed.getTreatmentEventId() == (setToCompleted.getTreatmentEventId())
                && completed.getTreatmentEventStatus().equals(setToCompleted.getTreatmentEventStatus());
    }


    @Override
    public List<TreatmentEventDTO> getTodayTreatmentEvents() {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in getTodayTreatmentEvents() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findTodayTreatmentEvents();
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Override
    public boolean cancelTreatmentEvent(int treatmentEventId, String cancelReason) {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in cancelTreatmentEvent() method");
        TreatmentEvent treatmentEventToCancel = treatmentEventDAO.findTreatmentEventById(treatmentEventId);
        if (treatmentEventToCancel == null) {
            return false;
        }
        treatmentEventToCancel.setCancelReason(cancelReason);
        treatmentEventToCancel.setTreatmentEventStatus(EventStatus.CANCELLED);
        TreatmentEvent canceled = treatmentEventDAO.cancelTreatmentEvent(treatmentEventToCancel);
        return canceled.getTreatmentEventId() == (treatmentEventToCancel.getTreatmentEventId())
                && canceled.getTreatmentEventStatus().equals(treatmentEventToCancel.getTreatmentEventStatus());
    }


    @Override
    public List<TreatmentEventDTO> findAllCompletedTreatmentEvents() {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in findAllCompletedTreatmentEvents() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findAllCompletedTreatmentEvents();
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Override
    public List<TreatmentEventDTO> findAllPlannedTreatmentEvents() {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in findAllPlannedTreatmentEvents() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findAllPlannedTreatmentEvents();
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Override
    public TreatmentEventDTO findTreatmentEventById(int treatmentEventId) {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in findTreatmentEventById() method");
        TreatmentEvent foundTreatmentEvent = treatmentEventDAO.findTreatmentEventById(treatmentEventId);
        if (foundTreatmentEvent != null) {
            return new TreatmentEventDTO(foundTreatmentEvent);
        }
        return null;
    }


    @Override
    public List<TreatmentEventDTO> findAllTreatmentEvents() {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in findAllTreatmentEvents() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findAllTreatmentEvents();
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Override
    public List<TreatmentEventDTO> getUrgentTreatmentEvents() {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in getUrgentTreatmentEvents() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findUrgentTreatmentEvents();
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Override
    public List<TreatmentEventDTO> findTreatmentEventsByPatientsSurname(String surname) {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - in findTreatmentEventsByPatientsSurname() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findTreatmentEventsByPatientsSurname(surname);
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Autowired
    public NurseServiceImpl(TreatmentEventDAO treatmentEventDAO) {
        this.treatmentEventDAO = treatmentEventDAO;
    }
}
