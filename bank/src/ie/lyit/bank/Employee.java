package ie.lyit.bank;

/**
 * @author  Florian Moss
 * @date    17/11/2019
 * @version 1.01
 * <h1> Employee Class </h1>
 * The type Employee, subclass of Person for Banking application.
 * Employees have unique numbers which are tracked via the static
 * variable nextNumber.
 * Every employee has a startDate, salary, number (employeeNumber).
 * A max. salary is defined as a final variable MAXIMUMSALARY and currently
 * set to 150.000.
 * Error checking for objects (Name, Date) is done in the respective classes
 * (Florian Moss, Assignment 1).
 *
 * The employee count is currently not persistent. Should be written to file
 * or DB for QA/Prod environment.
 *
 * SetNumber() does not exist, because the assigned employee number should
 * not be changed.
 *
 * SetSalary() does not exist, because the salary should only be changed
 * through incrementSalary(). If a reduction of the salary is needed, please
 * add decrementSalary().
 *
 * This class has been tested at 17/11/2019 (EmployeeTest.java).
 *
 * @version 1.01 fix: introduced minimum age of 14 for employees.
 */

public class Employee extends Person {
    private Date startDate;
    private double salary;
    private int number;
    // STATIC EMPLOYEE COUNT, NOT PERSISTENT @version 1.00
    private static int nextNumber = 0;
    // PRE-DEFINED MAXIMUMSALARY
    private final int MAXIMUMSALARY = 150000;

    /**
     * Instantiates a new Employee with default values. Design specification
     * requires this constructor, should be removed for future versions.
     */
    public Employee(){
        super();
        this.startDate = new Date();
        this.salary = 0;
        this.number = nextNumber++;
    }

    /**
     * Instantiates a new Employee with all parameters defined. Does not allow for
     * negative salaries. Child labour laws require persons to be aged 14 or above,
     * will throw an exception if not fulfilled.
     *
     * @param name        the name
     * @param dateOfBirth the date of birth
     * @param startDate   the start date
     * @param salary      the salary
     * @throws IllegalArgumentException the illegal argument exception, not permitting
     * negative salaries or persons aged below 14.
     */
    public Employee(Name name, Date dateOfBirth, Date startDate, double salary) throws IllegalArgumentException{
        super(name, dateOfBirth);
        if(salary < 0 || salary > MAXIMUMSALARY)
            throw new IllegalArgumentException("Salary can't be negative.");
        if(startDate.getYear() - dateOfBirth.getYear() < 14)
            throw new IllegalArgumentException("Child labour laws require employees" +
                    "to be 14 or older.");
        this.startDate = startDate;
        this.salary = salary;
        this.number = nextNumber++;
    }

    /**
     * Returns the employee object as a string.
     * @return Employee object string
     */
    @Override
    public String toString() {
        return "Employee{" +
                "startDate=" + startDate +
                ", salary=" + salary +
                ", number=" + number +
                ", name=" + name +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    /**
     * Method to check if 2 employee objects are equal. Requires
     * superclass and employee class values to be equal.
     * @param o     the object to compare to
     * @return      true if both objects are the same, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                number == employee.number &&
                startDate.equals(employee.startDate);
    }

    /**
     * Increment salary if increment amount is not negative and total
     * amount is <= the max. salary allowed (MAXIMUMSALARY).
     *
     * @param incrementAmount the increment amount
     * @throws IllegalArgumentException the illegal argument exception if increment
     * negative or total above MAXIMUMSALARY.
     */
    public void incrementSalary(double incrementAmount) throws IllegalArgumentException {
        if(incrementAmount >= 0 && salary+incrementAmount <= MAXIMUMSALARY)
            salary += incrementAmount;
        else
            throw new IllegalArgumentException();
    }

    /**
     * Calculate monthly wage, including all applicable taxes. Does not allow for
     * negative tax rate or tax rate above 100%.
     *
     * @param taxPercentage the tax percentage to be taken off.
     * @return the taxRate applicable
     * @throws IllegalArgumentException the illegal argument exception if tax
     * rate is negative or above 100%.
     */
    public double calculateWage(double taxPercentage) throws IllegalArgumentException{
        if(taxPercentage >=0 && taxPercentage <= 100)
            return salary*(1-(taxPercentage/100))/12;
        else
            throw new IllegalArgumentException();
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets salary.
     *
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets next number.
     *
     * @return the next number
     */
    public static int getNextNumber() {
        return nextNumber;
    }

    /**
     * Gets maximumsalary.
     *
     * @return the maximumsalary
     */
    public int getMAXIMUMSALARY() {
        return MAXIMUMSALARY;
    }

    /**
     * Overriden setDateOfBirth method to ensure that employees
     * fulfill legal requirement of being at least of age 14.
     * @param dateOfBirth new
     * @throws IllegalArgumentException if new age is below 14 years.
     */
    @Override
    public void setDateOfBirth(Date dateOfBirth) throws IllegalArgumentException{
        if(!(this.getStartDate().getYear() - dateOfBirth.getYear() < 14))
            this.dateOfBirth = dateOfBirth;
        else
            throw new IllegalArgumentException();
    }
}
