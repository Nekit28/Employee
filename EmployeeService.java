package pro.sky.employeespringdemo;

import org.springframework.stereotype.Service;
import pro.sky.employeespringdemo.exception.EmployeeAlreadyAddedException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;
import pro.sky.employeespringdemo.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee("Иван", "Иванов", 100.0, 1));
        employees.add(new Employee("Иван1", "Иванов1", 999.9, 1));
        employees.add(new Employee("Иван2", "Иванов2", 8.50, 1));

        employees.add(new Employee("Никита", "Батура", 100.0, 2));

        employees.add(new Employee("Денис", "Павлов", 685.30, 3));
        employees.add(new Employee("андрей","петров", 836.70,3));
    }
    private final static int MAX_SIZE = 2;
    public Employee add(String firstName, String lastName, double salary,int departmentId) {

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmployee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("такой сотрудник уже есть");
        }


        employees.add(newEmployee);
        return newEmployee;
    }
    public Employee find(String firstName, String lastName, double salary,int departmentI) {
        Employee employeeForFound = new Employee(firstName, lastName, salary, departmentI);
        for (Employee e : employees) {
            if (e.equals(employeeForFound)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }
    public Employee remove(String firstName, String lastName, double salary,int departmentI) {
        Employee employeeForRemove = new Employee(firstName, lastName, salary, departmentI);
        boolean removeResult = employees.remove(employeeForRemove);
        if (removeResult) {
            return employeeForRemove;
        } else {
            throw new EmployeeNotFoundException(" Сотрудник не удалён - не был найден в базе");
        }
    }

    public List<Employee> getAll() {
        return null;
    }
}
