package inglobal.manager;

import inglobal.model.Employee;
import inglobal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by evgenyandroshchuk on 10.12.17.
 */
@Service
@EnableAutoConfiguration
public class EmployeeManager {

    @Autowired
    public EmployeeRepository employeeRepository;


    public void createEmployee(Employee employee) {

        employeeRepository.save(employee);

    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }


    public Employee findById(int id) {
       return employeeRepository.findById(id);
    }

    public Iterable<Employee> findAll() {
       return employeeRepository.findAll();
    }

    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    public List<Employee> findByFirstNameLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }





}
