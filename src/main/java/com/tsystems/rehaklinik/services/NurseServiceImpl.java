package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.TreatmentEventDAO;
import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
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
    public boolean cancelTreatmentEvent(int treatmentEventId, String cancelReason) {
        TreatmentEvent treatmentEventToCancel = treatmentEventDAO.findTreatmentEventById(treatmentEventId);
        if (treatmentEventToCancel == null) {
            return false;
        }
        treatmentEventToCancel.setCancelReason(cancelReason);
        TreatmentEvent canceled = treatmentEventDAO.cancelTreatmentEvent(treatmentEventToCancel);
        if (canceled.getTreatmentEventId() == (treatmentEventToCancel.getTreatmentEventId())
                && canceled.getTreatmentEventStatus().equals(treatmentEventToCancel.getTreatmentEventStatus())) {
            return true;
        }
        return false;
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


    @Autowired
    public NurseServiceImpl(TreatmentEventDAO treatmentEventDAO) {
        this.treatmentEventDAO = treatmentEventDAO;
    }
}
