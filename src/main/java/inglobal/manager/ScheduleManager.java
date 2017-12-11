package inglobal.manager;

import inglobal.model.VacationSchedule;
import inglobal.repository.VacationScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

/**
 * Created by evgenyandroshchuk on 10.12.17.
 */
@Service
@EnableAutoConfiguration
public class ScheduleManager {

    @Autowired
    private VacationScheduleRepository repository;

    public void createSchedule(VacationSchedule vacationSchedule) {
        repository.save(vacationSchedule);
    }



}
