package inglobal.model;

/**
 * Created by evgenyandroshchuk on 12.12.17.
 */
public class EmployeeVacation {

    private Employee employee;
    private String vacation;
    private int countOfDays;

    public EmployeeVacation() {
        super();
    }

    public EmployeeVacation(Employee employee, String vacation) {
        this.employee = employee;
        this.vacation = vacation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public int getCountOfDays() {
        return countOfDays;
    }

    public void setCountOfDays(int countOfDays) {
        this.countOfDays = countOfDays;
    }
}
