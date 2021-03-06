package com.tsystems.rehaklinik.services.implementations;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.services.interfaces.TreatmentEventGenerationService;
import com.tsystems.rehaklinik.types.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service("TreatmentEventService")
@Transactional
public class TreatmentEventGenerationServiceImpl implements TreatmentEventGenerationService {

    private static Logger logger = LoggerFactory.getLogger(TreatmentEventGenerationServiceImpl.class);
    
    private static final int DEFAULT_MINUTE = 0;
    private static final int DEFAULT_SECOND = 0;

    /**
     * Generates treatment event(-s) (with status = PLANNED) immediately after a new prescription added to database
     *
     * @param prescription new prescription
     * @return List with all generated treatment events
     */
    @Override
    public List<TreatmentEvent> generateTreatmentEvents(Prescription prescription) {
        logger.info("MedHelper_LOGS: In TreatmentEventGenerationServiceImpl --> in generateTreatmentEvents() method");
        List<TreatmentEvent> treatmentEventsList = new ArrayList<>();

        List<LocalDate> treatmentDates = defineTreatmentDates(prescription);

        LocalTime time = prescription.getTreatmentTimePattern().getPrecisionTime();

        int interval = prescription.getTreatmentTimePattern().getIntervalInHours();

        for (int i = 0; i < treatmentDates.size(); i++) { //days
            if (interval != 0) {
                for (int j = time.getHour() ; j < 24; j += interval) { // hours
                    TreatmentEvent tEvent = new TreatmentEvent();
                    tEvent.setPrescription(prescription);
                    tEvent.setPatient(prescription.getPatient());
                    tEvent.setTreatmentEventDate(treatmentDates.get(i));
                    tEvent.setTreatmentEventTime(LocalTime.of(j, DEFAULT_MINUTE, DEFAULT_SECOND));
                    tEvent.setTreatmentEventStatus(EventStatus.PLANNED);
                    treatmentEventsList.add(tEvent);
                }
            } else {
                TreatmentEvent tEvent = new TreatmentEvent();
                tEvent.setPrescription(prescription);
                tEvent.setPatient(prescription.getPatient());
                tEvent.setTreatmentEventDate(treatmentDates.get(i));
                tEvent.setTreatmentEventTime(LocalTime.of(time.getHour(), time.getMinute(), DEFAULT_SECOND));
                tEvent.setTreatmentEventStatus(EventStatus.PLANNED);
                treatmentEventsList.add(tEvent);
            }
        }
        return treatmentEventsList;
    }


    /**
     * Specifies the dates on which treatment should be performed
     *
     * @param prescription new prescription
     * @return list with all dates when treatment should be performed
     */
    private List<LocalDate> defineTreatmentDates(Prescription prescription) {
        logger.info("MedHelper_LOGS: In TreatmentEventGenerationServiceImpl --> in defineTreatmentDates() method");
        List<LocalDate> treatmentDates = new ArrayList<>();
        LocalDate startDate = prescription.getStartTreatment();
        LocalDate tmpDate;
        LocalDate endDate = prescription.getEndTreatment();

        //If we haven't set a specific day/days of the week,
        // the method generates an event for each day within a specified period
        if (!prescription.getTreatmentTimePattern().isSunday()
                && !prescription.getTreatmentTimePattern().isMonday()
                && !prescription.getTreatmentTimePattern().isTuesday()
                && !prescription.getTreatmentTimePattern().isWednesday()
                && !prescription.getTreatmentTimePattern().isThursday()
                && !prescription.getTreatmentTimePattern().isFriday()
                && !prescription.getTreatmentTimePattern().isSaturday()
        ) {
            while (!startDate.isAfter(endDate)) {
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
