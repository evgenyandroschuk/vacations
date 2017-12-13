package inglobal.employee;

import inglobal.Application;
import inglobal.manager.EmployeeManager;
import inglobal.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

/**
 * Created by evgenyandroshchuk on 10.12.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {

    @Autowired
    EmployeeManager employeeManager;

    @Test
    public void createTest() {

        String fistName = "TestFistName";
        String lastName = "TestLastName";

        System.out.println("EmployeeCreateTest start");

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

    }

}
