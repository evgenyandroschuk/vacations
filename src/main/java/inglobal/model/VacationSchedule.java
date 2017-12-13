package inglobal.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by evgenyandroshchuk on 06.12.17.
 */
@Entity
@NamedQuery(
        name="findEmployeeByfistNameLastName",
        query="SELECT OBJECT(emp) FROM Employee emp WHERE emp.firstName = :fistName and emp.lastName = :lastName")
public class VacationSchedule {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable=false)
    private Employee employee;


    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    public VacationSchedule() {
        super();
    }

    public VacationSchedule(Employee e, Date startDate, Date endDate) {
        this.employee = e;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(startDate);
    }

    public String getEndDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(endDate);
    }
}
