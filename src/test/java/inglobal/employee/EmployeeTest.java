package inglobal.employee;

import inglobal.Application;
import inglobal.manager.CheckManager;
import inglobal.manager.EmployeeManager;
import inglobal.manager.ScheduleManager;
import inglobal.model.Employee;
import inglobal.model.VacationSchedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgenyandroshchuk on 10.12.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {

    @Autowired
    EmployeeManager employeeManager;

    @Autowired
    ScheduleManager scheduleManager;

    @Autowired
    CheckManager checkManager;

    @Test
    public void createTest() {

        String fistName = "TestFistName";
        String lastName = "TestLastName";

        System.out.println("Employee create delete test started.............");

        boolean employeeManagerExist = true;

        if(employeeManager != null) {

            //Delete test employee if exists
            Iterator deleteIterator = employeeManager.findByFirstNameLastName(fistName, lastName).iterator();
            while (deleteIterator.hasNext()) {
                Employee e = (Employee) deleteIterator.next();
                System.out.println("deleted: " + e);
            }

            Employee testEmployee = new Employee(fistName, lastName);
            employeeManager.createEmployee(testEmployee);
            System.out.println("Created employee " + testEmployee);

            boolean isExist = false;

            Iterator iterator = employeeManager.findByFirstNameLastName(fistName, lastName).iterator();
            while (iterator.hasNext()) {
                isExist = true;
                Employee e = (Employee) iterator.next();
                System.out.println("Delete employee after created: " + e);
                employeeManager.deleteEmployee(e);
            }



            assert isExist : "Create employee failed!";


        } else {
            employeeManagerExist = false;
        }

        assert employeeManagerExist : "employeeManager is empty!";

        System.out.println("Employee create delete test finished.......");

    }

    @Test
    public void checkDateTest() {


        System.out.println("Check date test started........");
        VacationSchedule vacationSchedule;


        Iterator iterator = scheduleManager.findAll().iterator();
        if(iterator.hasNext()) {
            vacationSchedule = (VacationSchedule) iterator.next();

            Employee employee = vacationSchedule.getEmployee();
            Date startDate = vacationSchedule.getStartDate();
            Date endDate = vacationSchedule.getEndDate();

            boolean isDeniedSameDate = checkManager.checkDate(employee, startDate, endDate).getKey();// if denied return false

            assert !isDeniedSameDate : "Denied same date test Failed!!";

        }
    }
}
