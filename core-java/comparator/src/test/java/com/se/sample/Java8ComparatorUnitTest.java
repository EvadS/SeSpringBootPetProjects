package com.se.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertTrue;

public class Java8ComparatorUnitTest {

    private Employee[] employees;
    private Employee[] employeesNullable;
    private Employee[] employeesArrayWithNulls;
    private Employee[] sortedEmployeesByName;
    private Employee[] sortedEmployeesByNameDesc;
    private Employee[] sortedEmployeesByAge;
    private Employee[] sortedEmployeesByMobile;
    private Employee[] sortedEmployeesBySalary;
    private Employee[] sortedEmployeesArray_WithNullsFirst;
    private Employee[] sortedEmployeesArray_WithNullsLast;
    private Employee[] sortedEmployeesByNameAge;
    private Employee[] someMoreEmployees;
    private Employee[] sortedEmployeesByAgeName;

    @Before
    public void initData() {
        employees = new Employee[] { new Employee("John", 25, 3000, 9922001), new Employee("Ace", 22, 2000, 5924001), new Employee("Keith", 35, 4000, 3924401) };

        employeesNullable = new Employee[] {
                new Employee("John", 25, 3000, 9922001),
                new Employee(null, 22, 2000, 5924001),
                new Employee("Keith", 35, 4000, 3924401),
                new Employee("Evgeniy", 25, 4000, 3924401),
                new Employee("null", null, 4000, 3924401)
        };

        employeesArrayWithNulls = new Employee[] {
                new Employee("John", 25, 3000, 9922001), null,
                new Employee("Ace", 22, 2000, 5924001), null,
                new Employee("Keith", 35, 4000, 3924401)
        };

        sortedEmployeesArray_WithNullsFirst = new Employee[] {
                null, null,
                new Employee("Ace", 22, 2000, 5924001),
                new Employee("John", 25, 3000, 9922001),
                new Employee("Keith", 35, 4000, 3924401)
        };

        sortedEmployeesArray_WithNullsLast = new Employee[] {
                new Employee("Ace", 22, 2000, 5924001),
                new Employee("John", 25, 3000, 9922001),
                new Employee("Keith", 35, 4000, 3924401), null, null
        };


        sortedEmployeesByName = new Employee[] { new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };
        sortedEmployeesByNameDesc = new Employee[] { new Employee("Keith", 35, 4000, 3924401), new Employee("John", 25, 3000, 9922001), new Employee("Ace", 22, 2000, 5924001) };

        sortedEmployeesByAge = new Employee[] { new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };

        sortedEmployeesByMobile = new Employee[] { new Employee("Keith", 35, 4000, 3924401), new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), };

        sortedEmployeesBySalary = new Employee[] { new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401), };



        someMoreEmployees = new Employee[] { new Employee("Jake", 25, 3000, 9922001), new Employee("Jake", 22, 2000, 5924001), new Employee("Ace", 22, 3000, 6423001), new Employee("Keith", 35, 4000, 3924401) };

        sortedEmployeesByAgeName = new Employee[] { new Employee("Ace", 22, 3000, 6423001), new Employee("Jake", 22, 2000, 5924001), new Employee("Jake", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };
        sortedEmployeesByNameAge = new Employee[] { new Employee("Ace", 22, 3000, 6423001), new Employee("Jake", 22, 2000, 5924001), new Employee("Jake", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };
    }

    @Test
    public void whenThenComparing_thenSortedByAgeName(){
        Comparator<Employee> employee_Age_Name_Comparator
                = Comparator.comparing(Employee::getAge)
                .thenComparing(Employee::getName);

        Arrays.sort(someMoreEmployees, employee_Age_Name_Comparator);

        assertTrue(Arrays.equals(someMoreEmployees, sortedEmployeesByAgeName));
    }

    @Test
    public void whenNullsFirst_thenSortedByNameWithNullsFirst() {
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullFirst = Comparator.nullsFirst(employeeNameComparator);

        Arrays.sort(employeesArrayWithNulls, employeeNameComparator_nullFirst);
         System.out.println(Arrays.toString(employeesArrayWithNulls));
        assertTrue(Arrays.equals(employeesArrayWithNulls, sortedEmployeesArray_WithNullsFirst));
    }

    @Test
    public void whenNullsFirst_thenSortedByNameWithNullsLast() {
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullLast = Comparator.nullsLast(employeeNameComparator);

        Arrays.sort(employeesArrayWithNulls, employeeNameComparator_nullLast);
        System.out.println(Arrays.toString(employeesArrayWithNulls));
        assertTrue(Arrays.equals(employeesArrayWithNulls, sortedEmployeesArray_WithNullsLast));
    }

    @Test
    public void whenThenComparing_thenSortedByNameAge() {
        Comparator<Employee> employee_Name_Age_Comparator =
                Comparator.comparing(Employee::getName)
                        .thenComparingInt(Employee::getAge);

        Arrays.sort(someMoreEmployees, employee_Name_Age_Comparator);
        // System.out.println(Arrays.toString(someMoreEmployees));

        Arrays.stream(someMoreEmployees).forEach(name -> {
            System.out.println(name);
        });
        assertTrue(Arrays.equals(someMoreEmployees, sortedEmployeesByNameAge));
    }

    @Test
    public void null_save_comparator_test(){
        Comparator<Employee> COMPARATOR1 =
                Comparator.comparing(Employee::getName, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(Employee::getSalary, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(Employee::getAge, Comparator.nullsLast(Comparator.naturalOrder()));

        System.out.println("before --> ");
        Arrays.stream(employeesNullable).forEach(name -> {
            System.out.println(name);
        });

        Arrays.sort(employeesNullable, COMPARATOR1);

        System.out.println("after --> ");
        Arrays.stream(employeesNullable).forEach(name -> {
            System.out.println(name);
        });

    }
}
