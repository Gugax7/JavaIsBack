import dev.lpa.domain.StoreEmployee;
import domain.Employee;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>(List.of(
                new Employee(1001,"Anderson", 2014),
                new Employee(1032,"Ana",2001),
                new Employee(1003,"Jefferson",2023),
                new Employee(1000,"Silvio",2013),
                new Employee(1231,"Lucas", 2017)
        ));

//        var comparator = new EmployeeComparator<>();
//        employeeList.sort(comparator);

        employeeList.sort(new Employee.EmployeeComparator<>(Employee.ComparationType.YEAR_ENTRANCE).reversed());

        for(Employee e : employeeList){
            System.out.println(e.toString());
        }

        System.out.println("Pigs with latin names:");
        addPigLatinName(employeeList);

    }
    public static void addPigLatinName(List<?extends Employee> list){

        class DecoratedEmployee extends StoreEmployee{
            private String pigLatinName;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName;
                this.originalInstance = originalInstance;
            }
            @Override
            public String toString(){
                return originalInstance.toString() + " " + pigLatinName;
            }

        }
        List<DecoratedEmployee> newList = new ArrayList<>(list.size());
        for(var employee: list){
            String name = employee.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedEmployee(pigLatin,employee));
        }

        for(var dEmploy: newList){
            System.out.println(dEmploy);
        }
    }
}