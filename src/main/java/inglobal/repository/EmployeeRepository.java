package inglobal.repository;

import inglobal.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by evgenyandroshchuk on 06.12.17.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByLastName(String lastName);
    List<Employee> findByFirstNameAndLastName(String first, String lastName);

    Employee findById(int id);

}
