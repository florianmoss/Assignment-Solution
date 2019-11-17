package ie.lyit.testers;

import ie.lyit.bank.Date;
import ie.lyit.bank.Employee;
import ie.lyit.bank.Name;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * @author  Florian Moss
 * @date    17/11/2019
 * @version 1.01
 * <h1> Employee Test Class </h1>
 * The employee type is tested with this class. It ensures that the constructors
 * work as expected and all class methods (not GETTERS).
 *
 * This test class assumes that the Date class and Name class provide
 * necessary error checking for invalid Dates and invalid Names.
 *
 * Errors found (fixed in Assignment 1, Florian Moss):
 * 30/02/2018 - is a valid date  -> need to adjust Date class
 * 31/04//2007 - is a valid date. - > need to adjust Date class
 * Dates in the future are permitted  - > need to adjust Date class
 * Employees can have names with special characters and digits.
 *                                  - > need to adjust Name class
 *
 */

/**
 * The type Employee test.
 */
public class EmployeeTest {
    private Employee e;
    private Employee eA;

    /**
     * Set up for 2 objects. Using empty and variable constructor respectively.
     */
    @Before
    public final void setUp() {
        e = new Employee();
        eA = new Employee(
                    new Name("Ms","Lisa","Simpson"),
                    new Date(1,2,2004),
                    new Date(1,2,2019),
                    400);
    }

    /**
     * Illegal salary constructor.
     * -5 will throw an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void illegalSalaryConstructor5(){
        Employee eB = new Employee(
                new Name("Ms","Lisa","Simpson"),
                new Date(1,2,2004),
                new Date(1,2,2019),
                -5);
    }

    /**
     * Illegal salary constructor.
     * 150001 will throw an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void illegalSalaryConstructor150001(){
        Employee eB = new Employee(
                new Name("Ms","Lisa","Simpson"),
                new Date(1,2,2004),
                new Date(1,2,2019),
                150001);
    }

    /**
     * Illegal age constructor.
     * Age below 14 will throw an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void illegalAgeConstructor(){
        Employee eB = new Employee(
                new Name("Ms","Lisa","Simpson"),
                new Date(1,2,2006),
                new Date(1,2,2019),
                10);
    }

    /**
     * Legal salary constructor.
     * 0 is valid.
     */
    @Test
    public final void legalSalaryConstructor0(){
        Employee eB = new Employee(
                new Name("Ms","Lisa","Simpson"),
                new Date(1,2,2004),
                new Date(1,2,2019),
                0);
    }

    /**
     * Legal salary constructor.
     * 150000 is valid.
     */
    @Test
    public final void legalSalaryConstructor150000(){
        Employee eB = new Employee(
                new Name("Ms","Lisa","Simpson"),
                new Date(1,2,2004),
                new Date(1,2,2019),
                150000);
    }

    /**
     * ToString could be tested for a specific string and comparing both
     * with equalsIgnoreCase() - very tidious. Ensure that no @ appears
     * or use ToStringVerifier class as per Stackoverflow Thread:
     * https://stackoverflow.com/questions/12528420/should-i-test-tostring-with-junit
     *
     * Legal String.
     */
    @Test
    public final void toStringVerify(){
        assertFalse(new Employee().toString().contains("@"));
        // Alternatively use ToString verifier - needs to be imported
        // ToStringVerifier.forClass(Employee.class).verify();
    }

    /**
     * Illegal salary incrementer, too high.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void illegalSalaryIncrementerTooHigh(){
        eA.incrementSalary(150001);
    }

    /**
     * Illegal salary incrementer, negative values not allowed.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void illegalSalaryIncrementerNegative(){
        eA.incrementSalary(-1);
    }

    /**
     * Legal salary incrementer = 10.
     */
    @Test()
    public final void legalSalaryIncrementer10(){
        eA.incrementSalary(10);
        assertTrue(eA.getSalary() == 410);
    }

    /**
     * Legal salary incrementer = 0.
     */
    @Test()
    public final void legalSalaryIncrementer0(){
        eA.incrementSalary(0);
        assertTrue(eA.getSalary() == 400);
    }

    /**
     * Calculate wage,  negative tax rate disallowed.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculateWageNegative(){
        eA.calculateWage(-1);
    }

    /**
     * Calculate wage, taxrate over 100 disallowed.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculateWageOver100(){
        eA.calculateWage(101);
    }

    /**
     * Calculate wage, taxrate 10%. Valid.
     */
    @Test
    public final void calculateWage10(){
        assertTrue(eA.calculateWage(10) == 30);
    }

    /**
     * Set start date test. Varifies the validity of the entered date.
     */
    @Test
    public final void setStartDateTest(){
        eA.setStartDate(new Date(2, 2, 2010));
        assertEquals(eA.getStartDate(), new Date(2, 2, 2010));
    }

    /**
     * Set name test. Verifies the validity of the entered name.
     */
    @Test
    public final void setNameTest(){
        eA.setName(new Name("Mr", "Linus", "Torvalds"));
        assertTrue(eA.getName().getTitle() == "Mr");
        assertTrue(eA.getName().getFirstName() == "Linus");
        assertTrue(eA.getName().getSurname() == "Torvalds");
    }

    /**
     * Set date of birth test, illegal age. Below 14.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void setDateOfBirthTestInvalid(){
        eA.setDateOfBirth(new Date(2, 2, 2010));
        assertEquals(eA.getDateOfBirth(), new Date(2, 2, 2010));
    }

    /**
     * Set date of birth test, legal age.
     */
    @Test
    public final void setDateOfBirthTestValid(){
        eA.setStartDate(new Date(2,2, 2020));
        eA.setDateOfBirth(new Date(2, 2, 2006));
        assertEquals(eA.getDateOfBirth(), new Date(2, 2, 2006));
    }
}
