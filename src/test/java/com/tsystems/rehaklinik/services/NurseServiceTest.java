package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.TreatmentEventDAO;
import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.fillers.TreatmentEventFiller;
import com.tsystems.rehaklinik.jms.MessageSender;
import com.tsystems.rehaklinik.services.implementations.NurseServiceImpl;
import com.tsystems.rehaklinik.types.EventStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NurseServiceTest {

    private final static int TEST_ID = 1;

    @Mock
    private TreatmentEventDAO treatmentEventDAO;
    @Mock
    private MessageSender messageSender;
    @Mock
    private Logger mockLogger;

    @InjectMocks
    NurseServiceImpl nurseService;


    @Test
    void findAllTreatmentEvents_should_return_nonempty_list_when_found_events() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        treatmentEventList.add(treatmentEvent);
        treatmentEventList.add(treatmentEvent);
        given(treatmentEventDAO.findAllTreatmentEvents()).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEventsList = nurseService.findAllTreatmentEvents();
        assertEquals(2, foundEventsList.size());
    }

    @Test
    void findAllTreatmentEvents_should_return_empty_list_when_not_found_events() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        given(treatmentEventDAO.findAllTreatmentEvents()).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEventsList = nurseService.findAllTreatmentEvents();
        assertTrue(foundEventsList.isEmpty());
    }


    @Test
    void findAllPlannedTreatmentEvents_should_return_nonempty_list_when_found_planned_events() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        treatmentEvent.setTreatmentEventStatus(EventStatus.PLANNED);
        treatmentEventList.add(treatmentEvent);
        treatmentEventList.add(treatmentEvent);
        given(treatmentEventDAO.findAllPlannedTreatmentEvents()).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEventsList = nurseService.findAllPlannedTreatmentEvents();
        assertFalse(foundEventsList.isEmpty());
        assertEquals(EventStatus.PLANNED, foundEventsList.get(0).getTreatmentEventStatus());
    }

    @Test
    void findAllPlannedTreatmentEvents_should_return_empty_list_when_not_found_planned_events() {
        given(treatmentEventDAO.findAllPlannedTreatmentEvents()).willReturn(null);
        nurseService.findAllPlannedTreatmentEvents();
        assertTrue(nurseService.findAllPlannedTreatmentEvents().isEmpty());
    }


    @Test
    void findAllCompletedTreatmentEvents_should_return_nonempty_list_when_found_completed_events() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        treatmentEvent.setTreatmentEventStatus(EventStatus.COMPLETED);
        treatmentEventList.add(treatmentEvent);
        assertEquals(EventStatus.COMPLETED, treatmentEvent.getTreatmentEventStatus());
        given(treatmentEventDAO.findAllCompletedTreatmentEvents()).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEventsList = nurseService.findAllCompletedTreatmentEvents();
        assertFalse(foundEventsList.isEmpty());
        assertEquals(EventStatus.COMPLETED, foundEventsList.get(0).getTreatmentEventStatus());
    }

    @Test
    void findTreatmentEventById_should_return_found_treatment_event() {
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(treatmentEvent);
        assertNotNull(nurseService.findTreatmentEventById(TEST_ID));
    }

    @Test
    void findTreatmentEventById_should_return_null() {
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(null);
        assertNull(nurseService.findTreatmentEventById(TEST_ID));
    }

    @Test
    void cancelTreatmentEvent_should_return_true() {
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(treatmentEvent);
        TreatmentEvent cancelled = TreatmentEventFiller.getTreatmentEvent();
        cancelled.setTreatmentEventStatus(EventStatus.CANCELLED);
        given(treatmentEventDAO.cancelTreatmentEvent(treatmentEvent)).willReturn(cancelled);
        assertTrue(nurseService.cancelTreatmentEvent(TEST_ID, "Cancel Reason"));
    }

    @Test
    void cancelTreatmentEvent_should_return_false() {
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(null);
        assertFalse(nurseService.cancelTreatmentEvent(TEST_ID, "Cancel Reason"));
    }

    @Test
    void setTreatmentEventToCompleted_should_return_true() {
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(treatmentEvent);
        TreatmentEvent completed = TreatmentEventFiller.getTreatmentEvent();
        completed.setTreatmentEventStatus(EventStatus.COMPLETED);
        given(treatmentEventDAO.setCompleted(treatmentEvent)).willReturn(completed);
        assertTrue(nurseService.setTreatmentEventToCompleted(TEST_ID));
    }

    @Test
    void setTreatmentEventToCompleted_should_return_false() {
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(null);
        assertFalse(nurseService.setTreatmentEventToCompleted(TEST_ID));
    }

    @Test
    void getUrgentTreatmentEvents_should_return_nonempty_list() {
        List<TreatmentEvent> urgentEventsList = new ArrayList<>();
        TreatmentEvent urgent = TreatmentEventFiller.getTreatmentEvent();
        urgentEventsList.add(urgent);
        given(treatmentEventDAO.findUrgentTreatmentEvents()).willReturn(urgentEventsList);
        List<TreatmentEventDTO> foundTreatmentEvents = nurseService.getUrgentTreatmentEvents();
        assertFalse(foundTreatmentEvents.isEmpty());
    }

    @Test
    void getUrgentTreatmentEvents_should_return_an_empty_list() {
        List<TreatmentEvent> urgentEventsList = new ArrayList<>();
        given(treatmentEventDAO.findUrgentTreatmentEvents()).willReturn(urgentEventsList);
        List<TreatmentEventDTO> foundTreatmentEvents = nurseService.getUrgentTreatmentEvents();
        assertTrue(foundTreatmentEvents.isEmpty());
    }


    @Test
    void getTodayTreatmentEvents_should_return_nonempty_list() {
        List<TreatmentEvent> todayEventsList = new ArrayList<>();
        TreatmentEvent today = TreatmentEventFiller.getTreatmentEvent();
        todayEventsList.add(today);
        given(treatmentEventDAO.findTodayTreatmentEvents()).willReturn(todayEventsList);
        List<TreatmentEventDTO> foundTreatmentEvents = nurseService.getTodayTreatmentEvents();
        assertFalse(foundTreatmentEvents.isEmpty());
    }

    @Test
    void findTreatmentEventsByPatientsSurname_should_return_nonempty_list() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        treatmentEventList.add(treatmentEvent);
        given(treatmentEventDAO.findTreatmentEventsByPatientsSurname("Young")).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundTreatmentEvents = nurseService.findTreatmentEventsByPatientsSurname("Young");
        assertFalse(foundTreatmentEvents.isEmpty());
    }

    @Test
    void findTreatmentEventsByPatientsSurname_should_return_empty_list() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        given(treatmentEventDAO.findTreatmentEventsByPatientsSurname("Young")).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundTreatmentEvents = nurseService.findTreatmentEventsByPatientsSurname("Young");
        assertTrue(foundTreatmentEvents.isEmpty());
    }

    @Test
    void findOverdueTreatmentEvents_should_return_nonempty_list() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        treatmentEventList.add(treatmentEvent);
        given(treatmentEventDAO.findOverdueTreatmentEvents()).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundTreatmentEvents = nurseService.findOverdueTreatmentEvents();
        assertFalse(foundTreatmentEvents.isEmpty());
    }
}