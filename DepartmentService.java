package pro.sky.employeespringdemo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service

public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {

        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден") );
    }
    public Employee getEmployeeWithMinSalary(Integer departmentId) {

        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден") );
    }

    public  List<Employee> getEmployeeByDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(toList());
    }

    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(groupingBy(employee -> employee.getDepartmentId(), toList()));


    }


}
