package inglobal.repository;

import inglobal.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by evgenyandroshchuk on 06.12.17.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByLastName(String lastName);
}
