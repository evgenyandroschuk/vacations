package inglobal;

import inglobal.manager.CheckManager;
import inglobal.manager.EmployeeManager;
import inglobal.manager.ScheduleManager;
import inglobal.model.Employee;
import inglobal.model.VacationSchedule;
import inglobal.repository.VacationScheduleRepository;
import javafx.scene.control.CheckMenuItem;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by evgenyandroshchuk on 04.12.17.
 */
@Controller
@EnableAutoConfiguration
public class WebController {


    @Autowired
    private VacationScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeManager employeeManager;

    @Autowired
    private ScheduleManager scheduleManager;

    @Autowired
    private CheckManager checkManager;


    @RequestMapping("/employee/request")
    public String requestEmployee() {
        return "employee/request";
    }


    @RequestMapping("/employee/response")
    public String responseEmployee(
            @RequestParam(value="firstName", required=true) String firstName,
            @RequestParam(value="lastName", required=true) String lastName,
            Model model) {
        model.addAttribute("lastName", lastName);
        model.addAttribute("firstName", firstName);

        Employee e = new Employee(firstName, lastName);
        //repository.save(e);
        employeeManager.createEmployee(e);
        for(Employee employee : employeeManager.findByFirstNameLastName(firstName, lastName)) {
            System.out.println(employee);
        }
        return "employee/response";
    }

    @RequestMapping("/")
    public String first(Model model) {
        return "index";
    }

    @RequestMapping("/employees")
    public ModelAndView employees() {

        ModelAndView mav = new ModelAndView("employees");
        mav.addObject("list", employeeManager.findEmployeeVacations());
        mav.addObject("year", (employeeManager.getYear() + 1900));

        return mav;
    }

    @RequestMapping("/schedule/request")
    public String requestSchedule(
            @RequestParam(value= "employeeId", required=true) String employeeId
            , Model model) {

        int id = Integer.parseInt(employeeId);
        Employee e = employeeManager.findById(id);
        List<VacationSchedule> vacationSchedules = scheduleManager.findByEmployee(e);

        model.addAttribute("employeeId", employeeId);
        model.addAttribute("vacations", vacationSchedules);
        model.addAttribute("employee", e);

        return "schedule/request";
    }

    @RequestMapping("/schedule/response")
    public String responseSchedule(
            @RequestParam(value= "employeeId", required=true) String employeeId
            ,@RequestParam(value="startDate", required = true) String startDate
            ,@RequestParam(value="endDate", required = true) String endDate
            , Model model) throws ParseException {

        model.addAttribute("employeeId", employeeId);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse(startDate);
        Date end = format.parse(endDate);



        int emplId = Integer.parseInt(employeeId);
        Employee employee = employeeManager.findById(emplId);


        Pair<Boolean, String> checkDatePair = checkManager.checkDate(employee, start, end);
        if(!checkDatePair.getKey()) {

            model.addAttribute("reason", checkDatePair.getValue());
            return "schedule/exception";
        }

        VacationSchedule vs = new VacationSchedule(employee, start, end);
        scheduleRepository.save(vs);

        return "schedule/response";
    }

    @RequestMapping("/delete/response")
    public String deleteVacation(
            @RequestParam(value= "employeeId", required=true) String employeeId
            ,@RequestParam(value="startDate", required = true) String startDate
            ,@RequestParam(value="endDate", required = true) String endDate
            ,@RequestParam(value="scheduleId", required = true) String scheduleId
            ,Model model) {

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);


        int id = Integer.parseInt(scheduleId);
        VacationSchedule vacationSchedule = scheduleManager.findById(id);
        scheduleManager.deleteSchedule(vacationSchedule);

        return "delete/response";

    }

}
