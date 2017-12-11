package inglobal.repository;

import inglobal.model.VacationSchedule;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by evgenyandroshchuk on 09.12.17.
 */
public interface VacationScheduleRepository extends CrudRepository<VacationSchedule, Long> {



}
