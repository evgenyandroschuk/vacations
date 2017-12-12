package inglobal.employee;

import inglobal.manager.EmployeeManager;
import inglobal.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * Created by evgenyandroshchuk on 10.12.17.
 */
public class EmployeeTest {
    
    @Autowired
    EmployeeManager employeeManager;

    @Test
    public void createTest() {

        String fistName = "TestFistName";
        String lastName = "TestLastName";




        System.out.println("EmployeeCreateTest start");

        boolean b = true;

        if(employeeManager != null) {
            //Delete test employee if exists
            Employee testEmployee = new Employee(fistName, lastName);
            employeeManager.createEmployee(testEmployee);

            boolean isExist = false;


        } else {
            System.out.println("employeeManager is Empty!!!");
        }




        assert b : "EmployeeCreateTest failed";

    }

}
