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
    public boolean setTreatmentEventToCompleted(int treatmentEventId) {
        logger.info("MedHelper_LOGS: In NurseServiceImpl - handler method  getUrgentTreatmentEvents(), GET");
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
        TreatmentEvent foundTreatmentEvent = treatmentEventDAO.findTreatmentEventById(treatmentEventId);
        if (foundTreatmentEvent != null) {
            return new TreatmentEventDTO(foundTreatmentEvent);
        }
        return null;
    }


    @Override
    public List<TreatmentEventDTO> findAllTreatmentEvents() {
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


    @Autowired
    public NurseServiceImpl(TreatmentEventDAO treatmentEventDAO) {
        this.treatmentEventDAO = treatmentEventDAO;
    }
}
