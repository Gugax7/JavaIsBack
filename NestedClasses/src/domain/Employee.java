package domain;

import java.util.Comparator;

public class Employee {

    public static enum ComparationType{YEAR_ENTRANCE,NAME,CODE}


    public static class EmployeeComparator <T extends Employee>
            implements Comparator<Employee> {
        ComparationType type;
        @Override
        public int compare(Employee o1, Employee o2) {
            if(type == ComparationType.CODE){
                return o1.employeeId - o2.employeeId;
            }
            if(type == ComparationType.YEAR_ENTRANCE){
                return o1.yearStarted - o2.yearStarted;
            }
            return o1.yearStarted - o2.yearStarted;
        }
        public EmployeeComparator(ComparationType comparationType){
            this.type = comparationType;
        }
    }

    private int employeeId;
    private String name;
    private int yearStarted;

    public Employee() {
    }

    public Employee(int employeeId, String name, int yearStarted) {
        this.employeeId = employeeId;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%d %-8s %d".formatted(employeeId,name,yearStarted);
    }
}
