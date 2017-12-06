package inglobal;

import inglobal.model.Employee;
import inglobal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by evgenyandroshchuk on 04.12.17.
 */
@Controller
@EnableAutoConfiguration
public class WebController {

    @Autowired
    private EmployeeRepository repository;


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

        repository.save(new Employee(firstName, lastName));
        for(Employee employee : repository.findAll()) {
            System.out.println(employee);
        }
        return "employee/response";
    }

    @RequestMapping("/")
    public String first(Model model) {
        model.addAttribute("others", "Attribute others");
        return "index";
    }

    @RequestMapping("/employees")
    public ModelAndView employees() {

        ModelAndView mav = new ModelAndView("employees");
        mav.addObject("list", repository.findAll());

        return mav;
    }

}
