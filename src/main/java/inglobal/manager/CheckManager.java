package inglobal.manager;

import inglobal.model.Employee;
import inglobal.model.VacationSchedule;
import inglobal.repository.VacationScheduleRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgenyandroshchuk on 12.12.17.
 */
@Service
public class CheckManager {


    private int yearlyVacationDays = 28;

    @Autowired
    private VacationScheduleRepository vacationScheduleRepository;

    public Pair<Boolean, String>  checkDate(Employee employee, Date startDate, Date endDate) {

        Pair<Boolean, String> result;

        if(endDate.before(startDate)) {
            return new Pair<>(false, "Дата окончания отпуска не может быть раньше даты начала!");
        }

        Pair<Boolean, String> cross = findCrossVacations(startDate, endDate);
        if(cross.getKey()) {
            return new Pair<>(false, cross.getValue()) ;
        }

        int sumOfDays = sumOfVacationDays(employee, startDate, endDate) ;
        if(sumOfDays > yearlyVacationDays ) {
            String reason = "Сумарное количество дней отпуска в году не должно превышать " + yearlyVacationDays + ", сейчас " + sumOfDays;
            return new Pair<>(false, reason);
        }

        return new Pair<>(true, "Ok");


    }


    private Pair<Boolean, String> findCrossVacations(Date startDate, Date endDate) {

        Pair<Boolean, String> result = new Pair<>(false, null);

        Iterator<VacationSchedule>  iterator = vacationScheduleRepository.findAll().iterator();
        while( iterator.hasNext()) {
            VacationSchedule vacationSchedule = (VacationSchedule) iterator.next();
            if( (startDate.equals(vacationSchedule.getStartDate() )) ||
                    (startDate.after(vacationSchedule.getStartDate()) && startDate.before(vacationSchedule.getEndDate())) ||
                    (vacationSchedule.getStartDate().after(startDate) && vacationSchedule.getStartDate().before(endDate))) {

                String empl = "Даты отпуска пересекаются с сотрудником " + vacationSchedule.getEmployee().getFirstName() + " "
                        + vacationSchedule.getEmployee().getLastName() + " c " + vacationSchedule.getStartDate() + " по "
                        + vacationSchedule.getEndDate();

                return new Pair<>(true, empl) ;

            }

        }

        return result;

    }

    private int sumOfVacationDays(Employee employee, Date startDate, Date endDate) {
        int sum = calculateDate(startDate, endDate); // days of new vacation
        Iterator<VacationSchedule> iterator = vacationScheduleRepository.findByEmployee(employee).iterator();
        while (iterator.hasNext()) {



            VacationSchedule schedule = (VacationSchedule) iterator.next();
            if(startDate.getYear() == schedule.getStartDate().getYear()) {
                int i = calculateDate(schedule.getStartDate(), schedule.getEndDate());
                sum += i;
            }
        }

        return sum;
    }

    public int calculateDate(Date startDate, Date endDate) {

        long diff = endDate.getTime() - startDate.getTime();
        int i = (int) TimeUnit.MILLISECONDS.toDays(diff);
        return i+1;
    }

}
