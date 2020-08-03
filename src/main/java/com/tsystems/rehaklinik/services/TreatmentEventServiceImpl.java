package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.EventStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service("TreatmentEventService")
@Transactional
public class TreatmentEventServiceImpl implements TreatmentEventService {

    @Override
    public List<TreatmentEvent> generateTreatmentEvents(Prescription prescription) {
        List<TreatmentEvent> treatmentEventsList = new ArrayList<>();

        List<LocalDate> treatmentDates = defineTreatmentDates(prescription);

        LocalTime time = prescription.getTreatmentTimePattern().getPrecisionTime();
        if (prescription.getTreatmentTimePattern().getPrecisionTime() == null) {
            time = LocalTime.of(7, 0, 0);
        }

        int startTime = time.getHour();

        int interval = prescription.getTreatmentTimePattern().getIntervalInHours();

        for (int i = 0; i < treatmentDates.size(); i++) { //days
            if (interval != 0) {
                for (int j = time.getHour() + 1; j <= 24; j += interval) { // hours
                    TreatmentEvent tEvent = new TreatmentEvent();
                    tEvent.setPrescription(prescription);
                    tEvent.setPatient(prescription.getPatient());
                    tEvent.setTreatmentEventDate(treatmentDates.get(i));
                    tEvent.setTreatmentEventTime(LocalTime.of(j, 0, 0));
                    tEvent.setTreatmentEventStatus(EventStatus.PLANNED);
                    treatmentEventsList.add(tEvent);
                }
            } else {
                TreatmentEvent tEvent = new TreatmentEvent();
                tEvent.setPrescription(prescription);
                tEvent.setPatient(prescription.getPatient());
                tEvent.setTreatmentEventDate(treatmentDates.get(i));
                tEvent.setTreatmentEventTime(LocalTime.of(startTime, 0, 0, 0));
                tEvent.setTreatmentEventStatus(EventStatus.PLANNED);
                treatmentEventsList.add(tEvent);
            }
        }
        return treatmentEventsList;
    }


    /**
     * @param prescription
     * @return
     */
    private List<LocalDate> defineTreatmentDates(Prescription prescription) {

        List<LocalDate> treatmentDates = new ArrayList<>();
        LocalDate startDate = prescription.getStartTreatment();
        LocalDate tmpDate;
        LocalDate endDate = prescription.getEndTreatment();

        //If we haven't set a specific day/days of the week,
        // the method generates an event for each day within a specified period
        if(prescription.getTreatmentTimePattern().isSunday() == false
                &&prescription.getTreatmentTimePattern().isMonday() == false
                &&prescription.getTreatmentTimePattern().isTuesday() == false
                &&prescription.getTreatmentTimePattern().isWednesday() == false
                &&prescription.getTreatmentTimePattern().isThursday() == false
                &&prescription.getTreatmentTimePattern().isFriday() == false
                &&prescription.getTreatmentTimePattern().isSaturday() == false
        ){
            while(!startDate.isAfter(endDate)) {
                treatmentDates.add(startDate);
                startDate = startDate.plusDays(1);
            }
            return treatmentDates;
        }

        if (prescription.getTreatmentTimePattern().isMonday()) {
            tmpDate = startDate;
            while (!tmpDate.isAfter(endDate)) {
                if (tmpDate.getDayOfWeek() == DayOfWeek.MONDAY) {
                    treatmentDates.add(tmpDate);
                }
                tmpDate = tmpDate.plusDays(1);
            }
        }
        if (prescription.getTreatmentTimePattern().isTuesday()) {
            tmpDate = startDate;
            while (!tmpDate.isAfter(endDate)) {
                if (tmpDate.getDayOfWeek() == DayOfWeek.TUESDAY) {
                    treatmentDates.add(tmpDate);
                }
                tmpDate = tmpDate.plusDays(1);
            }
        }
        if (prescription.getTreatmentTimePattern().isWednesday()) {
            tmpDate = startDate;
            while (!tmpDate.isAfter(endDate)) {
                if (tmpDate.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                    treatmentDates.add(tmpDate);
                }
                tmpDate = tmpDate.plusDays(1);
            }
        }
        if (prescription.getTreatmentTimePattern().isThursday()) {
            tmpDate = startDate;
            while (!tmpDate.isAfter(endDate)) {
                if (tmpDate.getDayOfWeek() == DayOfWeek.THURSDAY) {
                    treatmentDates.add(tmpDate);
                }
                tmpDate = tmpDate.plusDays(1);
            }
        }
        if (prescription.getTreatmentTimePattern().isFriday()) {
            tmpDate = startDate;
            while (!tmpDate.isAfter(endDate)) {
                if (tmpDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    treatmentDates.add(tmpDate);
                }
                tmpDate = tmpDate.plusDays(1);
            }
        }
        if (prescription.getTreatmentTimePattern().isSaturday()) {
            tmpDate = startDate;
            while (!tmpDate.isAfter(endDate)) {
                if (tmpDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    treatmentDates.add(tmpDate);
                }
                tmpDate = tmpDate.plusDays(1);
            }
        }
        if (prescription.getTreatmentTimePattern().isSunday()) {
            tmpDate = startDate;
            while (!tmpDate.isAfter(endDate)) {
                if (tmpDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    treatmentDates.add(tmpDate);
                }
                tmpDate = tmpDate.plusDays(1);
            }
        }
        return treatmentDates;
    }

}
