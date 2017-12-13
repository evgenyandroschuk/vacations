package inglobal.repository;

import inglobal.model.Employee;
import inglobal.model.VacationSchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by evgenyandroshchuk on 09.12.17.
 */
public interface VacationScheduleRepository extends CrudRepository<VacationSchedule, Long> {

    @Query("select v from VacationSchedule v where v.employee = ?1 order by v.startDate")
    List<VacationSchedule> findByEmployee(Employee employee);
    VacationSchedule findById(int i);



}
