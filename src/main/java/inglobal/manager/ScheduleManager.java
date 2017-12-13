package inglobal.manager;

import inglobal.model.Employee;
import inglobal.model.VacationSchedule;
import inglobal.repository.VacationScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<VacationSchedule> findByEmployee(Employee e) {
        return repository.findByEmployee(e);
    }

    public void deleteSchedule(VacationSchedule vacationSchedule) {
        repository.delete(vacationSchedule);
    }

    public VacationSchedule findById(int id) {

        return repository.findById(id);

    }



}
