package inglobal.manager;

import inglobal.model.Employee;
import inglobal.model.EmployeeVacation;
import inglobal.model.VacationSchedule;
import inglobal.repository.EmployeeRepository;
import inglobal.repository.VacationScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by evgenyandroshchuk on 10.12.17.
 */
@Service
@EnableAutoConfiguration
public class EmployeeManager {

    private int year = new Date().getYear();

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public VacationScheduleRepository vacationScheduleRepository;

    @Autowired
    public CheckManager checkManager;


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

    public List<EmployeeVacation> findEmployeeVacations() {

        List<EmployeeVacation> employeeVacationList = new ArrayList<>();
        Iterator iterator = findAll().iterator();
        while(iterator.hasNext()) {

            Employee employee = (Employee)iterator.next();
            int vacationNr = 1;
            String vacations = "";
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            int daysOfVacation = 0;
            for(VacationSchedule vacationSchedule : vacationScheduleRepository.findByEmployee(employee)) {



                if(vacationSchedule.getStartDate().getYear() == year ) {
                    String start = format.format(vacationSchedule.getStartDate());
                    String end = format.format(vacationSchedule.getEndDate());
                    String vacationPeriodStr = vacationNr + ": " + start + " - " + end + ";\n";
                    vacations = vacations.concat(vacationPeriodStr);
                    vacationNr += 1;

                    daysOfVacation += checkManager.calculateDate(vacationSchedule.getStartDate(), vacationSchedule.getEndDate());
                }


            }

            EmployeeVacation employeeVacation = new EmployeeVacation(employee, vacations);
            if(daysOfVacation > 0) {
                employeeVacation.setCountOfDays(daysOfVacation);
            }
            employeeVacationList.add(employeeVacation);

        }

        return employeeVacationList;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }
}
